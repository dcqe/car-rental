package com.example.carrental.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "rentals")
public class Rental {

    @Id
    private String id;

    private String customerId;
    private String carId;
    private Date rentalStartDate;
    private Date rentalEndDate;
    private double kilometersDriven;

    // Constructors
    public Rental() {}

    public Rental(String customerId, String carId) {
        this.customerId = customerId;
        this.carId = carId;
        this.rentalStartDate = new Date();
    }

    // Getters and Setters

    // Getters
    public String getId() {
        return id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getCarId() {
        return carId;
    }

    public Date getRentalStartDate() {
        return rentalStartDate;
    }

    public Date getRentalEndDate() {
        return rentalEndDate;
    }

    public double getKilometersDriven() {
        return kilometersDriven;
    }

    // Setters
    public void setId(String id) {
        this.id = id;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public void setRentalStartDate(Date rentalStartDate) {
        this.rentalStartDate = rentalStartDate;
    }

    public void setRentalEndDate(Date rentalEndDate) {
        this.rentalEndDate = rentalEndDate;
    }

    public void setKilometersDriven(double kilometersDriven) {
        this.kilometersDriven = kilometersDriven;
    }
}
