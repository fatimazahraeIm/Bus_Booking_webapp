/**
 * Module: Booking Module
 * Sub Modules: Booking, Cancellation, Review
 * Aim: To provide the service implementation for booking module
 * Author: Srijan Singh
 * Date: 07/06/2023 
 */
package com.busproject.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.busproject.entity.Booking;
import com.busproject.entity.Cancellation;
import com.busproject.entity.Review;
import com.busproject.repo.BookingRepository;
import com.busproject.repo.CancellationRepository;
import com.busproject.repo.ReviewRepository;

@Repository
public class BookingServiceImpl implements BookingService {

    /**
     * Autowiring the booking repository
     */
    @Autowired
    private BookingRepository bookingRepository;

    /**
     * Autowiring the cancellation repository
     */
    @Autowired
    private CancellationRepository cancellationRepository;

    /**
     * Autowiring the review repository
     */
    @Autowired
    private ReviewRepository reviewRepository;

    /**
     * Method to make a booking
     * @param booking
     * @return bookingID
     */
    @Override
    public Booking makeBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    /**
     * Method to get booking details
     * @param bookingID
     * @return booking
     */
    @Override
    public Booking getBookingDetails(int bookingID) {
        return bookingRepository.findById(bookingID).orElse(null);
    }
    
    /**
     * Method to get bookings from userID
     * @param userID
     * @return List<booking> (Latest Booking First)
     */
    @Override
    public List<Booking> getBookingDetailsByUserID(int userID) {
      List<Booking> bookings = bookingRepository.getBookingsByUserID(userID);
      Collections.reverse(bookings);
      return bookings;
    }

    /**
     * Method to cancel a booking
     * @param cancellation
     * @param bookingID
     * @return cancellationID
     */
    @Override
    public Booking cancelBooking(Cancellation cancellation, int bookingID) {
        Booking booking = bookingRepository.findById(bookingID).orElse(null);
        if(booking != null) {
            booking.setStatus("Cancelled");
            bookingRepository.save(booking);
            cancellation.setBooking(booking);
            cancellationRepository.save(cancellation);
        }   
        return booking;
    }

    /**
     * Method to get cancellation details
     * @param cancellationID
     * @return cancellation
     */
    @Override
    public Cancellation getCancellationDetails(int cancellationID) {
        return cancellationRepository.findById(cancellationID).orElse(null);
    }

    /**
     * Method to review a booking
     * @param review
     * @param bookingID
     * @return reviewID
     */
    @Override
    public Booking reviewBooking(Review review, int bookingID) {
        Booking booking = bookingRepository.findById(bookingID).orElse(null);
        if(booking != null) {
            booking.setStatus("Reviewed");
            bookingRepository.save(booking);
            review.setUserID(booking.getUserID());
            reviewRepository.save(review);
        }
        return booking;
    }

    /**
     * Method to get review details
     * @param reviewID
     * @return review
     */
    @Override
    public Review getReviewDetails(int reviewID) {
        return reviewRepository.findById(reviewID).orElse(null);
    }
    
}
