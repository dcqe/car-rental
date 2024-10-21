<template>
  <v-container>
    <h1>Ongoing Rentals</h1>
    <v-data-table :headers="headers" :items="rentals" class="elevation-1">
      <template v-slot:item.customer="{ item }">
        {{ item.customer.name }}
      </template>
      <template v-slot:item.car="{ item }">
        {{ item.car.make }} {{ item.car.model }}
      </template>
      <template v-slot:item.rentalStartDate="{ item }">
        {{ formatDate(item.rentalStartDate) }}
      </template>
    </v-data-table>
  </v-container>
</template>

<script>
import api from '../services/api';

export default {
  name: 'DashboardComponent',
  data() {
    return {
      rentals: [],
      headers: [
        { text: 'Customer', value: 'customer' },
        { text: 'Car', value: 'car' },
        { text: 'Start Date', value: 'rentalStartDate' }
      ]
    };
  },
  created() {
    this.fetchOngoingRentals();
  },
  methods: {
    async fetchOngoingRentals() {
      try {
        const response = await api.getOngoingRentals();
        const rentals = response.data;
        // Fetch customer and car details
        for (const rental of rentals) {
          const customerResponse = await api.getCustomer(rental.customerId);
          rental.customer = customerResponse.data;
          const carResponse = await api.getCar(rental.carId);
          rental.car = carResponse.data;
        }
        this.rentals = rentals;
      } catch (error) {
        console.error('Error fetching ongoing rentals:', error);
      }
    },
    formatDate(dateString) {
      return new Date(dateString).toLocaleString();
    }
  }
};
</script>
