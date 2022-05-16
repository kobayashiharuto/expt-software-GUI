package repositories;

import java.util.function.Consumer;

import entities.User;
import exceptions.UnknownException;
import utils.OriginalResult;

public class LoginRepository extends Thread {
  private final String name;
  private final String password;
  private final Consumer<OriginalResult<User>> callback;

  public LoginRepository(String name, String password, Consumer<OriginalResult<User>> callback) {
    this.name = name;
    this.password = password;
    this.callback = callback;
  }

  @Override
  public void run() {
    try {
      System.out.println("login start, name: " + name + " password: " + password);
      final User user = login(name, password);
      final OriginalResult<User> result = new OriginalResult<User>(user);
      callback.accept(result);
    } catch (InterruptedException e) {
      System.out.println("login faild");
      final OriginalResult<User> result = new OriginalResult<User>(new UnknownException());
      callback.accept(result);
    }
  }

  static private User login(String name, String password) throws InterruptedException {
    Thread.sleep(1000);
    return new User("1231", name, password, 100);
  }
}
