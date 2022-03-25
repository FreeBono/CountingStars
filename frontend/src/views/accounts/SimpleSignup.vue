<template>
  <body style="width:400px; margin: 0 auto;">
    <div style="height:36.4px; marign-top:40px; margin-bottom:30px; font-size:26px;" class="join_tit">
      <h6>휴대폰 본인인증</h6>
      <div class="join_step" style="margin-bottom:40px;">
        <span></span>
        <span class="now">2</span>
        <span></span>
      </div>
      <img src="@/assets/ico_phone.png" alt="" style="margin-bottom:20px;">
      <div class="join_tbox_txt" style="margin-bottom:40px;"> 카카오로 간편하게 가입해 보세요! </div>
      <!-- <button type="button" class="btn_type btn_color" >휴대폰 본인인증</button> -->
    </div>
    <div style="margin-top:250px;">
      <LoginKakao/>
      <button type="button" class="btn btn-secondary" >이메일로 가입</button>

    </div>
  </body>

</template>

<script scoped>
import LoginKakao from "../../components/LoginKakao.vue"

export default {
  name : "SimpleSignup",
  components : {
    LoginKakao,
  }

}
</script>

<style scoped>
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

.btn {
  display: block;
  width: 100%;
  font-size: 1.125rem;
  height : 54px;
  letter-spacing: -0.5px;
  font-weight: 400;
  text-align: center;
  background-color: #cccccc;
  cursor: pointer;
  border: 0px;
  border-radius: 8px;
}

</style>