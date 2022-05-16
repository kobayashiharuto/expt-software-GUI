package services;

import java.util.function.Consumer;

import repositories.LoginRepository;
import repositories.SignupRepository;
import settings.Settings;
import utils.OriginalResult;
import entities.*;

public class AuthService {

  public void login(String name, String password, Consumer<OriginalResult<User>> callback) {
    LoginRepository loginRepository = new LoginRepository(name, password, callback);
    loginRepository.start();
  }

  public void signup(String name, String password, Consumer<OriginalResult<User>> callback) {
    SignupRepository signupRepository = new SignupRepository(name, password, Settings.USER_INIT_POINT, callback);
    signupRepository.start();
  }
}
