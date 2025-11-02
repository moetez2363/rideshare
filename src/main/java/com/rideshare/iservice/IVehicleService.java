package com.rideshare.iservice;

import com.rideshare.entity.Vehicle;
import java.util.List;

public interface IVehicleService {
    Vehicle createVehicle(Vehicle vehicle, Long driverId);
    Vehicle updateVehicle(Long id, Vehicle vehicle);
    Vehicle getVehicleById(Long id);
    List<Vehicle> getAllVehicles();
    void deleteVehicle(Long id);
    List<Vehicle> getVehiclesByMarque(String marque);
}
