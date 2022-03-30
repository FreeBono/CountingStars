import store from '@/store'

export default async function encodeImageFileAsURL(imageFile) {
  const reader = new FileReader();

  reader.readAsDataURL(imageFile);
  reader.onloadend = function(e) {
      var result = e.target.result;
      console.log(result);
      store.dispatch('setIpfs',result)
      
  
  }
  // const abc = reader.onloadend
  // console.log(abc)
}