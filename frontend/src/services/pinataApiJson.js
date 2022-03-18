import axios from "axios";

const pinJSONToIPFS = (JSONBody) => {
    const url = `https://api.pinata.cloud/pinning/pinJSONToIPFS`;
    return axios
        .post(url, JSONBody, {
            headers: {
                pinata_api_key: "f15021ccdb4f250811be",
                pinata_secret_api_key: "e92f052b8554d308b42b38921b823ac35741f390fe5e42d04b1505c88b1e9f0f",
            }
        })
        .then(function (response) {
            return response;
        })
        .catch(function (error) {
            return error;
        });
};

export default pinJSONToIPFS;