const encodeImageFileAsURL = async (imageFile) =>  {
    const reader = new FileReader();
    
    reader.onloadend = function() {
        const url = reader.result;
        console.log(url);
        return url;
    }
    
    reader.readAsDataURL(imageFile);
}

export default encodeImageFileAsURL;