// import Web3 from 'web3'
import contractInfo from '@/utils/contractInfo'


export default async function publishToken(publisher) {
var Web3 = require('web3');
var web3 = new Web3(new Web3.providers.HttpProvider('https://rinkeby.infura.io/v3/1b71a03449674cfe98b98c4915a7cbc7'));
var sender = web3.eth.accounts.privateKeyToAccount('0x' + "62db5bf76a08ce902c087a504290008e9512dff0aef136d4d167a0a70b23d1a8");
console.log('sender확인 : ',sender)
web3.eth.accounts.wallet.add(sender);


let contract = new web3.eth.Contract( contractInfo.abi, contractInfo.address)
const abc = await contract.methods.mintNFT("0x6D6b463FcB1eE999741A94BCa578b38965a24942",publisher).send({from: "0xA4F883c083450f9D06B2bc5BaA31eEFB7d689D09",gas:600000, })

}