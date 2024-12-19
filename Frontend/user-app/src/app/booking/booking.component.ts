import { Component, OnInit, SimpleChanges } from '@angular/core';
import { Booking } from '../model/Booking';
import { Router } from '@angular/router';
import { Schedule } from '../model/Schedule';
import { Route } from '../model/Route';
import { RouteFare } from '../model/RouteFare';
import { RouteService } from '../service/route.service';
import { ScheduleService } from '../service/schedule.service';
import { BookingService } from '../service/booking.service';
import { DatePipe } from '@angular/common';
import { PaymentService } from '../service/payment.service';
import { Payment } from '../model/Payment';
import { ScheduleValueObject } from '../model/ScheduleValueObject';

@Component({
  selector: 'app-booking',
  templateUrl: './booking.component.html',
  styleUrls: ['./booking.component.css']
})
export class BookingComponent implements OnInit {
  datePipe: DatePipe = new DatePipe('en-US');

  // Route details
  origin: string = '';
  destination: string = '';
  origins: string[] = [];
  destinations: string[] = [];
  selectedRoute: Route = new Route();
  routeFare: RouteFare = new RouteFare();

  // Schedule details
  departure: string = '';
  arrival: string = '';
  departureDate: Date = new Date();
  departureTime: number = 0;
  times: string[] = [];
  arrivalDate: Date = new Date();
  arrivalTime: Date = new Date();
  amount: number = 0.0;
  schedule: Schedule = new Schedule();
  scheduleValueObject: ScheduleValueObject = new ScheduleValueObject();

  // Booking details
  booking: Booking = new Booking();
  seatNumber: number = 0;

  // Payment
  payment: Payment = new Payment();

  constructor(
    private route: Router,
    private routeService: RouteService,
    private scheduleService: ScheduleService,
    private bookingService: BookingService,
    private paymentService: PaymentService
  ) {}

  async ngOnInit() {
    await this.setOrigins();
    await this.setDestinations();
    await this.setTimeSlots();
  }

  // Set the available origins for the route
  async setOrigins() {
    await this.routeService.getAllOrigins().subscribe((data) => {
      this.origins = data;
    });
  }

  // Set the available destinations for the route
  async setDestinations() {
    await this.routeService.getAllDestinations().subscribe((data) => {
      this.destinations = data;
    });
  }

  // Set the available time slots for scheduling
  async setTimeSlots() {
    await this.routeService.getAllSlots().subscribe((data) => {
      this.times = data;
      console.log(this.times);
    });
  }

  // Get the route based on selected origin and destination
  async getRoute() {
    await this.routeService
      .getRouteByOriginAndDestination(this.origin, this.destination)
      .subscribe((data) => {
        this.selectedRoute = data;
        console.log(this.selectedRoute);

        this.getRouteFare();
      });
  }

  // Get the fare details for the selected route
  async getRouteFare() {
    await this.routeService.getRouteFare().subscribe((data) => {
      this.routeFare = data;
      console.log(this.routeFare);

      this.amount =
        this.routeFare.basicCost +
        this.selectedRoute.distance * this.routeFare.costPerKM;

      console.log(this.amount);
    });
  }

  // Book a ticket
  bookTicket() {
    this.getSeat()
      .then(() => this.makeSchedule())
      .then(() => this.makeBooking())
      .then(() => this.makePayment())
      .then(() => {
        localStorage.setItem('bookingID', String(this.payment.bookingID));
        localStorage.setItem('paymentID', String(this.payment.id));
        localStorage.setItem('amount', String(this.payment.amount));
        this.paymentGateway();
      })
      .catch((error) => {
        console.error('Error:', error);
        // Handle error if needed
      });
  }

  // Get the next available seat number
  async getSeat() {
    return new Promise<void>((resolve, reject) => {
      const departure = '' + this.departureDate + 'T' + this.departureTime;

      this.scheduleService
        .getBookedSeats(departure, this.selectedRoute.id)
        .subscribe(
          (data) => {
            this.seatNumber = Number(data) + 1;
            console.log(data);
            resolve();
          },
          (error) => {
            reject(error);
          }
        );
    });
  }

  // Create a schedule
  async makeSchedule() {
    return new Promise<void>((resolve, reject) => {
      this.setSchedule();

      this.scheduleService.createSchedule(this.scheduleValueObject).subscribe(
        (data) => {
          this.schedule = data;
          console.log(this.schedule);
          resolve();
        },
        (error) => {
          reject(error);
        }
      );
    });
  }

  // Create a booking
  async makeBooking() {
    return new Promise<void>((resolve, reject) => {
      this.setBooking(this.schedule.id);

      this.bookingService.createBooking(this.booking).subscribe(
        (data) => {
          this.booking = data;
          console.log(this.booking);
          resolve();
        },
        (error) => {
          reject(error);
        }
      );
    });
  }

  // Make a payment
  async makePayment() {
    return new Promise<void>((resolve, reject) => {
      this.setPayment(this.booking.id);

      this.paymentService.makePayment(this.payment).subscribe(
        (data) => {
          this.payment = data;
          console.log(this.payment);
          resolve();
        },
        (error) => {
          reject(error);
        }
      );
    });
  }

  // Set the schedule details
  setSchedule() {
    // Setting Departure & Arrival
    const stringDepartureHour = String(this.departureTime).split(':')[0];
    const stringDurationHour = String(this.selectedRoute.duration).split(':')[0];
    let arrivalTime = parseInt(stringDurationHour) + parseInt(stringDepartureHour);
    let arrivalTimeString = '0';
    const arrivalDate = new Date(this.departureDate);
    let newDate = false;

    if (arrivalTime > 24) {
      arrivalTime = arrivalTime - 24;
      newDate = true;
    }

    if (newDate) {
      arrivalDate.setDate(arrivalDate.getDate() + 1);
    }

    arrivalTimeString = String(arrivalTime);

    if (arrivalTime < 10) {
      arrivalTimeString = '0' + arrivalTimeString;
    }

    const stringArrivalDate = this.datePipe.transform(arrivalDate, 'yyyy-MM-dd');

    // Saving in schedule
    this.scheduleValueObject.departureTime = this.departureDate + 'T' + this.departureTime;
    this.scheduleValueObject.arrivalTime = stringArrivalDate + 'T' + arrivalTimeString + ':00:00';
    this.scheduleValueObject.routeID = this.selectedRoute.id;
    this.scheduleValueObject.amount = this.amount;

    console.log(this.scheduleValueObject);
  }

  // Set the booking details
  setBooking(scheduleID: number) {
    this.booking.userID = Number(localStorage.getItem('userID'));
    this.booking.scheduleID = scheduleID;
    this.booking.status = 'Booked';
    this.booking.seatNumber = this.seatNumber;
  }

  // Set the payment details
  setPayment(bookingID: number) {
    this.payment.bookingID = bookingID;
    this.payment.amount = this.amount;
  }

  // Navigate to the payment gateway
  paymentGateway() {
    this.route.navigate(['/payment']);
  }

  // Navigate back to the dashboard
  backToDashboard() {
    this.route.navigate(['/dashboard']);
  }
}
