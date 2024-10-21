package com.example.carrental.controller;

import com.example.carrental.model.Car;
import com.example.carrental.model.Customer;
import com.example.carrental.model.Rental;
import com.example.carrental.repository.CarRepository;
import com.example.carrental.repository.CustomerRepository;
import com.example.carrental.repository.RentalRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/rentals")
@CrossOrigin(origins = "*") // Adjust origins as needed for CORS policy
public class RentalController {

    @Autowired
    private RentalRepository rentalRepository;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private CustomerRepository customerRepository;

    // Get all rentals
    @GetMapping
    public List<Rental> getAllRentals() {
        return rentalRepository.findAll();
    }

    // Get ongoing rentals
    @GetMapping("/ongoing")
    public List<Rental> getOngoingRentals() {
        return rentalRepository.findByRentalEndDateIsNull();
    }

    // Start a new rental
    @PostMapping("/start")
    public ResponseEntity<?> startRental(@Valid @RequestBody Rental rental) {
        // Validate customer
        Customer customer = customerRepository.findById(rental.getCustomerId())
                .orElse(null);
        if (customer == null) {
            return ResponseEntity.badRequest().body("Invalid customer ID");
        }

        // Validate car
        Car car = carRepository.findById(rental.getCarId())
                .orElse(null);
        if (car == null) {
            return ResponseEntity.badRequest().body("Invalid car ID");
        }
        if (!car.isAvailable()) {
            return ResponseEntity.badRequest().body("Car is not available");
        }

        // Start rental
        car.setAvailable(false);
        carRepository.save(car);
        rental.setRentalStartDate(new Date());
        Rental newRental = rentalRepository.save(rental);

        return ResponseEntity.ok().body(newRental);
    }

    // End a rental
    @PostMapping("/end/{id}")
    public ResponseEntity<?> endRental(@PathVariable String id,
                                       @Valid @RequestBody Rental rentalDetails) {
        return rentalRepository.findById(id)
                .map(rental -> {
                    if (rental.getRentalEndDate() != null) {
                        return ResponseEntity.badRequest().body("Rental already ended");
                    }
                    rental.setRentalEndDate(new Date());
                    rental.setKilometersDriven(rentalDetails.getKilometersDriven());
                    rentalRepository.save(rental);

                    // Update car availability
                    Car car = carRepository.findById(rental.getCarId()).orElse(null);
                    if (car != null) {
                        car.setAvailable(true);
                        carRepository.save(car);
                    }

                    return ResponseEntity.ok().body(rental);
                }).orElse(ResponseEntity.notFound().build());
    }

    // Get a rental by ID
    @GetMapping("/{id}")
    public ResponseEntity<Rental> getRentalById(@PathVariable String id) {
        return rentalRepository.findById(id)
                .map(rental -> ResponseEntity.ok().body(rental))
                .orElse(ResponseEntity.notFound().build());
    }

    // Delete a rental
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRental(@PathVariable String id) {
        return rentalRepository.findById(id)
                .map(rental -> {
                    rentalRepository.delete(rental);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
