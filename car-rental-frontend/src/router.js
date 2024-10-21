import Vue from 'vue';
import Router from 'vue-router';
import Dashboard from './components/DashboardComponent.vue';
import Customers from './components/CustomerComponent.vue';
import Cars from './components/CarComponent.vue';
import Rentals from './components/RentalComponent.vue';

Vue.use(Router);

export default new Router({
  mode: 'history',
  routes: [
    { path: '/', name: 'Dashboard', component: Dashboard },
    { path: '/customers', name: 'Customers', component: Customers },
    { path: '/cars', name: 'Cars', component: Cars },
    { path: '/rentals', name: 'Rentals', component: Rentals }
  ]
});
