package jp.waseda.asagi.kobayashi.repositories;

import java.io.IOException;
import java.util.function.Consumer;

import jp.waseda.asagi.kobayashi.client.ServerClient;
import jp.waseda.asagi.kobayashi.entities.User;
import jp.waseda.asagi.kobayashi.exceptions.DuplicatedException;
import jp.waseda.asagi.kobayashi.exceptions.NetworkException;
import jp.waseda.asagi.kobayashi.utils.OriginalResult;
import jp.waseda.asagi.kobayashi.utils.RequestParser;
import jp.waseda.asagi.kobayashi.utils.ResponceParser;

public class SignupRepository extends Thread {
  private final String name;
  private final String password;
  private final Consumer<OriginalResult<User>> callback;

  public SignupRepository(String name, String password, Consumer<OriginalResult<User>> callback) {
    this.name = name;
    this.password = password;
    this.callback = callback;
  }

  @Override
  public void run() {
    try {
      System.out.println("signup start: " + name);
      final User user = signup(name, password);
      final OriginalResult<User> result = new OriginalResult<User>(user);
      callback.accept(result);
    } catch (IOException e) {
      final OriginalResult<User> result = new OriginalResult<User>(new NetworkException());
      callback.accept(result);
    } catch (DuplicatedException e) {
      final OriginalResult<User> result = new OriginalResult<User>(new DuplicatedException());
      callback.accept(result);
    }
  }

  // static private User signup(String name, String password, int point) throws
  // InterruptedException {
  // Thread.sleep(1000);
  // return new User("1231", name, password, point);
  // }

  static private User signup(String name, String password) throws IOException, DuplicatedException {
    final String request = RequestParser.registration(name, password);
    final String responce = ServerClient.getInstance().send(request);
    final User user = ResponceParser.signup(responce, name, password);
    return user;
  }
}
