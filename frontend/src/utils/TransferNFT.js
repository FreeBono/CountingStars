// import Web3 from 'web3'
import { createToast } from 'mosha-vue-toastify';
import 'mosha-vue-toastify/dist/style.css'
import store from '@/store';
import api from '@/services/api.js'
import contractInfo from '@/utils/contractInfo'

export default async function TransferToken(receiveAccount,receiveKey, tokenId) {
  var Web3 = require('web3');
  var web3 = new Web3(new Web3.providers.HttpProvider('https://rinkeby.infura.io/v3/1b71a03449674cfe98b98c4915a7cbc7'));
  const accounts = await ethereum.request({ method: 'eth_requestAccounts' });
  const sendAccount = accounts[0]
  var sender = web3.eth.accounts.privateKeyToAccount('0x'+receiveKey)
  web3.eth.accounts.wallet.add(sender);
  
  console.log(web3.eth.accounts.wallet);
  let contract = new web3.eth.Contract( contractInfo.abi , contractInfo.address)
  try {
    console.log('엥')
    await contract.methods.safeTransferFrom(sendAccount,receiveAccount,parseInt(tokenId)).send({from: sendAccount,gas:600000, }).then(res =>console.log(res)).catch(err => console.log(err))
    console.log('음')
    store.dispatch('sendToken',tokenId)
    
    //거래 내역 저장
    api.post("/userTransaction",{
      userId : store.state.auth.user.id, count : 1
    })
    .then(res => {
      console.log(res)
    })
    .catch(err => {
      console.log(err)
    })

    //알람
    createToast(
      { title: 'NFT Transfer Success',  },
      { type:'success', showIcon:true, position:'bottom-right', }
      )
  } catch(err) {
    createToast(
      { title: 'NFT Transfer Failed',  },
      { type:'danger', showIcon:true, position:'bottom-right', }
      )
  }
  
  }