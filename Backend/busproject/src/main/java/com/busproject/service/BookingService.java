/**
 * Module: Booking Module
 * Sub Modules: Booking, Cancellation, Review
 * Aim: To provide the service for booking module
 * Author: Srijan Singh
 * Date: 07/06/2023 
 */
package com.busproject.service;

import java.util.List;

import com.busproject.entity.Booking;
import com.busproject.entity.Cancellation;
import com.busproject.entity.Review;

/**
 * Interface for Booking Service
 */
public interface BookingService {
    
    /**
     * Method to make a booking
     * @param booking
     * @return
     */
    Booking makeBooking(Booking booking);

    /**
     * Method to get booking details
     * @param bookingID
     * @return
     */
    Booking getBookingDetails(int bookingID);
    
    /**
     * Method to get bookings from userID
     * @param userID
     * @return List<Booking>
     */
    List<Booking> getBookingDetailsByUserID(int userID);

    /**
     * Method to cancel a booking
     * @param cancellation
     * @param bookingID
     * @return
     */
    Booking cancelBooking(Cancellation cancellation, int bookingID);

    /**
     * Method to get cancellation details
     * @param cancellationID
     * @return
     */
    Cancellation getCancellationDetails(int cancellationID);

    /**
     * Method to review a booking
     * @param review
     * @param bookingID
     * @return
     */
    Booking reviewBooking(Review review, int bookingID);

    /**
     * Method to get review details
     * @param reviewID
     * @return
     */
    Review getReviewDetails(int reviewID);
}
