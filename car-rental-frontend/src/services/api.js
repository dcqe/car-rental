import axios from 'axios';

const API_URL = 'http://localhost:8080/api';

export default {
  // Customer APIs
  getCustomers() {
    return axios.get(`${API_URL}/customers`);
  },
  getCustomer(id) {
    return axios.get(`${API_URL}/customers/${id}`);
  },
  createCustomer(customer) {
    return axios.post(`${API_URL}/customers`, customer);
  },
  updateCustomer(id, customer) {
    return axios.put(`${API_URL}/customers/${id}`, customer);
  },
  deleteCustomer(id) {
    return axios.delete(`${API_URL}/customers/${id}`);
  },

  // Car APIs
  getCars() {
    return axios.get(`${API_URL}/cars`);
  },
  getCar(id) {
    return axios.get(`${API_URL}/cars/${id}`);
  },
  createCar(car) {
    return axios.post(`${API_URL}/cars`, car);
  },
  updateCar(id, car) {
    return axios.put(`${API_URL}/cars/${id}`, car);
  },
  deleteCar(id) {
    return axios.delete(`${API_URL}/cars/${id}`);
  },

  // Rental APIs
  getRentals() {
    return axios.get(`${API_URL}/rentals`);
  },
  getRental(id) {
    return axios.get(`${API_URL}/rentals/${id}`);
  },
  startRental(rental) {
    return axios.post(`${API_URL}/rentals/start`, rental);
  },
  endRental(id, rentalDetails) {
    return axios.post(`${API_URL}/rentals/end/${id}`, rentalDetails);
  },
  getOngoingRentals() {
    return axios.get(`${API_URL}/rentals/ongoing`);
  }
};
