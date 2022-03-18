<template>
  <body style="width:400px; margin: 0 auto;">
    <div style="height:36.4px; marign-top:40px; margin-bottom:30px; font-size:26px;" class="join_tit">
      <h6>휴대폰 본인인증</h6>
      <div class="join_step" style="margin-bottom:40px;">
        <span class="now">1</span>
        <span></span>
        <span></span>
      </div>
      <img src="@/assets/ico_phone.png" alt="" style="margin-bottom:20px;">
      <div class="join_tbox_txt" style="margin-bottom:40px;"> 본인 인증 후 간편가입이 가능합니다. </div>
      <!-- <button type="button" class="btn_type btn_color" >휴대폰 본인인증</button> -->
    </div>
  </body>


<div class="container" style="padding-top:225px;">

  <details>
    <summary>
      <div class="button btn_type btn_color" style="width:400px; margin:0 auto;"  >
        휴대폰 본인인증
      </div>
      <div class="details-modal-overlay"></div>
    </summary>
    <div class="details-modal">
      <div class="details-modal-close">
        <svg xmlns="http://www.w3.org/2000/svg" width="14" height="14" viewBox="0 0 14 14" fill="none">
          <path fill-rule="evenodd" clip-rule="evenodd" d="M13.7071 1.70711C14.0976 1.31658 14.0976 0.683417 13.7071 0.292893C13.3166 -0.0976311 12.6834 -0.0976311 12.2929 0.292893L7 5.58579L1.70711 0.292893C1.31658 -0.0976311 0.683417 -0.0976311 0.292893 0.292893C-0.0976311 0.683417 -0.0976311 1.31658 0.292893 1.70711L5.58579 7L0.292893 12.2929C-0.0976311 12.6834 -0.0976311 13.3166 0.292893 13.7071C0.683417 14.0976 1.31658 14.0976 1.70711 13.7071L7 8.41421L12.2929 13.7071C12.6834 14.0976 13.3166 14.0976 13.7071 13.7071C14.0976 13.3166 14.0976 12.6834 13.7071 12.2929L8.41421 7L13.7071 1.70711Z" fill="black" />
        </svg>
      </div>
      <div class="details-modal-title" >
        <h1>휴대폰 본인인증</h1>
      </div>
      <div class="details-modal-content">
        <div>
          <div style="margin-bottom: 10px;">
          <input type="text" v-model="notificationData.phoneNum"><button type="button" class="btn btn-dark" @click="phoneNotifcation">인증</button>
          </div>
          <vue-countdown v-if="flag ==1" :time="1 * 1 * 60 * 3 * 1000" v-slot="{  minutes, seconds }">
            Time Remaining：{{ minutes }} minutes, {{ seconds }} seconds.
          </vue-countdown>
          <div v-if="flag==1">
            <input type="text" v-model="notificationNumber"><button type="button" class="btn btn-dark" @click="checkNotinumber">확인</button>
          </div>
        </div>
      </div>
    </div>
  </details>
</div>
</template>

<script>
import axios from 'axios'
import VueCountdown from '@chenfengyuan/vue-countdown';
import {ref} from 'vue'
import {useRouter} from 'vue-router'

export default {
  name : "Notification",
  components : {
    VueCountdown
  },
  setup() {
    const router= useRouter()
    const notificationNumber = ref('')
    const flag = ref(0)
    const notificationData = ref({country : "82", phoneNum : ''})
    const userNotinum = ref('')
    const phoneNotifcation = () => {
      console.log(notificationData.value)
      axios({
        method : 'post',
        url : "http://localhost:8080/api/v1/auth/signup/phone",
        data : notificationData.value
      })
      .then( res => {
        flag.value = 1
        console.log(res)
        userNotinum.value = res.data
      })
      .catch( err => {
        console.log(err)
      })
    }
    const checkNotinumber = () => {
      console.log(notificationNumber.value,'df',userNotinum.value)
      if (Number(notificationNumber.value) === userNotinum.value) {
      router.push({name:'SimpleSignup'})
      } else {
        alert('꺼지세요')
      }
    }
    return {
      notificationData,
      phoneNotifcation,
      flag,
      notificationNumber,
      checkNotinumber,
      router,
      userNotinum,

    }
  }
}
</script>

<style scoped lang="scss">
.join_step {
    display: flex;
    justify-content: center;
    align-items: center;
}
.join_tit h6 {
    font-size: 1.625rem;
    line-height: 1.4;
    letter-spacing: -0.6px;
    font-weight: 300;
    margin-bottom: 30px;
} 
.join_tit span.now {
    width: 20px;
    height: 20px;
    line-height: 20px;
    background: #222222;
    color: white;
    font-size: 0.875rem;
    letter-spacing: -0.25px;
    text-align: center;
}

.join_tit span {
    font-family: 'Roboto', sans-serif;
    display: block;
    text-align: center;
    width: 12px;
    height: 12px;
    background: #cccccc;
    margin-left: 30px;
    border-radius: 50%;
    margin: 0 15px;
}

.join_tbox_txt {
    font-size: 1.25rem;
    line-height: 2rem;
    letter-spacing: -0.6px;
    font-weight: 400;
}

.btn_type.btn_color {
    border: none;
    background: #000;
    color: #fff;
}

.btn_type {
    display: block;
    width: 100%;
    font-size: 1.125rem;
    line-height: 54px;
    letter-spacing: -0.5px;
    font-weight: 400;
    text-align: center;
    border: 1px solid #E1E1E1;
    background: #fff;
    cursor: pointer;
}

@import url("https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700&display=swap");

html {
  box-sizing: border-box;
}

*,
*:before,
*:after {
  box-sizing: border-box;
}

body {
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  background-color: #f9fafb;
  font-size: 70%;
  line-height: 1.4;
  font-family: "Inter", sans-serif;
  color: #6b7280;
  font-weight: 400;

 
}

// -------------- BUTTON

.button {
  appearance: none;
  background: #16a34a;
  border-radius: 0.25em;
  color: white;
  cursor: pointer;
  font-weight: 500;
  height: 3em;
  line-height: 3em;
  padding: 0 1em;

  &:hover {
    // background-color: lighten(#16a34a, 2%);
  }
}

// -------------- DETAILS MODAL

.details-modal {
  background: #ffffff;
  border-radius: 0.5em;
  box-shadow: 0 10px 20px rgba(black, 0.2);
  left: 50%;
  max-width: 90%;
  pointer-events: none;
  position: absolute;
  top: 50%;
  transform: translate(-50%, -50%);
  width: 30em;
  text-align: left;
  max-height: 90vh;
  display: flex;
  flex-direction: column;

  // -------------- CLOSE

  .details-modal-close {
    align-items: center;
    color: #111827;
    display: flex;
    height: 4.5em;
    justify-content: center;
    pointer-events: none;
    position: absolute;
    right: 0;
    top: 0;
    width: 4.5em;

    svg {
      display: block;
    }
  }

  // -------------- TITLE

  .details-modal-title {
    color: #111827;
    padding: 1.5em 2em;
    pointer-events: all;
    position: relative;
    width: calc(100% - 4.5em);

    h1 {
      font-size: 1.25rem;
      font-weight: 600;
      line-height: normal;
    }
  }

  // -------------- CONTENT

  .details-modal-content {
    border-top: 1px solid #e0e0e0;
    padding: 2em;
    pointer-events: all;
    overflow: auto;
  }
}

// -------------- OVERLAY

.details-modal-overlay {
  transition: opacity 0.2s ease-out;
  pointer-events: none;
  background: rgba(#0f172a, 0.8);
  position: fixed;
  opacity: 0;
  bottom: 0;
  right: 0;
  left: 0;
  top: 0;

  details[open] & {
    pointer-events: all;
    opacity: 0.5;
  }
}

// -------------- DETAILS

details {
  summary {
    list-style: none;
      
    &:focus {
      outline: none;
    }
    &::-webkit-details-marker {
      display: none;
    }
  }
}

// -------------- OTHER

code {
  font-family: Monaco, monospace;
  line-height: 100%;
  background-color: #2d2d2c;
  padding: 0.1em 0.4em;
  letter-spacing: -0.05em;
  word-break: normal;
  border-radius: 7px;
  color: white;
  font-weight: normal;
  font-size: 1.75rem;
  position: relative;
  top: -2px;
}

.container {
  text-align: center;
  max-width: 40em;
  padding: 2em;

  > h1 {
    font-weight: 700;
    font-size: 2rem;
    line-height: normal;
    color: #111827;
  }

  > p {
    margin-top: 2em;
    margin-bottom: 2em;
  }

  sup {
    font-size: 1rem;
    margin-left: 0.25em;
    opacity: 0.5;
    position: relative;
  }
}

</style>