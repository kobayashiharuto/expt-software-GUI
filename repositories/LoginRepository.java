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
      Thread.sleep(1000);
      user.id = "123";
      final OriginalResult<User> result = new OriginalResult<User>(user);
      callback.accept(result);
    } catch (InterruptedException e) {
      final OriginalResult<User> result = new OriginalResult<User>(new UnknownException());
      callback.accept(result);
    }
  }
}
