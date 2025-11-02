package com.rideshare.service;

import com.rideshare.entity.Booking;
import com.rideshare.entity.BookingStatus;
import com.rideshare.entity.Passenger;
import com.rideshare.entity.Ride;
import com.rideshare.iservice.IBookingService;
import com.rideshare.repository.BookingRepository;
import com.rideshare.repository.PassengerRepository;
import com.rideshare.repository.RideRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class BookingServiceImpl implements IBookingService {
    
    private final BookingRepository bookingRepository;
    private final RideRepository rideRepository;
    private final PassengerRepository passengerRepository;
    
    @Override
    public Booking createBooking(Booking booking, Long rideId, Long passengerId) {
        Ride ride = rideRepository.findById(rideId)
            .orElseThrow(() -> new RuntimeException("Trajet non trouvé"));
        
        Passenger passenger = passengerRepository.findById(passengerId)
            .orElseThrow(() -> new RuntimeException("Passager non trouvé"));
        
        // Vérifier les places disponibles
        if (booking.getNombrePlacesReservees() > ride.getPlacesDisponibles()) {
            throw new RuntimeException("Pas assez de places disponibles");
        }
        
        // Calculer le montant total
        Double montantTotal = ride.getPrixParPlace() * booking.getNombrePlacesReservees();
        
        booking.setRide(ride);
        booking.setPassenger(passenger);
        booking.setMontantTotal(montantTotal);
        booking.setDateReservation(LocalDateTime.now());
        booking.setStatut(BookingStatus.EN_ATTENTE);
        
        return bookingRepository.save(booking);
    }
    
    @Override
    public Booking updateBookingStatus(Long id, BookingStatus status) {
        Booking booking = getBookingById(id);
        BookingStatus oldStatus = booking.getStatut();
        booking.setStatut(status);
        
        Ride ride = booking.getRide();
        
        // Ajuster les places disponibles
        if (status == BookingStatus.CONFIRMEE && oldStatus == BookingStatus.EN_ATTENTE) {
            ride.setPlacesDisponibles(ride.getPlacesDisponibles() - booking.getNombrePlacesReservees());
        } else if (status == BookingStatus.ANNULEE && oldStatus == BookingStatus.CONFIRMEE) {
            ride.setPlacesDisponibles(ride.getPlacesDisponibles() + booking.getNombrePlacesReservees());
        }
        
        rideRepository.save(ride);
        return bookingRepository.save(booking);
    }
    
    @Override
    public Booking getBookingById(Long id) {
        return bookingRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Réservation non trouvée avec l'ID: " + id));
    }
    
    @Override
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }
    
    @Override
    public void deleteBooking(Long id) {
        Booking booking = getBookingById(id);
        if (booking.getStatut() == BookingStatus.CONFIRMEE) {
            throw new RuntimeException("Impossible de supprimer une réservation confirmée. Annulez-la d'abord.");
        }
        bookingRepository.deleteById(id);
    }
    
    @Override
    public List<Booking> getBookingsByPassenger(Long passengerId) {
        return bookingRepository.findByPassengerId(passengerId);
    }
    
    @Override
    public List<Booking> getBookingsByRide(Long rideId) {
        return bookingRepository.findByRideId(rideId);
    }
}
