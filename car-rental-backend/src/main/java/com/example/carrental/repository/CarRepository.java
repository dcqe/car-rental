package com.example.carrental.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.carrental.model.Car;

public interface CarRepository extends MongoRepository<Car, String> {
    // Additional query methods (if needed)
}
