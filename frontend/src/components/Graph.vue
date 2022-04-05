<template>
  <div>
  <LineChart :chartData="testData" />
</div>
 
</template>

<script>

import { DoughnutChart,BarChart,LineChart, } from 'vue-chart-3';
import api from '@/services/api'
import { Chart, registerables } from "chart.js";
import {useStore} from 'vuex'
import {ref} from 'vue'
// import moment from 'moment'


Chart.register(...registerables);


export default {
  name: 'Graph',
  components: { DoughnutChart,BarChart,LineChart, },
  setup() {
    const store = useStore()
    const moment = require('moment');
    
    const dates = []
    for (let i=11; i >= 0 ; i--) {
      const today = moment();
      dates.push(today.subtract(i,'months').format('YYYY-MM'))
    }

    const testData = ref({
      labels: dates,
      datasets: [
        {
          data: [0,0,0,0,0,0,0,0,0,0,0,0],
          backgroundColor: ['#77CEFF', '#0079AF', '#123E6B', '#97B0C4', '#A5C8ED'],
        },
      ],
    });
    // console.log(today.format('YYYY-MM-DD'));
    // const a = today.add(7, 'months')
    api.get('/userTransaction',{params: {userId: store.state.auth.user.id}})
    .then(res => {
      res.data.forEach(e => {
        for (let i = 0; i < 12; i ++) {
          if (e.date.substring(0,7) === dates[i]) {
            testData.value.datasets[0].data[i] += 1
          }
        }
        // if (e.date.substring(0,7) === dates[0]) {
        //   testData.datasets[0].data[0] += 1
        // } 
      })
    })
    .catch(err => {
      console.log(err)
    })
    
    
    
    

    


    

    

    
      

  

  

    return { 
      testData,
      // today,
      dates,
    };
  },
};
</script>