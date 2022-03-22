<template>
  <div class="container" >
    <div class ="row">
      <!-- <div class="col-12"> -->
        <label for="fileName" class="join-profile-img-edit" >
          <input ref="image" type="file" id="fileName" accept="image/*" @change="changeImgFile" style="opacity: 0">
          <div class="align-items:center;">
            <div v-if="state.nftImg" style="height:250px; width : 100%;">
              <img :src="state.nftImg" alt="" class="aa" >
            </div>
            <div v-else style="height:250px; width : 100%;">
              <img src="@/assets/uploadicon.jpg" alt="" style="">
            </div>
          </div>
        </label>
      <!-- </div> -->
    </div>
  </div>
</template>

<script>
// import axios from 'axios'
import {ref} from 'vue'
export default {
  emits : ['file-upload'],
  name : 'FileUpload',
  setup(props,{emit}) {
    const state = ref({

      nftImg: null,
      nftImgFile: null,
    })


    const changeImgFile = async function (event) {
      if( event.target.files && event.target.files.length > 0 ) {
        
        state.value.nftImgFile = event.target.files[0];
        state.value.nftImg = URL.createObjectURL(state.value.nftImgFile); // 파일 경로로 바꿔서 추가
        // console.log(state.value.nftImg)
        emit('file-upload',state.value)
      }
    }

    return {
  
      changeImgFile,
      state
    }
  }


}
</script>


<style scoped >
#fileName {

}

.join-profile-img-edit{
 
}

.aa{
  width :100%;
  height:100%;
}

img {
 max-width: 100%;
 max-height: 100%;
}

.im {
  width : 100%;
  overflow:hidden; 
}
</style>