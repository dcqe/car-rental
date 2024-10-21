import Vue from 'vue';
import App from './App.vue';
import router from './router';

import Vuetify from 'vuetify';
import 'vuetify/dist/vuetify.min.css';

// Import Material Design Icons CSS
import '@mdi/font/css/materialdesignicons.css';

Vue.config.productionTip = false;

Vue.use(Vuetify);

new Vue({
  router,
  vuetify: new Vuetify({
    icons: {
      iconfont: 'mdi', // Default - use 'mdi' for Material Design Icons
    },
  }),
  render: h => h(App),
}).$mount('#app');
