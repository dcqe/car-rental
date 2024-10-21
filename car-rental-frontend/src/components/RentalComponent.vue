<template>
  <v-container>
    <h1>Rentals</h1>
    <v-btn color="primary" @click="openStartRentalDialog">Start Rental</v-btn>

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
      <template v-slot:item.rentalEndDate="{ item }">
        {{ item.rentalEndDate ? formatDate(item.rentalEndDate) : 'Ongoing' }}
      </template>
      <template v-slot:item.actions="{ item }">
        <v-btn v-if="!item.rentalEndDate" color="primary" @click="endRental(item)">
          End Rental
        </v-btn>
      </template>
    </v-data-table>

    <!-- Start Rental Dialog -->
    <v-dialog v-model="startRentalDialog" max-width="500px">
      <v-card>
        <v-card-title>
          <span class="headline">Start Rental</span>
        </v-card-title>
        <v-card-text>
          <v-form ref="formStart">
            <v-select v-model="rental.customerId" :items="customerOptions" label="Customer" required></v-select>
            <v-select v-model="rental.carId" :items="availableCarOptions" label="Car" required></v-select>
          </v-form>
        </v-card-text>
        <v-card-actions>
          <v-btn text @click="closeStartRentalDialog">Cancel</v-btn>
          <v-spacer></v-spacer>
          <v-btn color="primary" text @click="startRental">Start</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>

    <!-- End Rental Dialog -->
    <v-dialog v-model="endRentalDialog" max-width="500px">
      <v-card>
        <v-card-title>
          <span class="headline">End Rental</span>
        </v-card-title>
        <v-card-text>
          <v-form ref="formEnd">
            <v-text-field v-model="rentalDetails.kilometersDriven" label="Kilometers Driven" type="number" required></v-text-field>
          </v-form>
        </v-card-text>
        <v-card-actions>
          <v-btn text @click="closeEndRentalDialog">Cancel</v-btn>
          <v-spacer></v-spacer>
          <v-btn color="primary" text @click="confirmEndRental">End</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>

  </v-container>
</template>

<script>
import api from '../services/api';

export default {
  name: 'RentalComponent',
  data() {
    return {
      rentals: [],
      headers: [
        { text: 'Customer', value: 'customer' },
        { text: 'Car', value: 'car' },
        { text: 'Start Date', value: 'rentalStartDate' },
        { text: 'End Date', value: 'rentalEndDate' },
        { text: 'Actions', value: 'actions', sortable: false }
      ],
      startRentalDialog: false,
      endRentalDialog: false,
      rental: {},
      rentalDetails: {},
      selectedRental: null,
      customerOptions: [],
      availableCarOptions: []
    };
  },
  created() {
    this.fetchRentals();
    this.fetchCustomers();
    this.fetchAvailableCars();
  },
  methods: {
    async fetchRentals() {
      try {
        const response = await api.getRentals();
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
        console.error('Error fetching rentals:', error);
      }
    },
    async fetchCustomers() {
      const response = await api.getCustomers();
      this.customerOptions = response.data.map(customer => ({
        text: customer.name,
        value: customer.id
      }));
    },
    async fetchAvailableCars() {
      const response = await api.getCars();
      const availableCars = response.data.filter(car => car.available);
      this.availableCarOptions = availableCars.map(car => ({
        text: `${car.make} ${car.model}`,
        value: car.id
      }));
    },
    openStartRentalDialog() {
      this.startRentalDialog = true;
      this.rental = {};
    },
    closeStartRentalDialog() {
      this.startRentalDialog = false;
      this.rental = {};
    },
    startRental() {
      api.startRental(this.rental).then(() => {
        this.fetchRentals();
        this.fetchAvailableCars();
        this.closeStartRentalDialog();
      });
    },
    endRental(rental) {
      this.selectedRental = rental;
      this.endRentalDialog = true;
      this.rentalDetails = {};
    },
    closeEndRentalDialog() {
      this.endRentalDialog = false;
      this.selectedRental = null;
      this.rentalDetails = {};
    },
    confirmEndRental() {
      api.endRental(this.selectedRental.id, this.rentalDetails).then(() => {
        this.fetchRentals();
        this.fetchAvailableCars();
        this.closeEndRentalDialog();
      });
    },
    formatDate(dateString) {
      return new Date(dateString).toLocaleString();
    }
  }
};
</script>
