<template>
  <v-app>
    <v-navigation-drawer app>
      <v-list dense>
        <!-- Dashboard -->
        <v-list-item
            :to="{ name: 'Dashboard' }"
            router
            exact
        >
          <v-list-item-icon>
            <v-icon>mdi-view-dashboard</v-icon>
          </v-list-item-icon>
          <v-list-item-content>
            <v-list-item-title>Dashboard</v-list-item-title>
          </v-list-item-content>
        </v-list-item>

        <!-- Customers -->
        <v-list-item
            :to="{ name: 'Customers' }"
            router
            exact
        >
          <v-list-item-icon>
            <v-icon>mdi-account-group</v-icon>
          </v-list-item-icon>
          <v-list-item-content>
            <v-list-item-title>Customers</v-list-item-title>
          </v-list-item-content>
        </v-list-item>

        <!-- Cars -->
        <v-list-item
            :to="{ name: 'Cars' }"
            router
            exact
        >
          <v-list-item-icon>
            <v-icon>mdi-car</v-icon>
          </v-list-item-icon>
          <v-list-item-content>
            <v-list-item-title>Cars</v-list-item-title>
          </v-list-item-content>
        </v-list-item>

        <!-- Rentals -->
        <v-list-item
            :to="{ name: 'Rentals' }"
            router
            exact
        >
          <v-list-item-icon>
            <v-icon>mdi-car-key</v-icon>
          </v-list-item-icon>
          <v-list-item-content>
            <v-list-item-title>Rentals</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
      </v-list>
    </v-navigation-drawer>

    <v-app-bar app>
      <v-toolbar-title>Car Rental Service</v-toolbar-title>
    </v-app-bar>

    <v-main>
      <router-view></router-view>
    </v-main>
  </v-app>
</template>

<script>
export default {
  name: 'App',
};
</script>

<style>
/* Add any global styles here */
</style>
