# Build and Run Instructions

## Prerequisites

### Backend

* **Java Development Kit (JDK)**: Version 11 or higher (e.g., Java 11 or Java 17)
* **Maven**: For building the Java project
* **MongoDB**: Running locally on your machine or accessible via network

### Frontend

* **Node.js**: Version 12 or higher
* **npm**: Node Package Manager (comes with Node.js)

---

## Instructions

1. **Clone the Repository**
```bash
git clone https://github.com/dcqe/car-rental-project.git
```

2. **Navigate to the Project Directory**
```bash
cd car-rental-project
```

3. **Open Two Terminals**

   You will need to run the backend and frontend applications simultaneously in separate terminals.

---

## Backend Instructions

*In the first terminal:*

1. **Navigate to the Backend Directory**
```bash
cd car-rental-backend
```

2. **Configure MongoDB Connection (If Necessary)**
    * The application expects MongoDB to be running at `mongodb://localhost:27017/car_rental_db`.
    * If your MongoDB instance is running elsewhere, update the `application.properties` file located at `src/main/resources/application.properties`:
```
spring.data.mongodb.uri=mongodb://your_mongodb_host:port/your_database_name
```

3. **Build the Backend Project**
```bash
mvn clean install
```

4. **Run the Backend Application**
```bash
mvn spring-boot:run
```
* The backend API will be running at `http://localhost:8080/api`.

---

## Frontend Instructions

*In the second terminal:*

1. **Navigate to the Frontend Directory**

   From the project root directory:
```bash
cd car-rental-frontend
```

2. **Install Frontend Dependencies**
```bash
npm install
```

3. **Configure API Base URL (If Necessary)**
    * The frontend expects the backend API to be running at `http://localhost:8080/api`.
    * If your backend is running at a different URL or port, update the `api.js` file located at `src/services/api.js`:
```javascript
const API_URL = 'http://your_backend_host:port/api';
```

4. **Run the Frontend Application**
```bash
npm run serve
```
* The frontend will be running at `http://localhost:8081`.

---

## Accessing the Application

* **Frontend URL**: Open your web browser and navigate to `http://localhost:8081`.
* **Backend API**: Accessible at `http://localhost:8080/api`.

---

## Additional Notes

* Ensure that both the backend and frontend applications are running simultaneously to use the application fully.
* If you encounter any issues related to CORS when making API requests from the frontend to the backend, ensure that the backend controllers have the appropriate `@CrossOrigin` annotations or configure CORS globally.

---

## Stopping the Applications

* **Backend**: Press `Ctrl + C` in the terminal where the backend application is running.
* **Frontend**: Press `Ctrl + C` in the terminal where the frontend application is running.

---

## Troubleshooting

* **Port Conflicts**: If ports `8080` (backend) or `8081` (frontend) are already in use, you can change the ports:
    * **Backend**: Modify the `server.port` property in `application.properties`.
    * **Frontend**: Modify the `port` value in `vue.config.js`.
* **MongoDB Connection Issues**: Ensure that MongoDB is running and accessible. Check your MongoDB connection string in `application.properties`.
* **Dependency Issues**:
    * For Maven dependencies, run `mvn clean install` to resolve them.
    * For npm packages, run `npm install` to install missing packages.

---

## Build and Run with Docker (Optional)

If you prefer using Docker, you can containerize both the backend and frontend applications.

### Prerequisites

* **Docker**: Installed on your machine
* **Docker Compose**: (Optional) For orchestrating multi-container applications

### Steps

1. **Navigate to the Project Directory**
```bash
cd car-rental-project
```

2. **Build Docker Images**
    * **Backend**:
```bash
docker build -t car-rental-backend ./car-rental-backend
```
* **Frontend**:
```bash
docker build -t car-rental-frontend ./car-rental-frontend
```

3. **Run Containers**
    * **Without Docker Compose**:
```bash
# Run MongoDB
docker run -d --name mongo -p 27017:27017 mongo

# Run Backend
docker run -d --name backend -p 8080:8080 --link mongo:mongo car-rental-backend

# Run Frontend
docker run -d --name frontend -p 8081:8081 car-rental-frontend
```

* **With Docker Compose**:

  Create a `docker-compose.yml` file in the project root:
```yaml
version: '3'
services:
  mongo:
    image: mongo
    ports:
      - "27017:27017"
  backend:
    build: ./car-rental-backend
    ports:
      - "8080:8080"
    depends_on:
      - mongo
  frontend:
    build: ./car-rental-frontend
    ports:
      - "8081:8081"
```

     Run the applications:
```bash
docker-compose up
```

---

## Contact

If you have any questions or need further assistance, please contact the project maintainer at your_email@example.com.

---