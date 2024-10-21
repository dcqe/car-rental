// create_indexes.js

// Switch to the appropriate database
//use car_rental_db;
db = connect( 'mongodb://localhost/car_rental_db' );

// Customers Collection Indexes
db.customers.createIndex({ email: 1 }, { unique: true });
db.customers.createIndex({ name: 1 });

// Cars Collection Indexes
db.cars.createIndex({ licensePlate: 1 }, { unique: true });
db.cars.createIndex({ make: 1 });
db.cars.createIndex({ model: 1 });
db.cars.createIndex({ available: 1 });

// Rentals Collection Indexes
db.rentals.createIndex({ customerId: 1 });
db.rentals.createIndex({ carId: 1 });
db.rentals.createIndex({ rentalStartDate: 1 });
db.rentals.createIndex({ rentalEndDate: 1 });
db.rentals.createIndex({ carId: 1, rentalEndDate: 1 }, { name: "rental_carid_enddate_idx" });

// Confirmation Messages
print("Indexes created successfully.");

print(db.customers.getIndexes());
print(db.cars.getIndexes());
print(db.rentals.getIndexes());