const { MongoClient } = require('mongodb');

const uri = "mongodb://localhost:27017/car_rental_db";

async function wipeAndSeedDatabase() {
    const client = new MongoClient(uri);

    try {
        await client.connect();
        const db = client.db();

        // Drop the database
        await db.dropDatabase();

        // Insert new values
        const collection = db.collection("yourCollection");
        await collection.insertMany([
            { field1: "value1", field2: "value2" },
            { field1: "value3", field2: "value4" }
        ]);

        console.log("Database wiped and initialized with sample data!");
    } catch (e) {
        console.error(e);
    } finally {
        await client.close();
    }
}

wipeAndSeedDatabase();
