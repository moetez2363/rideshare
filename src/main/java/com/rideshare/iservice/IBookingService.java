package com.rideshare.iservice;

import com.rideshare.entity.Booking;
import com.rideshare.entity.BookingStatus;
import java.util.List;

public interface IBookingService {
    Booking createBooking(Booking booking, Long rideId, Long passengerId);
    Booking updateBookingStatus(Long id, BookingStatus status);
    Booking getBookingById(Long id);
    List<Booking> getAllBookings();
    void deleteBooking(Long id);
    List<Booking> getBookingsByPassenger(Long passengerId);
    List<Booking> getBookingsByRide(Long rideId);
}
