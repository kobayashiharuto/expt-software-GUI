package services;

import java.util.function.Consumer;

import repositories.LoginRepository;
import utils.OriginalResult;
import entities.*;

public class LoginService {
  private LoginService() {
  }

  static public void login(String name, String password, Consumer<OriginalResult<User>> callback) {
    User user = new User(null, name, password, 200); // TODO: point を定数化する
    LoginRepository loginRepository = new LoginRepository(user, callback);
    loginRepository.start();
  }
}
