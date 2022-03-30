import store from '@/store';
import axios from 'axios'

export default async function CheckTransaction(txHash) {

  var Web3 = require('web3');
  var web3 = new Web3(new Web3.providers.HttpProvider('https://rinkeby.infura.io/v3/1b71a03449674cfe98b98c4915a7cbc7'));

  var sender = web3.eth.accounts.privateKeyToAccount('0x' + "3f5480375cbab19af805d26913fb9e7ee93ae744434ec20fbffc3c06ba39d18e");

  web3.eth.accounts.wallet.add(sender);



  const log = await web3.eth.getTransaction(txHash)
  console.log(log)

  return log
  }