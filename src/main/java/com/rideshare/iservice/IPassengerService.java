package com.rideshare.iservice;

import com.rideshare.entity.Passenger;
import java.util.List;

public interface IPassengerService {
    Passenger createPassenger(Passenger passenger);
    Passenger updatePassenger(Long id, Passenger passenger);
    Passenger getPassengerById(Long id);
    List<Passenger> getAllPassengers();
    void deletePassenger(Long id);
}
