
package com.busproject.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.busproject.entity.Booking;
import com.busproject.entity.Cancellation;
import com.busproject.entity.Review;
import com.busproject.service.BookingService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class BookingController {
    
    @Autowired
    private BookingService bookingService;

    /**
     * This method uses Post Request to book a bus ride
     * @param booking
     * @return bookingID
     */
    @PostMapping(value="/booking", consumes="application/json")
    public Booking bookBusRide(@RequestBody Booking booking) {
        return bookingService.makeBooking(booking);
    }

    /**
     * This method uses Get Request to get the details of a booked bus ride
     * @param bookingID
     * @return booking
     */
    @GetMapping(value="/booking/get/{bookingID}", produces="application/json")
    public Booking getBusRideDetails(@PathVariable int bookingID) {
        return bookingService.getBookingDetails(bookingID);
    }

    /**
     * This method uses Post Request to cancel a booked bus ride
     * @param cancellation
     * @param bookingID
     * @return cancellationID
     */
    @PostMapping(value="/booking/cancel/{bookingID}", consumes="application/json")
    public Booking cancelBusRide(@RequestBody Cancellation cancellation, @PathVariable int bookingID) {
        return bookingService.cancelBooking(cancellation, bookingID);
    }

    /**
     * This method uses Get Request to get the details of a cancelled bus ride
     * @param cancellationID
     * @return cancellation
     */
    @GetMapping(value="/booking/cancel/get/{cancellationID}", produces="application/json")
    public Cancellation getCancelledBusRideDetails(@PathVariable int cancellationID) {
        return bookingService.getCancellationDetails(cancellationID);
    }

    /**
     * This method uses Post Request to review a booked bus ride
     * @param review
     * @param bookingID
     * @return reviewID
     */
    @PostMapping(value="/booking/review/{bookingID}", consumes="application/json")
    public Booking reviewBusRide(@RequestBody Review review, @PathVariable int bookingID) {
        return bookingService.reviewBooking(review, bookingID);
    }

    /**
     * This method uses Get Request to get the details of a reviewed bus ride
     * @param reviewID
     * @return review
     */
    @GetMapping(value="/booking/review/get/{reviewID}", produces="application/json")
    public Review getReviewedBusRideDetails(@PathVariable int reviewID) {
        return bookingService.getReviewDetails(reviewID);
    } 

    /**
     * This method uses Get Request to get the details of all booked bus ride of a user
     * @param userID
     * @return List<booking>
     */
    @GetMapping(value="/booking/all/{userID}", produces="application/json")
    public List<Booking> getBookingDetailsByUserID(@PathVariable int userID) {
        return bookingService.getBookingDetailsByUserID(userID);
    }
}
