// const Migrations = artifacts.require("Migrations");
// const GameItem = artifacts.require("GameItem");
const MyNFTs = artifacts.require("MyNFTs")
// const MintToken = artifacts.require("MintToken")
module.exports = function (deployer) {
  // deployer.deploy(Migrations);
  // deployer.deploy(GameItem)
  deployer.deploy(MyNFTs)
  // deployer.deploy(MintToken)
};
