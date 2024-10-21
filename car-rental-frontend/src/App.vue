<template>
  <v-app>
    <v-navigation-drawer app>
      <v-list>
        <v-list-item link to=\"/\">
          <v-list-item-title>Dashboard</v-list-item-title>
        </v-list-item>
        <v-list-item link to=\"/customers\">
          <v-list-item-title>Customers</v-list-item-title>
        </v-list-item>
        <v-list-item link to=\"/cars\">
          <v-list-item-title>Cars</v-list-item-title>
        </v-list-item>
        <v-list-item link to=\"/rentals\">
          <v-list-item-title>Rentals</v-list-item-title>
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
}
</script>
