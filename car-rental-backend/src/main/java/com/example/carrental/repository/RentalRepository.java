package com.example.carrental.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.carrental.model.Rental;

import java.util.List;

public interface RentalRepository extends MongoRepository<Rental, String> {
    List<Rental> findByRentalEndDateIsNull();
}
