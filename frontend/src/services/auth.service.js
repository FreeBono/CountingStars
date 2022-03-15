import api from "./api";
import TokenService from "./token.service";

class AuthService {
  login({ email, password }) {
    console.log('3로그인 서비스 실행')
    return api
      .post("/auth/signin", {
        email,
        password
      })
      .then((response) => {
        console.log("authservice : " + response)
        if (response.data.accessToken) {
          TokenService.setUser(response.data);
        }

        return response.data;
      })
      .catch((err) => {
        console.log(err)
      })
  }

  logout() {
    TokenService.removeUser();
  }

  register({ username, email, password }) {
    return api.post("/auth/signup", {
      username,
      email,
      password
    });
  }
}

export default new AuthService();
