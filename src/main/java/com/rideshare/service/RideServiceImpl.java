package com.rideshare.service;

import com.rideshare.entity.Driver;
import com.rideshare.entity.Ride;
import com.rideshare.iservice.IRideService;
import com.rideshare.repository.DriverRepository;
import com.rideshare.repository.RideRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class RideServiceImpl implements IRideService {
    
    private final RideRepository rideRepository;
    private final DriverRepository driverRepository;
    
    @Override
    public Ride createRide(Ride ride, Long driverId) {
        Driver driver = driverRepository.findById(driverId)
            .orElseThrow(() -> new RuntimeException("Conducteur non trouvé"));
        
        if (driver.getVehicle() == null) {
            throw new RuntimeException("Le conducteur doit avoir un véhicule pour créer un trajet");
        }
        
        if (ride.getPlacesDisponibles() > driver.getVehicle().getNombrePlaces()) {
            throw new RuntimeException("Le nombre de places dépasse la capacité du véhicule");
        }
        
        ride.setDriver(driver);
        return rideRepository.save(ride);
    }
    
    @Override
    public Ride updateRide(Long id, Ride ride) {
        Ride existingRide = getRideById(id);
        existingRide.setVilleDepart(ride.getVilleDepart());
        existingRide.setVilleArrivee(ride.getVilleArrivee());
        existingRide.setDateHeureDepart(ride.getDateHeureDepart());
        existingRide.setPrixParPlace(ride.getPrixParPlace());
        existingRide.setPlacesDisponibles(ride.getPlacesDisponibles());
        existingRide.setPointRencontreDepart(ride.getPointRencontreDepart());
        existingRide.setPointRencontreArrivee(ride.getPointRencontreArrivee());
        return rideRepository.save(existingRide);
    }
    
    @Override
    public Ride getRideById(Long id) {
        return rideRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Trajet non trouvé avec l'ID: " + id));
    }
    
    @Override
    public List<Ride> getAllRides() {
        return rideRepository.findAll();
    }
    
    @Override
    public void deleteRide(Long id) {
        rideRepository.deleteById(id);
    }
    
    @Override
    public List<Ride> searchRides(String depart, String arrivee) {
        return rideRepository.findByVilleDepartAndVilleArrivee(depart, arrivee);
    }
    
    @Override
    public List<Ride> searchAvailableRides(String depart, String arrivee, 
                                           LocalDateTime date, Integer places) {
        return rideRepository.searchAvailableRides(depart, arrivee, date, places);
    }
    
    @Override
    public List<Ride> getRidesByDriver(Long driverId) {
        return rideRepository.findByDriverId(driverId);
    }
}
