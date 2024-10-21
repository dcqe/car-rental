package com.example.carrental.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.validation.constraints.NotBlank;

@Document(collection = "cars")
public class Car {

    @Id
    private String id;

    @NotBlank(message = "Make is mandatory")
    private String make;

    @NotBlank(message = "Model is mandatory")
    private String model;

    private int year;

    @NotBlank(message = "License Plate is mandatory")
    private String licensePlate;

    private boolean available = true;

    private double totalKilometersDriven = 0.0; // New field

    // Constructors
    public Car() {}

    public Car(String make, String model, int year, String licensePlate) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.licensePlate = licensePlate;
        this.available = true;
        this.totalKilometersDriven = 0.0;
    }

    // Getters and Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public double getTotalKilometersDriven() {
        return totalKilometersDriven;
    }

    public void setTotalKilometersDriven(double totalKilometersDriven) {
        this.totalKilometersDriven = totalKilometersDriven;
    }

    public void addKilometersDriven(double kilometers) {
        this.totalKilometersDriven += kilometers;
    }
}
