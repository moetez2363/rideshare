package com.rideshare.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import java.time.LocalDateTime;

@Entity
@Table(name = "bookings")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Booking {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "ride_id", nullable = false)
    @JsonBackReference
    private Ride ride;
    
    @ManyToOne
    @JoinColumn(name = "passenger_id", nullable = false)
    @JsonBackReference
    private Passenger passenger;
    
    @Min(value = 1, message = "Au moins 1 place doit être réservée")
    private Integer nombrePlacesReservees;
    
    @Enumerated(EnumType.STRING)
    private BookingStatus statut = BookingStatus.EN_ATTENTE;
    
    private LocalDateTime dateReservation = LocalDateTime.now();
    
    private Double montantTotal;
}
