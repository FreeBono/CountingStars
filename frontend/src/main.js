import { createApp } from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import "bootstrap";
import "bootstrap/dist/css/bootstrap.min.css";
import VueCountdown from '@chenfengyuan/vue-countdown';
import setupInterceptors from './services/setupInterceptors';
import BootstrapVue3 from 'bootstrap-vue-3'


setupInterceptors(store);

createApp(App)
  .use(router)
  .use(store)
  .component(VueCountdown.name, VueCountdown)
  .use(BootstrapVue3)
  .mount("#app")

// window.Kakao.init('eb6b89b350284c15c748ea3d3855f6c5');
