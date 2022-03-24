<template>
  <div class="about">
    <h1 @click="ipfsUpload">This is an about page</h1>
  </div>
</template>

<script>
  export default {
    name: 'hello-metamask',
    setup() {
      const test = "0"
      const IpfsApi = require('ipfs-api');
      const ipfs = IpfsApi("thhh", "5001", { protocol: "https" });

      const ipfsUpload = (req, res) => {
        const buf2 = Buffer.from("hello buffer");

          const file = buf2

          //IPFS UPLOAD & HASH GET
          //접속 : https://ipfs.io/ipfs/{해시값}
          ipfs.files.add(file)
              .then(result => {
                  console.log(result[0].hash);
                  // res.redirect(`https://ipfs.io/ipfs/${result[0].hash}`);
              });
      };
      return {
        ipfsUpload,
        test,
        ipfs,


      }
    }
  }
</script>
    
<style>
</style>