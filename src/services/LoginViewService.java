package services;

import java.util.concurrent.*;

import repositories.LoginRepository;

public class LoginViewService {
  public LoginViewService() {
  }

  public void login(String name, String password) throws InterruptedException, ExecutionException, TimeoutException {
    LoginRepository loginRepository = new LoginRepository();

    ExecutorService executorService = Executors.newSingleThreadExecutor();
    Future<Boolean> future = executorService.submit(loginRepository);
    System.out.println("start");
    Boolean result = future.get(500000, TimeUnit.MILLISECONDS);
    System.out.println("end");
  }
}
