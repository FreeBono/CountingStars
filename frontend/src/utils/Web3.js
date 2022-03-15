var Web3 = require('web3');
var web3 = new Web3(new Web3.providers.HttpProvider('http://localhost:7545'));
console.log("isConnected=");
web3.eth.net.isListening().then(console.log);


web3.eth.getAccounts(console.log);
// web3.isConnected()
// export default getWeb3