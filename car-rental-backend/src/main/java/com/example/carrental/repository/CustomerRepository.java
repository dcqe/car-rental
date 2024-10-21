package com.example.carrental.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.carrental.model.Customer;

public interface CustomerRepository extends MongoRepository<Customer, String> {
    // Additional query methods (if needed)
}
