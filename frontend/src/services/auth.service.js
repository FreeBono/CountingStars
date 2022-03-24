import api from "./api";
import TokenService from "./token.service";

class AuthService {
  login({ email, password }) {
    console.log(email)
    console.log(password)
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

  register({ username, email, password,roles, name}) {
    return api.post("/auth/signup", {
      username,
      email,
      password,
      roles,
      name,
    });
  }
}

export default new AuthService();
