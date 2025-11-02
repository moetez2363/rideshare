package com.rideshare.controller;

import com.rideshare.entity.Ride;
import com.rideshare.iservice.IRideService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/rides")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class RideController {
    
    private final IRideService rideService;
    
    @PostMapping("/driver/{driverId}")
    public ResponseEntity<Ride> createRide(@Valid @RequestBody Ride ride,
                                          @PathVariable Long driverId) {
        try {
            Ride created = rideService.createRide(ride, driverId);
            return new ResponseEntity<>(created, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
    
    @GetMapping
    public ResponseEntity<List<Ride>> getAllRides() {
        return ResponseEntity.ok(rideService.getAllRides());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Ride> getRideById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(rideService.getRideById(id));
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Ride> updateRide(@PathVariable Long id,
                                          @Valid @RequestBody Ride ride) {
        try {
            return ResponseEntity.ok(rideService.updateRide(id, ride));
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRide(@PathVariable Long id) {
        rideService.deleteRide(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<Ride>> searchRides(
            @RequestParam String depart,
            @RequestParam String arrivee) {
        List<Ride> rides = rideService.searchRides(depart, arrivee);
        return ResponseEntity.ok(rides);
    }
    
    @GetMapping("/search/available")
    public ResponseEntity<List<Ride>> searchAvailableRides(
            @RequestParam String depart,
            @RequestParam String arrivee,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date,
            @RequestParam Integer places) {
        List<Ride> rides = rideService.searchAvailableRides(depart, arrivee, date, places);
        return ResponseEntity.ok(rides);
    }
    
    @GetMapping("/driver/{driverId}")
    public ResponseEntity<List<Ride>> getRidesByDriver(@PathVariable Long driverId) {
        return ResponseEntity.ok(rideService.getRidesByDriver(driverId));
    }
}
