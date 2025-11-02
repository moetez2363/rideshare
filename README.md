# 🚗 RideShare Platform

Application de covoiturage urbain développée avec Spring Boot.

## 📋 Description

Permet aux conducteurs de proposer des trajets et aux passagers de réserver des places.

## 🛠️ Technologies

- Java 17
- Spring Boot 3.2.0
- Spring Data JPA
- H2 Database
- Maven

## ⚙️ Installation
```bash
git clone https://github.com/moetez2363/rideshare.git
cd rideshare
mvn clean install
mvn spring-boot:run
```

## 🌐 Accès

- API : http://localhost:8081
- H2 Console : http://localhost:8081/h2-console
  - JDBC URL: `jdbc:h2:mem:ridesharedb`
  - Username: `sa`
  - Password: (vide)

## 📡 Endpoints Principaux

| Entité | POST | GET |
|--------|------|-----|
| Drivers | `/api/drivers` | `/api/drivers` |
| Passengers | `/api/passengers` | `/api/passengers` |
| Vehicles | `/api/vehicles/driver/{id}` | `/api/vehicles` |
| Rides | `/api/rides/driver/{id}` | `/api/rides` |
| Bookings | `/api/bookings/ride/{id}/passenger/{id}` | `/api/bookings` |

## 🗂️ Entités

- **Driver** : Conducteur avec véhicule
- **Passenger** : Passager
- **Vehicle** : Véhicule du conducteur
- **Ride** : Trajet proposé
- **Booking** : Réservation d'un trajet

## 👨‍💻 Auteur

**Moetez Bakr Hana **  
Projet DS1 - Spring Boot  
Enseignant : Med Amine Laribi

---

© 2025 RideShare Platform
