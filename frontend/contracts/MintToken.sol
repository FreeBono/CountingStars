// SPDX-License-Identifier: MIT
pragma solidity ^0.8.12;

import "@openzeppelin/contracts/token/ERC721/extensions/ERC721Enumerable.sol";

contract MintToken is ERC721Enumerable {
  constructor() ERC721("brands","luxury") {}

  mapping(uint256 => uint256) public brandTypes;
  
  function mintTokoen() public {
    uint256 brandTokenId = totalSupply() +1;
    uint256 brandType = uint256(keccak256(abi.encodePacked(block.timestamp, msg.sender, brandTokenId))) % 5 +1;
    brandTypes[brandTokenId] = brandType;
    _mint(msg.sender,brandTokenId);
  }
}