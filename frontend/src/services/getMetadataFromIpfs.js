import { create } from "ipfs-http-client";

export default async function getMetadataFromIpfs(ipfsHash) {
    const ipfs = create("/ip4/127.0.0.1/tcp/5001");
    const chunks = []
    for await (const chunk of ipfs.cat(ipfsHash)) {
      chunks.push(chunk)
    }
    const data = Buffer.concat(chunks).toString();
    const metaData = JSON.parse(data);
    console.log(metaData);
    return metaData;
}