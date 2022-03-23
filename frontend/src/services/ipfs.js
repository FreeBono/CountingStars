const pinFileToBack = (data) =>  {
    const ipfsAPI = require('ipfs-api');
    const ipfs = ipfsAPI('127.0.0.1' ,'5001', {protocol: 'http'})
    const fs = require('fs');

    //read file
    let testFile = fs.readFileSync(data, 'utf8')

    //input buffer
    let testBuffer = Buffer.from(testFile); //new Buffer -> Buffer.from

    //upload file to ipfs
    ipfs.files.add(testBuffer, (err,file)=>{
        if(err) {
            console.log(err);
        }
        console.log(file)
    })
}

export default pinFileToBack;