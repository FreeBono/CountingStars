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
// Import the functions you need from the SDKs you need
import { initializeApp } from "firebase/app";
// TODO: Add SDKs for Firebase products that you want to use
// https://firebase.google.com/docs/web/setup#available-libraries

// Your web app's Firebase configuration
const firebaseConfig = {
  apiKey: "AIzaSyC8IDEeMke2Atc3UU_52VHv39IBGrL0CwE",
  authDomain: "grand-lamp-331014.firebaseapp.com",
  projectId: "grand-lamp-331014",
  storageBucket: "grand-lamp-331014.appspot.com",
  messagingSenderId: "382068271286",
  appId: "1:382068271286:web:39724923cca5e6ccc75e17"
};

// Initialize Firebase
const firebase = initializeApp(firebaseConfig);


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
