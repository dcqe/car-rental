const { MongoClient, ObjectId } = require('mongodb');

const uri = "mongodb://localhost:27017/car_rental_db";

async function wipeAndSeedDatabase() {
    const client = new MongoClient(uri);

    try {
        await client.connect();
        const db = client.db();

        // Drop the database
        await db.dropDatabase();

        // Insert sample customers
        const customers = [
            {
                _id: new ObjectId(),
                name: "John Doe",
                email: "john.doe@example.com",
                phone: "+1 (555) 123-4567",
                address: "123 Main St, Anytown, USA"
            },
            {
                _id: new ObjectId(),
                name: "Jane Smith",
                email: "jane.smith@example.com",
                phone: "+1 (555) 987-6543",
                address: "456 Elm St, Othertown, USA"
            },
            {
                _id: new ObjectId(),
                name: "Alice Johnson",
                email: "alice.johnson@example.com",
                phone: "+1 (555) 555-1212",
                address: "789 Oak St, Sometown, USA"
            }
        ];

        const customerResult = await db.collection("customers").insertMany(customers);
        console.log(`Inserted ${customerResult.insertedCount} customers`);

        // Insert sample cars
        const cars = [
            {
                _id: new ObjectId(),
                make: "Toyota",
                model: "Camry",
                year: 2020,
                licensePlate: "ABC-1234",
                status: "Available"
            },
            {
                _id: new ObjectId(),
                make: "Honda",
                model: "Civic",
                year: 2019,
                licensePlate: "XYZ-5678",
                status: "Available"
            },
            {
                _id: new ObjectId(),
                make: "Ford",
                model: "Mustang",
                year: 2021,
                licensePlate: "DEF-9012",
                status: "Available"
            }
        ];

        const carResult = await db.collection("cars").insertMany(cars);
        console.log(`Inserted ${carResult.insertedCount} cars`);

        console.log("Database wiped and initialized with sample data!");
    } catch (e) {
        console.error(e);
    } finally {
        await client.close();
    }
}

wipeAndSeedDatabase();
