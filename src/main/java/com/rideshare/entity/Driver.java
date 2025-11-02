package com.rideshare.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.List;

@Entity
@Table(name = "drivers")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Driver {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Le nom est obligatoire")
    @Size(min = 2, max = 100)
    private String nom;
    
    @NotBlank(message = "L'email est obligatoire")
    @Email(message = "Format d'email invalide")
    @Column(unique = true)
    private String email;
    
    @NotBlank(message = "Le téléphone est obligatoire")
    @Pattern(regexp = "^[0-9]{8,15}$", message = "Numéro de téléphone invalide")
    private String telephone;
    
    @Min(value = 0, message = "Les années d'expérience doivent être positives")
    @Max(value = 50)
    private Integer anneesExperience;
    
    @DecimalMin(value = "0.0")
    @DecimalMax(value = "5.0")
    private Double noteMoyenne = 0.0;
    
    @OneToOne(mappedBy = "driver", cascade = CascadeType.ALL)
    @JsonManagedReference("driver-vehicle")
    private Vehicle vehicle;
    
    @OneToMany(mappedBy = "driver", cascade = CascadeType.ALL)
    @JsonManagedReference("driver-rides")
    private List<Ride> rides;
}
