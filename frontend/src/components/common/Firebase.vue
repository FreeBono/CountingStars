<template>
  <!-- <div>
    <div >
      <p>Upload an image to Firebase:</p>
      <input type="file" @change="previewImage" accept="image/*" >
    </div>
    <div>
      <p>Progress: {{uploadValue.toFixed()+"%"}}
      <progress id="progress" :value="uploadValue" max="100" ></progress>  </p>
    </div>
    <div v-if="imageDataa!=null">
      <img class="preview" :src="picture">
        <br>
    </div>
      <button @click="onUpload">Upload</button>
  </div> -->
  <button type="primary" width="200px">
    이미지 업로드
    <input
      class="imginput"
      type="file"
      accept="image/jpeg, image/jpg"
      @change="getImgPath"
    />
  </button>
</template>

<script>
import { getStorage, ref, uploadBytes } from "firebase/storage";
import { firebase } from "firebase/app";

export default {
  name: 'Firebase',
  setup() {
    const storage = getStorage();
    const storageRef = ref(storage, 'some-child');

    var imageDataa = null
    var picture = null
    var uploadValue = 0

    // 'file' comes from the Blob or File API
    // uploadBytes(storageRef, file)
    //   .then((snapshot) => {
    //     console.log(snapshot, 'Uploaded a blob or file!');
    //   })
    const getImgPath = (e) => {
      const img = e.target.files[0];
      this.imgpath = img;
      this.data.push({ imgpath: img });
    }

    const previewImage = (event) => {
      uploadValue = 0;
      picture = null;
      imageDataa = event.target.files[0];
    }

    const onUpload = () => {
      picture=null;
      const storageRef=firebase.storage().ref(`${imageDataa.name}`).put(imageDataa);
      storageRef.on(`state_changed`,snapshot=>{
        uploadValue = (snapshot.bytesTransferred/snapshot.totalBytes)*100;
      }, error=>{console.log(error.message)},
      ()=>{uploadValue=100;
        storageRef.snapshot.ref.getDownloadURL().then((url)=>{
          picture =url;
        });
      }
      );
    }

    return {
      storageRef,
      storage,
      previewImage,
      onUpload,
      // uploadBytes,
      imageDataa,
      picture,
      uploadValue,

      getImgPath,
    }
  }
}
</script>

<style scoped>

</style>