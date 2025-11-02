package com.rideshare.repository;

import com.rideshare.entity.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {
    Optional<Driver> findByEmail(String email);
    List<Driver> findByNoteMoyenneGreaterThanEqual(Double note);
    boolean existsByEmail(String email);
}