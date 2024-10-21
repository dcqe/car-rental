<template>
  <v-container>
    <h1>Customers</h1>
    <v-btn color="primary" @click="openDialog">Add Customer</v-btn>

    <v-data-table :headers="headers" :items="customers" class="elevation-1">
      <template v-slot:item.actions="{ item }">
        <v-btn icon @click="editCustomer(item)">
          <v-icon>mdi-pencil</v-icon>
        </v-btn>
        <v-btn icon @click="deleteCustomer(item.id)">
          <v-icon>mdi-delete</v-icon>
        </v-btn>
      </template>
    </v-data-table>

    <!-- Add/Edit Customer Dialog -->
    <v-dialog v-model="dialog" max-width="500px">
      <v-card>
        <v-card-title>
          <span class="headline">{{ formTitle }}</span>
        </v-card-title>
        <v-card-text>
          <v-form ref="form">
            <v-text-field v-model="customer.name" label="Name" required></v-text-field>
            <v-text-field v-model="customer.email" label="Email" required></v-text-field>
            <v-text-field v-model="customer.phoneNumber" label="Phone Number"></v-text-field>
          </v-form>
        </v-card-text>
        <v-card-actions>
          <v-btn text @click="closeDialog">Cancel</v-btn>
          <v-spacer></v-spacer>
          <v-btn color="primary" text @click="saveCustomer">Save</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>

  </v-container>
</template>

<script>
import api from '../services/api';

export default {
  name: 'CustomerComponent',
  data() {
    return {
      customers: [],
      headers: [
        {text: 'Name', value: 'name'},
        {text: 'Email', value: 'email'},
        {text: 'Phone Number', value: 'phoneNumber'},
        {text: 'Actions', value: 'actions', sortable: false}
      ],
      dialog: false,
      customer: {},
      isEdit: false
    };
  },
  created() {
    this.fetchCustomers();
  },
  methods: {
    fetchCustomers() {
      api.getCustomers().then(response => {
        this.customers = response.data;
      });
    },
    openDialog() {
      this.dialog = true;
      this.isEdit = false;
      this.customer = {};
    },
    closeDialog() {
      this.dialog = false;
      this.customer = {};
    },
    saveCustomer() {
      if (this.isEdit) {
        api.updateCustomer(this.customer.id, this.customer).then(() => {
          this.fetchCustomers();
          this.closeDialog();
        });
      } else {
        api.createCustomer(this.customer).then(() => {
          this.fetchCustomers();
          this.closeDialog();
        });
      }
    },
    editCustomer(customer) {
      this.customer = {...customer};
      this.isEdit = true;
      this.dialog = true;
    },
    deleteCustomer(id) {
      api.deleteCustomer(id).then(() => {
        this.fetchCustomers();
      });
    }
  },
  computed: {
    formTitle() {
      return this.isEdit ? 'Edit Customer' : 'Add Customer';
    }
  }
};
</script>
