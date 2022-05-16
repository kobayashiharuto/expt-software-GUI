package repositories;

import java.util.function.Consumer;

import entities.User;
import exceptions.UnknownException;
import utils.OriginalResult;

public class SignupRepository extends Thread {
  private final String name;
  private final String password;
  private final int point;
  private final Consumer<OriginalResult<User>> callback;

  public SignupRepository(String name, String password, int point, Consumer<OriginalResult<User>> callback) {
    this.name = name;
    this.password = password;
    this.point = point;
    this.callback = callback;
  }

  @Override
  public void run() {
    try {
      System.out.println("signup start: " + name);
      final User user = signup(name, password, point);
      final OriginalResult<User> result = new OriginalResult<User>(user);
      callback.accept(result);
    } catch (InterruptedException e) {
      System.out.println("signup faild");
      final OriginalResult<User> result = new OriginalResult<User>(new UnknownException());
      callback.accept(result);
    }
  }

  static private User signup(String name, String password, int point) throws InterruptedException {
    Thread.sleep(1000);
    return new User("1231", name, password, point);
  }
}
