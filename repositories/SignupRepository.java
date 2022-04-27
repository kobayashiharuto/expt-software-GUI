package repositories;

import java.util.function.Consumer;

import entities.User;
import exceptions.UnknownException;
import utils.OriginalResult;

public class SignupRepository extends Thread {
  private final User user;
  private final Consumer<OriginalResult<User>> callback;

  public SignupRepository(User user, Consumer<OriginalResult<User>> callback) {
    this.user = user;
    this.callback = callback;
  }

  @Override
  public void run() {
    try {
      System.out.println("signup start: " + user.name);
      Thread.sleep(1000); // ここにサインアップ処理を記述する
      user.id = "123";
      final OriginalResult<User> result = new OriginalResult<User>(user);
      callback.accept(result);
    } catch (InterruptedException e) {
      System.out.println("signup faild");
      final OriginalResult<User> result = new OriginalResult<User>(new UnknownException());
      callback.accept(result);
    }
  }
}
