package com.example.carrental.controller;

import com.example.carrental.model.Car;
import com.example.carrental.repository.CarRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/cars")
@CrossOrigin(origins = "*") // For CORS policy, adjust as needed
public class CarController {

    @Autowired
    private CarRepository carRepository;

    // Get all cars
    @GetMapping
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    // Get a car by ID
    @GetMapping("/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable String id) {
        return carRepository.findById(id)
                .map(car -> ResponseEntity.ok().body(car))
                .orElse(ResponseEntity.notFound().build());
    }

    // Create a new car
    @PostMapping
    public ResponseEntity<Car> createCar(@Valid @RequestBody Car car) {
        Car savedCar = carRepository.save(car);
        return ResponseEntity.ok(savedCar);
    }

    // Update an existing car
    @PutMapping("/{id}")
    public ResponseEntity<Car> updateCar(@PathVariable String id,
                                         @Valid @RequestBody Car carDetails) {
        return carRepository.findById(id)
                .map(car -> {
                    car.setMake(carDetails.getMake());
                    car.setModel(carDetails.getModel());
                    car.setYear(carDetails.getYear());
                    car.setLicensePlate(carDetails.getLicensePlate());
                    // Note: totalKilometersDriven should not be updated here
                    Car updatedCar = carRepository.save(car);
                    return ResponseEntity.ok().body(updatedCar);
                }).orElse(ResponseEntity.notFound().build());
    }

    // Delete a car
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCar(@PathVariable String id) {
        return carRepository.findById(id)
                .map(car -> {
                    carRepository.delete(car);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }

    // Optional: Get total kilometers driven for a specific car
    @GetMapping("/{id}/kilometers")
    public ResponseEntity<Double> getTotalKilometers(@PathVariable String id) {
        return carRepository.findById(id)
                .map(car -> ResponseEntity.ok().body(car.getTotalKilometersDriven()))
                .orElse(ResponseEntity.notFound().build());
    }
}
