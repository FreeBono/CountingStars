import axios from "axios";

const instance = axios.create({
  baseURL: "https://j6e204.p.ssafy.io:8443/api/v1",
  headers: {
    "Content-Type": "application/json",
  },
});

export default instance;
