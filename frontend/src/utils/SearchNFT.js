import store from '@/store';
import axios from 'axios'
import contractInfo from '@/utils/contractInfo'
export default async function SearchToken(myWallet) {

  var Web3 = require('web3');
  var web3 = new Web3(new Web3.providers.HttpProvider('https://rinkeby.infura.io/v3/1b71a03449674cfe98b98c4915a7cbc7'));
  var sender = web3.eth.accounts.privateKeyToAccount('0x' + "3f5480375cbab19af805d26913fb9e7ee93ae744434ec20fbffc3c06ba39d18e");
  web3.eth.accounts.wallet.add(sender);
  let contract = new web3.eth.Contract(contractInfo.abi, contractInfo.address)
  
  const logs = []  
  await contract.getPastEvents('Transfer', {
      filter: {
          from: myWallet,
          
      },
      fromBlock: 0
      }).then((events) => {
          logs.push(...events)
      });

  await contract.getPastEvents('Transfer', {
    filter: {
        to: myWallet
    },
    fromBlock: 0
    }).then((events) => {
        logs.push(...events)
    });

  return logs

  }