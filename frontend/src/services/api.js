import axios from "axios";

const instance = axios.create({
  baseURL: `${도메인}/api/v1`,
  headers: {
    "Content-Type": "application/json",
  },
});

export default instance;
