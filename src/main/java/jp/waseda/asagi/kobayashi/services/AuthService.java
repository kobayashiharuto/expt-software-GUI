package jp.waseda.asagi.kobayashi.services;

import java.util.function.Consumer;

import jp.waseda.asagi.kobayashi.repositories.LoginRepository;
import jp.waseda.asagi.kobayashi.repositories.SignupRepository;
import jp.waseda.asagi.kobayashi.settings.Settings;
import jp.waseda.asagi.kobayashi.utils.OriginalResult;
import jp.waseda.asagi.kobayashi.entities.*;

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
