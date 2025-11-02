package com.rideshare.service;

import com.rideshare.entity.Driver;
import com.rideshare.entity.Vehicle;
import com.rideshare.iservice.IVehicleService;
import com.rideshare.repository.DriverRepository;
import com.rideshare.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class VehicleServiceImpl implements IVehicleService {
    
    private final VehicleRepository vehicleRepository;
    private final DriverRepository driverRepository;
    
    @Override
    public Vehicle createVehicle(Vehicle vehicle, Long driverId) {
        if (vehicleRepository.existsByPlaque(vehicle.getPlaque())) {
            throw new RuntimeException("Un véhicule avec cette plaque existe déjà");
        }
        
        Driver driver = driverRepository.findById(driverId)
            .orElseThrow(() -> new RuntimeException("Conducteur non trouvé"));
        
        if (driver.getVehicle() != null) {
            throw new RuntimeException("Ce conducteur a déjà un véhicule associé");
        }
        
        vehicle.setDriver(driver);
        return vehicleRepository.save(vehicle);
    }
    
    @Override
    public Vehicle updateVehicle(Long id, Vehicle vehicle) {
        Vehicle existingVehicle = getVehicleById(id);
        existingVehicle.setMarque(vehicle.getMarque());
        existingVehicle.setModele(vehicle.getModele());
        existingVehicle.setCouleur(vehicle.getCouleur());
        existingVehicle.setNombrePlaces(vehicle.getNombrePlaces());
        existingVehicle.setAnnee(vehicle.getAnnee());
        return vehicleRepository.save(existingVehicle);
    }
    
    @Override
    public Vehicle getVehicleById(Long id) {
        return vehicleRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Véhicule non trouvé avec l'ID: " + id));
    }
    
    @Override
    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }
    
    @Override
    public void deleteVehicle(Long id) {
        vehicleRepository.deleteById(id);
    }
    
    @Override
    public List<Vehicle> getVehiclesByMarque(String marque) {
        return vehicleRepository.findByMarque(marque);
    }
}