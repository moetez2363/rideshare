package com.rideshare.iservice;

import com.rideshare.entity.Driver;
import java.util.List;

public interface IDriverService {
    Driver createDriver(Driver driver);
    Driver updateDriver(Long id, Driver driver);
    Driver getDriverById(Long id);
    List<Driver> getAllDrivers();
    void deleteDriver(Long id);
    List<Driver> getTopRatedDrivers(Double minRating);
}
