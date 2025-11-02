package com.rideshare.service;

import com.rideshare.entity.Passenger;
import com.rideshare.iservice.IPassengerService;
import com.rideshare.repository.PassengerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PassengerServiceImpl implements IPassengerService {
    
    private final PassengerRepository passengerRepository;
    
    @Override
    public Passenger createPassenger(Passenger passenger) {
        if (passengerRepository.existsByEmail(passenger.getEmail())) {
            throw new RuntimeException("Un passager avec cet email existe déjà");
        }
        passenger.setNoteMoyenne(0.0);
        return passengerRepository.save(passenger);
    }
    
    @Override
    public Passenger updatePassenger(Long id, Passenger passenger) {
        Passenger existingPassenger = getPassengerById(id);
        existingPassenger.setNom(passenger.getNom());
        existingPassenger.setTelephone(passenger.getTelephone());
        existingPassenger.setPreferences(passenger.getPreferences());
        return passengerRepository.save(existingPassenger);
    }
    
    @Override
    public Passenger getPassengerById(Long id) {
        return passengerRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Passager non trouvé avec l'ID: " + id));
    }
    
    @Override
    public List<Passenger> getAllPassengers() {
        return passengerRepository.findAll();
    }
    
    @Override
    public void deletePassenger(Long id) {
        if (!passengerRepository.existsById(id)) {
            throw new RuntimeException("Passager non trouvé avec l'ID: " + id);
        }
        passengerRepository.deleteById(id);
    }
}
