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
// import moment from 'moment'


Chart.register(...registerables);


export default {
  name: 'Graph',
  components: { DoughnutChart,BarChart,LineChart, },
  setup() {
    const store = useStore()
    const moment = require('moment');
    
    // console.log(today.format('YYYY-MM-DD'));
    // const a = today.add(7, 'months')
    
    const dates = []
    for (let i=11; i >= 0 ; i--) {
      const today = moment();
      dates.push(today.subtract(i,'months').format('YYYY-MM'))
    }
    
    const testData = {
      labels: dates,
      datasets: [
        {
          data: [4,6,3,5,7,2,6,5,4,1,3,5],
          backgroundColor: ['#77CEFF', '#0079AF', '#123E6B', '#97B0C4', '#A5C8ED'],
        },
      ],
    };

    


    // api.get('/userTransaction',{params: {userId: store.state.auth.user.id}})
    // .then(res => {
    //   console.log(res)
    // })
    // .catch(err => {
    //   console.log(err)
    // })

    

    
      

  

  

    return { 
      testData,
      // today,
      dates,
    };
  },
};
</script>