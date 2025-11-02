package com.rideshare.repository;

import com.rideshare.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    Optional<Vehicle> findByPlaque(String plaque);
    List<Vehicle> findByMarque(String marque);
    boolean existsByPlaque(String plaque);
}
