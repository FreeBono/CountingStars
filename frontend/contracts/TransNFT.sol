// SPDX-License-Identifier: MIT

pragma solidity ^0.8.12;

import "@openzeppelin/contracts/token/ERC721/ERC721.sol";
import "@openzeppelin/contracts/utils/Counters.sol";
import "@openzeppelin/contracts/access/Ownable.sol";
import "@openzeppelin/contracts/token/ERC721/extensions/ERC721URIStorage.sol";

contract TransNFT is ERC721URIStorage, Ownable {
    using Counters for Counters.Counter;
    Counters.Counter private _tokenIds;

    constructor() public ERC721("MyNFTs", "MNFT") {}
    mapping(uint => string) tokenURIs;

    // function tokenURI(uint256 tokenId) public view virtual override returns (string memory) {
    //     return tokenURIs[tokenId];
    // }

    

    // function mintNFT(address to, string memory tokenURI)
    //     public onlyOwner
    //     returns (uint256)
    // {
    //     _tokenIds.increment();

    //     uint256 newItemId = _tokenIds.current();
    //     _mint(to, newItemId);
    //     _setTokenURI(newItemId, tokenURI);

    //     return newItemId;
    // }
     function safeTransferFrom(
    address from,
    address to,
    uint256 tokenId,
    bytes memory _data
     ) public virtual override {
    // require(_isApprovedOrOwner(_msgSender(), tokenId), "ERC721: transfer caller is not owner nor approved");
    _safeTransfer(from, to, tokenId, _data);
 }
    
}