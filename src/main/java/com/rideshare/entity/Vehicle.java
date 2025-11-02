package com.rideshare.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "vehicles")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Vehicle {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "La marque est obligatoire")
    private String marque;
    
    @NotBlank(message = "Le modèle est obligatoire")
    private String modele;
    
    @NotBlank(message = "La couleur est obligatoire")
    private String couleur;
    
    @NotBlank(message = "La plaque d'immatriculation est obligatoire")
    @Column(unique = true)
    private String plaque;
    
    @Min(value = 1, message = "Le nombre de places doit être au moins 1")
    @Max(value = 8, message = "Maximum 8 places")
    private Integer nombrePlaces;
    
    @Min(value = 1990)
    @Max(value = 2025)
    private Integer annee;
    
   @OneToOne
   @JoinColumn(name = "driver_id")
   @JsonBackReference("driver-vehicle")  // ← Match the name
   private Driver driver;
}

