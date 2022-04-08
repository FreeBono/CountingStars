// import Web3 from 'web3'
import axios from 'axios'
import store from '@/store';
import contractInfo from '@/utils/contractInfo'
import getMetadataFromIpfs from '@/services/getMetadataFromIpfs'


export default async function searchNFTs(targetAccount) {
  const accounts = await ethereum.request({ method: 'eth_requestAccounts' });
  const sendAccount = accounts[0]

  var Web3 = require('web3');
  var web3 = new Web3(new Web3.providers.HttpProvider('https://rinkeby.infura.io/v3/1b71a03449674cfe98b98c4915a7cbc7'));
  let contract = new web3.eth.Contract( contractInfo.abi, contractInfo.address)
  const balance = await contract.methods.balanceOf(targetAccount).call();

  const objects = [];
  const tokens = [];
  const tokenIdList = [];
  const result = [];

  for (let i = 1; i < parseInt(balance)+1; i++) {
    tokenIdList.push(i);
  }
  
  for (var i = 0; i < balance; i++) {
      tokens.push(await contract.methods.tokenOfOwnerByIndex(targetAccount, i).call());
  }
  
  console.log(tokens)
  for await (const token of tokens) {
    console.log(token)
    contract.methods.tokenURI(token).call().then(res => {
      getMetadataFromIpfs(res).then(
        r => {
          const myData = r
         myData['tokenId'] = element
         myData['stats'] = 0
         objects.push(myData)
         console.log(myData)
        }
      )
    })
  }
//   await tokens.forEach( element => {
//     console.log(element)
//     contract.methods.tokenURI(element).call().then(res => {
//      getMetadataFromIpfs(res).then(
//        r => {
//          const myData = r
//          myData['tokenId'] = element
//          myData['stats'] = 0
//          objects.push(myData)
//          console.log(myData)
//        }
//      )
     

     
//    });
//  })
  // await console.log('마지막')
  setTimeout(()=> {
    store.dispatch('searchWallet',objects)
    // console.log('마지막')
  },2000)
    

}