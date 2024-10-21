# Car Rental Service Application

## Introduction

Welcome to the **Car Rental Service Application**! This application is designed to manage car rentals, customers, and rental transactions. It consists of a backend API built with Spring Boot and a frontend application built with Vue.js and Vuetify.

This README provides instructions on how to build and run the application using **Docker** and **Docker Compose**. Using Docker simplifies the setup process by containerizing the application and its dependencies, ensuring a consistent environment across different systems.

---

## Prerequisites

* **Docker**: Installed on your machine
* **Docker Compose**: Comes bundled with Docker Desktop on Windows and macOS. For Linux, you may need to install it separately.

---

## Build and Run Instructions (Preferred Method)

### Clone the Repository
```bash
git clone https://github.com/dcqe/car-rental-project.git
```

### Navigate to the Project Directory
```bash
cd car-rental-project
```

### Build and Run with Docker Compose

1. **Build and Start the Containers**

   Run the following command to build the Docker images and start all services:
```bash
docker-compose up --build
```
* The `--build` flag forces a rebuild of the Docker images.
* This command will:
    * Build the Docker images for the backend, frontend, and MongoDB services.
    * Start the containers and link them together using a Docker network.

2. **Access the Application**
    * **Frontend Application**: Open your web browser and navigate to http://localhost:8081.
    * **Backend API**: Accessible at http://localhost:8080/api.

3. **Stopping the Application**

   To stop the running containers, press `Ctrl + C` in the terminal where `docker-compose` is running, or run:
```bash
docker-compose down
```

---

## Application Details

### Services Overview

* **MongoDB**: Database service used to store application data.
* **Backend**: Spring Boot application providing RESTful APIs.
* **Frontend**: Vue.js application providing the user interface.

### Ports Used

* **MongoDB**: Exposed on port `27017` (not typically accessed directly).
* **Backend**: Exposed on port `8080`.
* **Frontend**: Exposed on port `8081`.

### Docker Compose File

The `docker-compose.yml` file orchestrates the services required for the application.

```yaml
version: '3.8'

services:
  mongo:
    image: mongo:latest
    container_name: mongo
    ports:
      - "27017:27017"
    volumes:
      - mongo-data:/data/db

  backend:
    build: ./car-rental-backend
    container_name: car-rental-backend
    ports:
      - "8080:8080"
    depends_on:
      - mongo
    environment:
      - SPRING_DATA_MONGODB_URI=mongodb://mongo:27017/car_rental_db

  frontend:
    build: ./car-rental-frontend
    container_name: car-rental-frontend
    ports:
      - "8081:80"
    depends_on:
      - backend

volumes:
  mongo-data:
```

---

## Configuration

### Environment Variables

* **MongoDB Connection URI**: The backend connects to MongoDB using the environment variable `SPRING_DATA_MONGODB_URI`. This is set in the `docker-compose.yml` file.
```yaml
environment:
  - SPRING_DATA_MONGODB_URI=mongodb://mongo:27017/car_rental_db
```

* **Frontend API Base URL**: The frontend expects the backend API to be running at `http://localhost:8080/api`. If you change the backend port, update the `API_URL` in `car-rental-frontend/src/services/api.js`.
```javascript
const API_URL = 'http://localhost:8080/api';
```

### Modifying Ports (If Necessary)

If the default ports (`8080` for backend, `8081` for frontend) are already in use, you can change them in the `docker-compose.yml` file under the `ports` section.

Example:
```yaml
backend:
  ports:
    - "9090:8080"  # Maps container's port 8080 to host's port 9090
```

---

## Additional Docker Commands

* **Running in Detached Mode**: To run the containers in the background:
```bash
docker-compose up --build -d
```

* **Viewing Logs**: To view logs from all services:
```bash
docker-compose logs -f
```

* **Rebuilding a Specific Service**:
```bash
docker-compose build backend
# Then restart the service:
docker-compose up backend
```

---

## Troubleshooting

### Common Issues

#### Containers Not Starting
* **Symptom**: Containers fail to start or exit immediately.
* **Solution**:
    * Check the logs for errors:
```bash
docker-compose logs
```
* Ensure that all services are properly defined and that there are no syntax errors in the `docker-compose.yml` file.

#### Port Conflicts
* **Symptom**: Error messages about ports already being in use.
* **Solution**:
    * Modify the ports in the `docker-compose.yml` file.
    * Example: Change `8080:8080` to `9090:8080` for the backend.

#### MongoDB Connection Issues
* **Symptom**: Backend cannot connect to MongoDB.
* **Solution**:
    * Ensure the `SPRING_DATA_MONGODB_URI` is correctly set to `mongodb://mongo:27017/car_rental_db`.
    * The hostname `mongo` refers to the MongoDB service within the Docker network.

#### Frontend API Errors
* **Symptom**: Frontend cannot communicate with the backend API.
* **Solution**:
    * Ensure the backend is running and accessible at the expected URL.
    * Verify the `API_URL` in `car-rental-frontend/src/services/api.js`.

### Clearing Docker Resources

If you encounter issues that might be related to cached Docker resources, you can prune unused images, containers, and volumes:

```bash
# Remove stopped containers
docker container prune

# Remove unused images
docker image prune

# Remove unused volumes
docker volume prune
```

**Warning**: Be cautious when pruning resources as it can delete data you might need.

---

## Optional: Running Without Docker

If you prefer to run the application without Docker, follow these steps.

### Prerequisites

#### Backend
* **Java Development Kit (JDK)**: Version 11 or higher
* **Maven**

#### Frontend
* **Node.js**: Version 12 or higher
* **npm**

#### Database
* **MongoDB**: Installed and running locally

---

### Backend Instructions

1. **Start MongoDB**

   Ensure MongoDB is running locally on port `27017`.

2. **Navigate to the Backend Directory**
```bash
cd car-rental-backend
```

3. **Configure MongoDB Connection**

   Update `src/main/resources/application.properties` if your MongoDB is not running on `localhost:27017`.
```properties
spring.data.mongodb.uri=mongodb://localhost:27017/car_rental_db
```

4. **Build and Run the Backend Application**
```bash
mvn clean install
mvn spring-boot:run
```
* The backend API will be running at `http://localhost:8080/api`.

---

### Frontend Instructions

1. **Navigate to the Frontend Directory**
```bash
cd car-rental-frontend
```

2. **Install Dependencies**
```bash
npm install
```

3. **Configure API Base URL**

   If the backend API URL differs from `http://localhost:8080/api`, update `src/services/api.js`:
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

* **Frontend URL**: Open your web browser and navigate to http://localhost:8081.
* **Backend API**: Accessible at http://localhost:8080/api.

---

## Stopping the Applications

* **Backend**: Press `Ctrl + C` in the terminal where the backend application is running.
* **Frontend**: Press `Ctrl + C` in the terminal where the frontend application is running.

---

## Additional Notes

* **Ensure Simultaneous Execution**: Both the backend and frontend applications need to be running simultaneously to use the application fully.
* **CORS Issues**: If you encounter any issues related to Cross-Origin Resource Sharing (CORS) when making API requests from the frontend to the backend, ensure that the backend controllers have the appropriate `@CrossOrigin` annotations or configure CORS globally.
* **Dependency Management**:
    * **Backend**: If you encounter issues with Maven dependencies, run `mvn clean install` to resolve them.
    * **Frontend**: If you encounter issues with npm packages, run `npm install` to install missing packages.

---

## Contact

If you have any questions or need further assistance, please contact the project maintainer at your_email@example.com.

---

## License

This project is licensed under the [MIT License](LICENSE).

---

## Acknowledgments

* Thanks to all contributors and open-source projects that made this application possible.

---

## Conclusion

Using Docker simplifies the setup and deployment process of the Car Rental Service Application. By following the instructions provided, you should be able to run the application seamlessly. If you prefer a traditional setup, instructions are also provided for running the application without Docker.

---