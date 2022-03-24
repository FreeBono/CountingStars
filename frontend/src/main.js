import { createApp } from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import "bootstrap";
import "bootstrap/dist/css/bootstrap.min.css";
import VueCountdown from '@chenfengyuan/vue-countdown';
import setupInterceptors from './services/setupInterceptors';
import BootstrapVue3 from 'bootstrap-vue-3'
import VueNextSelect from 'vue-next-select'
import { globalCookiesConfig } from "vue3-cookies";
import { Chart, Legend,Title,Tooltip} from "chart.js"
Chart.register(Legend,Title,Tooltip)
setupInterceptors(store);
Chart.defaults.plugins.title.display = true



globalCookiesConfig({
  expireTimes: "3d",
  path: "/",
  domain: "",
  secure: true,
  sameSite: "None",
});


createApp(App)
  .use(router)
  .use(store)
  .component(VueCountdown.name, VueCountdown)
  .use(BootstrapVue3)
  .component('vue-select', VueNextSelect)
  // .use(VueCookies)
  .mount("#app")

// window.Kakao.init('eb6b89b350284c15c748ea3d3855f6c5');
