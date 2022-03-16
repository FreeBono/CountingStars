<template>

  <body>
      <div style="height:36.4px; marign-top:40px; margin-bottom:30px; font-size:26px;">로그인</div>
      <div style="width:400px; margin: 0 auto;"  >
        
        <div class="form-group">
          <input name="email" type="text" placeholder="이메일을 입력해주세요" class="form-control" v-model="data.email"/>
      

        </div>
        <div class="form-group">
          <input name="password" type="password" placeholder="비밀번호를 입력해주세요" class="form-control" v-model="data.password"/>
    
        </div>
        <div align="right" style="font-size:15px; margin-top:16px; margin-bottom:30px;">
          <span>비밀번호 찾기</span> | <span @click="goSignup" style="cursor:pointer">회원가입</span>
        </div>
        <div class="form-group">
          <button type="button" class="btn btn-dark" style="margin-bottom : 30px;" :disabled="loading" @click="handleLogin" >로그인</button>
          <span
            v-show="loading"
            class="spinner-border spinner-border-sm"
          ></span>
        </div>
        <hr style="margin-bottom:30px;">
        <div>
          <LoginKakao/>
        </div>
        <div>
          <button type="button" class="btn btn-secondary" style="background-color:#1DC800">이메일로 로그인</button>
        </div>
        <div class="form-group">
          <div v-if="message" class="alert alert-danger" role="alert">
            {{ message }}
          </div>
        </div>
        

      <!-- <div class="col-md-12">
        <div class="card card-container">
          <img
            id="profile-img"
            src="//ssl.gstatic.com/accounts/ui/avatar_2x.png"
            class="profile-img-card"
          />
          <Form  :validation-schema="schema">
            <div class="form-group">
              <label for="email">Email</label>
              <Field name="email" type="text" class="form-control" />
              <ErrorMessage name="email" class="error-feedback" />
            </div>
            <div class="form-group">
              <label for="password">Password</label>
              <Field name="password" type="password" class="form-control" />
              <ErrorMessage name="password" class="error-feedback" />
            </div>

            <div class="form-group">
              <button class="btn btn-primary btn-block" :disabled="loading">
                <span
                  v-show="loading"
                  class="spinner-border spinner-border-sm"
                ></span>
                <span>Login</span>
              </button>
            </div>

            <div class="form-group">
              <div v-if="message" class="alert alert-danger" role="alert">
                {{ message }}
              </div>
            </div>
          </Form>
        </div>
      </div> -->
      <!-- <div class="btn">
        <LoginKakao/>
      </div> -->
    </div>
    <Block/>
  </body>

</template>

<script>
import { Form, Field, ErrorMessage } from "vee-validate";
import * as yup from "yup";
import LoginKakao from "../components/LoginKakao.vue"
import Block from "@/components/Block.vue"
export default {
  name: "Login",
  components: {
    Block,
    Form,
    Field,
    ErrorMessage,
    LoginKakao,
  },
  data() {
    const schema = yup.object().shape({
      email: yup.string().required("email is required!"),
      password: yup.string().required("Password is required!"),
    });

    return {
      loading: false,
      message: "",
      schema,
      data : {
        email : '',
        password : '',
      }
    };
  },
  computed: {
    loggedIn() {
      return this.$store.state.auth.status.loggedIn;
    },
  },
  created() {
    if (this.loggedIn) {

    }
  },
  methods: {
    handleLogin() {
      console.log(22)
      this.loading = true;
      console.log(this.data)
      this.$store.dispatch("auth/login", this.data).then(
        () => {
  
        },
        (error) => {
          this.loading = false;
          this.message =
            (error.response &&
              error.response.data &&
              error.response.data.message) ||
            error.message ||
            error.toString();
        }
      );
    },
    goSignup() {
      this.$router.push({name : 'Signup'})
    }
  },
};
</script>

<style scoped>
label {
  display: block;
  margin-top: 10px;
}

.card-container.card {
  max-width: 350px !important;
  padding: 40px 40px;
}

.card {
  background-color: #f7f7f7;
  padding: 20px 25px 30px;
  margin: 0 auto 25px;
  margin-top: 50px;
  -moz-border-radius: 2px;
  -webkit-border-radius: 2px;
  border-radius: 2px;
  -moz-box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
  -webkit-box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
  box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
}

.profile-img-card {
  width: 96px;
  height: 96px;
  margin: 0 auto 10px;
  display: block;
  -moz-border-radius: 50%;
  -webkit-border-radius: 50%;
  border-radius: 50%;
}

.error-feedback {
  color: red;
}

input {
  width: 100%;
  font-size: 1rem;
  line-height: 46px;
  letter-spacing: -0.6px;
  font-weight: 400;
  padding: 0 18px;
  border: 1px solid #E2E2E2;
  transition: 0.3s;
  font-family: 'Helvetica Neue', "Noto Sans KR", sans-serif;
  margin-bottom : 10px;
  font-family: 'Helvetica Neue', "Noto Sans KR", sans-serif;

}

body {
  font-family: "Helvetica Neue", "Noto Sans KR", sans-serif;
  font-weight: 400;

}



.btn {
  display: block;
  width: 100%;
  font-size: 1.125rem;
  height : 54px;
  letter-spacing: -0.5px;
  font-weight: 400;
  text-align: center;

  cursor: pointer;
}


</style>
