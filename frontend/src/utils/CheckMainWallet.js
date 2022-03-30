

export default async function checkAccount(account,privatekey) {
  var Web3 = require('web3')
  var web3 = new Web3(new Web3.providers.HttpProvider('https://rinkeby.infura.io/v3/1b71a03449674cfe98b98c4915a7cbc7'));
  var checkAddress = web3.eth.accounts.privateKeyToAccount('0x' + privatekey);
  console.log(checkAddress)
  console.log(account)
  return checkAddress
} 