package jp.waseda.asagi.kobayashi.repositories;

import java.io.IOException;
import java.util.function.Consumer;

import jp.waseda.asagi.kobayashi.client.ServerClient;
import jp.waseda.asagi.kobayashi.entities.User;
import jp.waseda.asagi.kobayashi.exceptions.ForbiddenException;
import jp.waseda.asagi.kobayashi.exceptions.NetworkException;
import jp.waseda.asagi.kobayashi.exceptions.UnknownException;
import jp.waseda.asagi.kobayashi.utils.OriginalResult;
import jp.waseda.asagi.kobayashi.utils.RequestParser;
import jp.waseda.asagi.kobayashi.utils.ResponceParser;

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
      final OriginalResult<User> result = new OriginalResult<User>(new UnknownException());
      callback.accept(result);
    } catch (IOException e) {
      final OriginalResult<User> result = new OriginalResult<User>(new NetworkException());
      callback.accept(result);
    } catch (ForbiddenException e) {
      final OriginalResult<User> result = new OriginalResult<User>(new ForbiddenException());
      callback.accept(result);
    }
  }

  // private User login(String name, String password) throws InterruptedException
  // {
  // Thread.sleep(1000);
  // return new User("1231", name, password, 100);
  // }

  private User login(String name, String password) throws InterruptedException, IOException, ForbiddenException {
    final String request = RequestParser.login(name, password);
    final String responce = ServerClient.getInstance().send(request);
    final User user = ResponceParser.login(responce, name, password);
    return user;
  }
}
