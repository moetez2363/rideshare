package com.rideshare.repository;

import com.rideshare.entity.Ride;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface RideRepository extends JpaRepository<Ride, Long> {
    
    // Rechercher des trajets par ville de départ et d'arrivée
    List<Ride> findByVilleDepartAndVilleArrivee(String villeDepart, String villeArrivee);
    
    // Rechercher des trajets disponibles (avec places)
    List<Ride> findByPlacesDisponiblesGreaterThan(Integer places);
    
    // Rechercher les trajets d'un conducteur
    List<Ride> findByDriverId(Long driverId);
    
    // Rechercher des trajets par date
    @Query("SELECT r FROM Ride r WHERE r.dateHeureDepart >= :dateDebut AND r.dateHeureDepart <= :dateFin")
    List<Ride> findRidesBetweenDates(
        @Param("dateDebut") LocalDateTime dateDebut, 
        @Param("dateFin") LocalDateTime dateFin
    );
    
    // Recherche complète (ville départ, arrivée, date, places disponibles)
    @Query("SELECT r FROM Ride r WHERE r.villeDepart = :depart " +
           "AND r.villeArrivee = :arrivee " +
           "AND r.dateHeureDepart >= :date " +
           "AND r.placesDisponibles >= :places")
    List<Ride> searchAvailableRides(
        @Param("depart") String villeDepart,
        @Param("arrivee") String villeArrivee,
        @Param("date") LocalDateTime date,
        @Param("places") Integer places
    );
}
