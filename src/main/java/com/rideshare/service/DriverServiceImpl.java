package com.rideshare.service;

import com.rideshare.entity.Driver;
import com.rideshare.iservice.IDriverService;
import com.rideshare.repository.DriverRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class DriverServiceImpl implements IDriverService {
    
    private final DriverRepository driverRepository;
    
    @Override
    public Driver createDriver(Driver driver) {
        if (driverRepository.existsByEmail(driver.getEmail())) {
            throw new RuntimeException("Un conducteur avec cet email existe déjà");
        }
        driver.setNoteMoyenne(0.0);
        return driverRepository.save(driver);
    }
    
    @Override
    public Driver updateDriver(Long id, Driver driver) {
        Driver existingDriver = getDriverById(id);
        existingDriver.setNom(driver.getNom());
        existingDriver.setTelephone(driver.getTelephone());
        existingDriver.setAnneesExperience(driver.getAnneesExperience());
        // Email et note ne peuvent pas être modifiés ici
        return driverRepository.save(existingDriver);
    }
    
    @Override
    public Driver getDriverById(Long id) {
        return driverRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Conducteur non trouvé avec l'ID: " + id));
    }
    
    @Override
    public List<Driver> getAllDrivers() {
        return driverRepository.findAll();
    }
    
    @Override
    public void deleteDriver(Long id) {
        if (!driverRepository.existsById(id)) {
            throw new RuntimeException("Conducteur non trouvé avec l'ID: " + id);
        }
        driverRepository.deleteById(id);
    }
    
    @Override
    public List<Driver> getTopRatedDrivers(Double minRating) {
        return driverRepository.findByNoteMoyenneGreaterThanEqual(minRating);
    }
}
