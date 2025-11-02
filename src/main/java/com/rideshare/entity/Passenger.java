package com.rideshare.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.List;

@Entity
@Table(name = "passengers")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Passenger {
    
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
    
    private String preferences; // Ex: "Non-fumeur, Bavard, Musique"
    
    @DecimalMin(value = "0.0")
    @DecimalMax(value = "5.0")
    private Double noteMoyenne = 0.0;
    
   @OneToMany(mappedBy = "passenger", cascade = CascadeType.ALL)
   @JsonManagedReference("passenger-bookings")  // ← Match the name
   private List<Booking> bookings;
}