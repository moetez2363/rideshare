package com.rideshare;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 🚗 RideShare Platform - Application de Covoiturage Urbain
 * 
 * Cette application permet de :
 * - Gérer les conducteurs et leurs véhicules
 * - Gérer les passagers
 * - Créer et rechercher des trajets
 * - Effectuer des réservations
 * 
 * @author Moetez
 * @version 1.0.0
 */
@SpringBootApplication
public class RideShareApplication {

    public static void main(String[] args) {
        SpringApplication.run(RideShareApplication.class, args);
        
        System.out.println("\n" +
            "╔════════════════════════════════════════════╗\n" +
            "║   🚗 RideShare Platform Started! 🚗       ║\n" +
            "║                                            ║\n" +
            "║   API: http://localhost:8080               ║\n" +
            "║   H2 Console: http://localhost:8080/h2-console ║\n" +
            "║                                            ║\n" +
            "║   JDBC URL: jdbc:h2:mem:ridesharedb        ║\n" +
            "║   Username: sa                             ║\n" +
            "║   Password: (vide)                         ║\n" +
            "╚════════════════════════════════════════════╝\n"
        );
    }
}