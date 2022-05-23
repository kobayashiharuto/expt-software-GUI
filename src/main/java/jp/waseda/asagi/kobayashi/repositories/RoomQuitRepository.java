package jp.waseda.asagi.kobayashi.repositories;

import java.io.IOException;
import java.util.function.Consumer;

import jp.waseda.asagi.kobayashi.client.ServerClient;
import jp.waseda.asagi.kobayashi.exceptions.NetworkException;
import jp.waseda.asagi.kobayashi.exceptions.UnknownException;
import jp.waseda.asagi.kobayashi.utils.OriginalResult;
import jp.waseda.asagi.kobayashi.utils.RequestParser;
import jp.waseda.asagi.kobayashi.utils.ResponceParser;

public class RoomQuitRepository extends Thread {
  private final String roomID;
  private final Consumer<OriginalResult<Boolean>> callback;

  public RoomQuitRepository(String roomID, Consumer<OriginalResult<Boolean>> callback) {
    this.roomID = roomID;
    this.callback = callback;
  }

  @Override
  public void run() {
    try {
      final Boolean success = stop(roomID);
      final OriginalResult<Boolean> result = new OriginalResult<Boolean>(success);
      callback.accept(result);
    } catch (IOException e) {
      final OriginalResult<Boolean> result = new OriginalResult<Boolean>(new NetworkException());
      callback.accept(result);
    } catch (UnknownException e) {
      final OriginalResult<Boolean> result = new OriginalResult<Boolean>(new UnknownException());
      callback.accept(result);
    }
  }

  // private User stop(String name, String password) throws InterruptedException
  // {
  // Thread.sleep(1000);
  // return new User("1231", name, password, 100);
  // }

  private Boolean stop(String roomID) throws IOException, UnknownException {
    final String request = RequestParser.quitroom(roomID);
    System.out.println("awdawda");
    final String responce = ServerClient.getInstance().send(request);
    final boolean success = ResponceParser.quitroom(responce);
    return success;
  }
}
