package services;

import java.util.function.Consumer;

import repositories.LoginRepository;
import repositories.SignupRepository;
import settings.Settings;
import utils.OriginalResult;
import entities.*;

public class AuthService {

  public void login(String name, String password, Consumer<OriginalResult<User>> callback) {
    User user = new User(null, name, password, Settings.USER_INIT_POINT);
    LoginRepository loginRepository = new LoginRepository(user, callback);
    loginRepository.start();
  }

  public void signup(String name, String password, Consumer<OriginalResult<User>> callback) {
    User user = new User(null, name, password, Settings.USER_INIT_POINT);
    SignupRepository signupRepository = new SignupRepository(user, callback);
    signupRepository.start();
  }
}
