export class API{
 
    protected userAPI = "http://localhost:9001/" + "user/";

    protected bookingAPI =  "http://localhost:9002/" + "booking/";

    protected routeAPI =  "http://localhost:9003/" + "route/";

    protected scheduleAPI =  "http://localhost:9004/" + "schedule/";

    protected paymentAPI =  "http://localhost:9005/" + "payment/";

    protected emailAPI = "http://localhost:9006/"+"send-email";

}

export class UserAPI extends API{
    constructor(){
        super();
    }
  
    public registerUser(){
        return this.userAPI+"registeration";
    }
  
    public loginUser(){
        return this.userAPI+"login";
    }
  
    public bookRide(userID : number){
        return this.userAPI+"bookRide/"+userID;
    }
  
    public cancelRide(bookingID : number){
        return this.userAPI+"cancelRide/"+bookingID;
    }
  
    public reviewRide(bookingID : number){
        return this.userAPI+"reviewRide/"+bookingID;
    }
  
    public getAllUsers(){
        return this.userAPI+"listAll"
    }
  }

  export class BookingAPI extends API {

    constructor() {
      super();
    }

    public getBookingAPI(){
        return this.bookingAPI;
    }

    public makeBooking(){
        return this.bookingAPI
    }

    public getBookingbyUserID(userID : string){
        return this.bookingAPI+"all/"+userID;
    }

    public cancelBooking(bookingID : number){
        return this.bookingAPI+"cancel/"+bookingID
    }

    public reviewBooking(bookingID : number){
        return this.bookingAPI+"review/"+bookingID
    }
    
  }

export class RouteAPI extends API{

    constructor(){
        super();
    }
    
    public addRoute(){
        return this.routeAPI+"add";
    }

    public getRouteByID(routeID : number){
        return this.routeAPI+"get/"+routeID;
    }

    public getRouteByOrigin(origin : string){
        return this.routeAPI+"origin/"+origin;
    }

    public getRouteByOriginAndDestination(origin : string, destination : string){
        return this.routeAPI+"origin/"+origin+"/destination/"+destination;
    }

    public getAllOrigins(){
        return this.routeAPI+"origin/all"
    }

    public getAllDestinations(){
        return this.routeAPI+"destination/all"
    }

    public getRouteFare(fareID : number = 1){
        return this.routeAPI+"fare/"+fareID;
    }

    public getAllSlots(){
        return this.routeAPI+"slot/all";
    }

    public getAllRoutes(){
        return this.routeAPI+"all";
    }
}
  
export class ScheduleAPI extends API{
    
    constructor(){
        super();
    }    
    
    public makeSchedule(){
        return this.scheduleAPI;
    }

    public getScheduleByID(scheduleID : number){
        return this.scheduleAPI+scheduleID;
    }

    public getScheduleByRouteID(routeID : number){
        return this.scheduleAPI+"findByRoute/"+routeID;
    }

    public getNNumberOfBookedScheduleByDepartureTimeAndRootID(departureTime : string, routeID : number){
        return this.scheduleAPI+"booked/route/"+routeID+"/departure/"+departureTime
    }

    public getAllSchedules(){
        return this.scheduleAPI+"all";
    }
}

export class PaymentAPI extends API{

    constructor(){
        super();
    }

    public makePayment(){
        return this.paymentAPI;
    }

    public getPaymentByBookingID(bookingID : number){
        return this.paymentAPI+bookingID;
    }

    public getAllPayments(){
        return this.paymentAPI+"all";
    }
}

export class EmailAPI extends API{

    public sendEmail(){
        return this.emailAPI;
    }
}