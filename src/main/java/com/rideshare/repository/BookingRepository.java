package com.rideshare.repository;

import com.rideshare.entity.Booking;
import com.rideshare.entity.BookingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    
    // Réservations d'un passager
    List<Booking> findByPassengerId(Long passengerId);
    
    // Réservations d'un trajet
    List<Booking> findByRideId(Long rideId);
    
    // Réservations par statut
    List<Booking> findByStatut(BookingStatus statut);
    
    // Compter les places réservées pour un trajet
    @Query("SELECT SUM(b.nombrePlacesReservees) FROM Booking b WHERE b.ride.id = :rideId AND b.statut = 'CONFIRMEE'")
    Integer countConfirmedSeatsForRide(Long rideId);
}
