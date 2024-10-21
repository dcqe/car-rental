# Car Rental Service Application


## Build and Run with Docker (Preferred Method)

### Clone the Repository
```bash
git clone https://github.com/dcqe/car-rental-project.git
cd car-rental-project
```

### Build and Run with Docker Compose

1. **Build and Start the Containers**

   Run the following command to build the Docker images and start all services:

```bash
docker-compose up --build -d
```

2. **Access the Application**
    * **Frontend Application**: Open your web browser and navigate to http://localhost:8081.
    * **Backend API**: Accessible at http://localhost:8080/api.

3. **Adding Data (Optional)**

   Execute the following script to reset the database and add initial values:
```bash
node car-rental-frontend/db_scripts/init_database.js
```
Execute the following script to just clear the database:
```bash
node car-rental-frontend/db_scripts/wipe_database.js
```

4. **Stopping the Application**

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

* **MongoDB**: Exposed on port `27017`.
* **Backend**: Exposed on port `8080`.
* **Frontend**: Exposed on port `8081`.


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

## Useful Docker Commands

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

---

## Alternative: Running Without Docker

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