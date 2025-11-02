package com.rideshare.controller;

import com.rideshare.entity.Driver;
import com.rideshare.iservice.IDriverService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/drivers")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class DriverController {
    
    private final IDriverService driverService;
    
    /**
     * Créer un nouveau conducteur
     * POST /api/drivers
     */
    @PostMapping
    public ResponseEntity<Driver> createDriver(@Valid @RequestBody Driver driver) {
        try {
            Driver created = driverService.createDriver(driver);
            return new ResponseEntity<>(created, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
    
    /**
     * Obtenir tous les conducteurs
     * GET /api/drivers
     */
    @GetMapping
    public ResponseEntity<List<Driver>> getAllDrivers() {
        List<Driver> drivers = driverService.getAllDrivers();
        return ResponseEntity.ok(drivers);
    }
    
    /**
     * Obtenir un conducteur par ID
     * GET /api/drivers/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<Driver> getDriverById(@PathVariable Long id) {
        try {
            Driver driver = driverService.getDriverById(id);
            return ResponseEntity.ok(driver);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
    
    /**
     * Mettre à jour un conducteur
     * PUT /api/drivers/{id}
     */
    @PutMapping("/{id}")
    public ResponseEntity<Driver> updateDriver(@PathVariable Long id, 
                                               @Valid @RequestBody Driver driver) {
        try {
            Driver updated = driverService.updateDriver(id, driver);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
    
    /**
     * Supprimer un conducteur
     * DELETE /api/drivers/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDriver(@PathVariable Long id) {
        try {
            driverService.deleteDriver(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    /**
     * Obtenir les conducteurs bien notés
     * GET /api/drivers/top-rated?minRating=4.0
     */
    @GetMapping("/top-rated")
    public ResponseEntity<List<Driver>> getTopRatedDrivers(
            @RequestParam(defaultValue = "4.0") Double minRating) {
        List<Driver> drivers = driverService.getTopRatedDrivers(minRating);
        return ResponseEntity.ok(drivers);
    }
}
