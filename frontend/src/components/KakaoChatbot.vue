<template>
  <div class="chat-btn">
    <div
      id="kakao-talk-channel-chat-button"
      data-channel-public-id="_jpaNb"
      data-title="consult"
      data-size="small"
      data-color="yellow"
      data-shape="pc"
      data-support-multiple-densities="true"
    ></div>
  </div>
</template>

<script>
export default {
  name: 'KakaoChatbot',
  setup() {


    return {
    }
  }

}
</script>

<style scoped>
.chat-btn {
  position: fixed ;
  bottom: 15px;
  right: 15px;
}
</style>