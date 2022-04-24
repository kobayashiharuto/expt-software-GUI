package repositories;

import java.util.concurrent.Callable;

public class LoginRepository implements Callable<Boolean> {
  @Override
  public Boolean call() throws InterruptedException {
    Thread.sleep(1000);
    System.out.println("login success");
    return true;
  }
}
