package repositories;

import java.util.function.Consumer;

import entities.User;

public class LoginRepository extends Thread {
  private final User user;
  private final Consumer<Boolean> callback;

  public LoginRepository(User user, Consumer<Boolean> callback) {
    this.user = user;
    this.callback = callback;
  }

  @Override
  public void run() {
    try {
      System.out.println("login start: " + user.name);
      Thread.sleep(1000);
      callback.accept(true);
    } catch (InterruptedException e) {
      callback.accept(false);
    }
  }
}
