<template>
  <div class="about">
    <h1 @click="ipfsUpload">This is an about page</h1>
  </div>
</template>

<script>
import { create } from "ipfs-http-client";
import axios from 'axios'

  export default {
    name: 'hello-metamask',
    setup() {
      const jsonn = {
        name : 'donghyun',
        age : '28',
        image : 'https://t1.daumcdn.net/cfile/tistory/24283C3858F778CA2E',
      }
      
  






      abc()
      async function abc() {
        const client = create('/ip4/127.0.0.1/tcp/5001');
        console.log(2)
        axios({
          method: 'post',
          url : `http://127.0.0.1:5001/api/v0/bootstrap`
        })
        .then(res => console.log(res.data))
        .catch(err => console.log(err))
        // const buf2 = Buffer.from(JSON.stringify(jsonn));
        // client.add(JSON.stringify(jsonn)).then(res => 
        // axios({
        //   method: 'post',
        //   url : `http://127.0.0.1:5001/api/v0/cat?arg=${res.path}`
        // })
        // .then(res => console.log(res.data))
        // .catch(err => console.log(err))
    
        // );
        // console.log(created)
        // axios({
        //   method: 'post',
        //   url : `http://127.0.0.1:5001/api/v0/cat?arg=${created}`
        // })
        // .then(res => console.log(res))
        // .catch(err => console.log(err))
    
      }

      // const test = "0"
      // const IpfsApi = require('ipfs-api');
      // const ipfs = IpfsApi("127.0.0.1", "5001", { protocol: "http" });

      // const ipfsUpload = (req, res) => {
      //   const buf2 = Buffer.from("hello buffrrrer");

      //     const file = buf2

      //     //IPFS UPLOAD & HASH GET
      //     //접속 : https://ipfs.io/ipfs/{해시값}
      //     //접속 : https://ipfs.io/ipfs/QmeTDymMT6BTd4NKuS5Kz3kx2HTMtbSRTDF6JteYxMqx2o
      //     ipfs.files.add(file)
      //         .then(result => {
      //             console.log(result[0].hash);
      //             // res.redirect(`https://ipfs.io/ipfs/${result[0].hash}`);
      //         });
      // };
      return {
        // ipfsUpload,
        // test,
        // ipfs,
        abc

      }
    }
  }
</script>
    
<style>
</style>