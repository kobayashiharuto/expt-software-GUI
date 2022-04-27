package repositories;

import java.util.function.Consumer;

import entities.User;
import exceptions.UnknownException;
import utils.OriginalResult;

public class LoginRepository extends Thread {
  private final User user;
  private final Consumer<OriginalResult<User>> callback;

  public LoginRepository(User user, Consumer<OriginalResult<User>> callback) {
    this.user = user;
    this.callback = callback;
  }

  @Override
  public void run() {
    try {
      System.out.println("login start: " + user.name);
      Thread.sleep(1000); // ログイン処理を記述
      final User userData = User.generateMockUser();
      final OriginalResult<User> result = new OriginalResult<User>(userData);
      callback.accept(result);
    } catch (InterruptedException e) {
      System.out.println("login faild");
      final OriginalResult<User> result = new OriginalResult<User>(new UnknownException());
      callback.accept(result);
    }
  }
}