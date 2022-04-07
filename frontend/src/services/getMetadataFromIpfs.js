import { create } from "ipfs-http-client";

export default async function getMetadataFromIpfs(ipfsHash) {
    const ipfs = create();
    const chunks = []
    for await (const chunk of ipfs.cat(ipfsHash)) {
      chunks.push(chunk)
    }
    const data = Buffer.concat(chunks).toString();
    const metaData = JSON.parse(data);
    console.log(metaData);
    return metaData;
}