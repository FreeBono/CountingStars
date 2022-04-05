import store from '@/store'

export default async function encodeImageFileAsURL(imageFile) {
  const reader = new FileReader();

  
  reader.onloadend = function(e) {
    var result = e.target.result;
    store.dispatch('setIpfs', result)
  }

  reader.readAsDataURL(imageFile);
}