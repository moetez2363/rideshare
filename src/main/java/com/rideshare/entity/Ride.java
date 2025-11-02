package com.rideshare.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "rides")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ride {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Le point de départ est obligatoire")
    private String villeDepart;
    
    @NotBlank(message = "Le point d'arrivée est obligatoire")
    private String villeArrivee;
    
    @NotNull(message = "La date de départ est obligatoire")
    @Future(message = "La date doit être dans le futur")
    private LocalDateTime dateHeureDepart;
    
    @DecimalMin(value = "0.0", message = "Le prix doit être positif")
    private Double prixParPlace;
    
    @Min(value = 1)
    private Integer placesDisponibles;
    
    private String pointRencontreDepart;
    private String pointRencontreArrivee;
    
    @ManyToOne
    @JoinColumn(name = "driver_id", nullable = false)
    @JsonBackReference("driver-rides")  // ← Match the name
    private Driver driver;

    @OneToMany(mappedBy = "ride", cascade = CascadeType.ALL)
    @JsonManagedReference("ride-bookings")  // ← Add name
    private List<Booking> bookings;
}
