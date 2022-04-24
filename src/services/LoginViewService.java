package services;

import java.util.concurrent.*;

import repositories.LoginRepository;
import entities.*;

public class LoginViewService {
  public LoginViewService() {
  }

  public void login(String name, String password) throws InterruptedException, ExecutionException, TimeoutException {
    User user = new User(name, password, 200);
    LoginRepository loginRepository = new LoginRepository(user, (result) -> loginCallback(result));
    loginRepository.start();
  }

  static void loginCallback(boolean result) {
    if (result) {
      System.out.println("login success");
    } else {
      System.out.println("login fail");
    }
  }
}
