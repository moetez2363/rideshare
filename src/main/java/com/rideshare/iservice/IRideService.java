package com.rideshare.iservice;

import com.rideshare.entity.Ride;
import java.time.LocalDateTime;
import java.util.List;

public interface IRideService {
    Ride createRide(Ride ride, Long driverId);
    Ride updateRide(Long id, Ride ride);
    Ride getRideById(Long id);
    List<Ride> getAllRides();
    void deleteRide(Long id);
    List<Ride> searchRides(String depart, String arrivee);
    List<Ride> searchAvailableRides(String depart, String arrivee, LocalDateTime date, Integer places);
    List<Ride> getRidesByDriver(Long driverId);
}
