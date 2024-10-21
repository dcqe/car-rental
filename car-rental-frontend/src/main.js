import Vue from 'vue';
import App from './App.vue';
import router from './router';

import Vuetify from 'vuetify';
import 'vuetify/dist/vuetify.min.css';

import '@mdi/font/css/materialdesignicons.css';

Vue.config.productionTip = false;

Vue.use(Vuetify);

const vuetify = new Vuetify({
  theme: {
    dark: true,
  },
  icons: {
    iconfont: 'mdi',
  },
});

new Vue({
  router,
  vuetify,
  render: h => h(App),
}).$mount('#app');
