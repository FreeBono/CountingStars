export default async function encodeImageFileExcel(metadata, imageFile) {
  const reader = new FileReader();

  reader.onloadend = function(e) {
      var result = e.target.result;
      metadata.image = result;
  }
  reader.readAsDataURL(imageFile);
}