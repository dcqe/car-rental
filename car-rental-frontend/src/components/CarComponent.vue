<template>
  <v-container>
    <h1>Cars</h1>
    <v-btn color="primary" @click="openDialog">Add Car</v-btn>

    <v-data-table :headers="headers" :items="cars" class="elevation-1">
      <template v-slot:item.available="{ item }">
        <v-chip :color="item.available ? 'green' : 'red'" dark>
          {{ item.available ? 'Yes' : 'No' }}
        </v-chip>
      </template>
      <template v-slot:item.actions="{ item }">
        <v-btn icon @click="editCar(item)">
          <v-icon>mdi-pencil</v-icon>
        </v-btn>
        <v-btn icon @click="deleteCar(item.id)">
          <v-icon>mdi-delete</v-icon>
        </v-btn>
      </template>
    </v-data-table>

    <!-- Add/Edit Car Dialog -->
    <v-dialog v-model="dialog" max-width="500px">
      <v-card>
        <v-card-title>
          <span class="headline">{{ formTitle }}</span>
        </v-card-title>
        <v-card-text>
          <v-form ref="form">
            <v-text-field v-model="car.make" label="Make" required></v-text-field>
            <v-text-field v-model="car.model" label="Model" required></v-text-field>
            <v-text-field v-model="car.year" label="Year" type="number" required></v-text-field>
            <v-text-field v-model="car.licensePlate" label="License Plate" required></v-text-field>
          </v-form>
        </v-card-text>
        <v-card-actions>
          <v-btn text @click="closeDialog">Cancel</v-btn>
          <v-spacer></v-spacer>
          <v-btn color="primary" text @click="saveCar">Save</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>

  </v-container>
</template>

<script>
import api from '../services/api';

export default {
  name: 'CarComponent',
  data() {
    return {
      cars: [],
      headers: [
        { text: 'Make', value: 'make' },
        { text: 'Model', value: 'model' },
        { text: 'Year', value: 'year' },
        { text: 'License Plate', value: 'licensePlate' },
        { text: 'Available', value: 'available' },
        { text: 'Actions', value: 'actions', sortable: false }
      ],
      dialog: false,
      car: {},
      isEdit: false
    };
  },
  created() {
    this.fetchCars();
  },
  methods: {
    fetchCars() {
      api.getCars().then(response => {
        this.cars = response.data;
      });
    },
    openDialog() {
      this.dialog = true;
      this.isEdit = false;
      this.car = {};
    },
    closeDialog() {
      this.dialog = false;
      this.car = {};
    },
    saveCar() {
      if (this.isEdit) {
        api.updateCar(this.car.id, this.car).then(() => {
          this.fetchCars();
          this.closeDialog();
        });
      } else {
        api.createCar(this.car).then(() => {
          this.fetchCars();
          this.closeDialog();
        });
      }
    },
    editCar(car) {
      this.car = { ...car };
      this.isEdit = true;
      this.dialog = true;
    },
    deleteCar(id) {
      api.deleteCar(id).then(() => {
        this.fetchCars();
      });
    }
  },
  computed: {
    formTitle() {
      return this.isEdit ? 'Edit Car' : 'Add Car';
    }
  }
};
</script>
