package jp.waseda.asagi.kobayashi.repositories;

import java.io.IOException;
import java.util.function.Consumer;

import jp.waseda.asagi.kobayashi.client.RoomClient;
import jp.waseda.asagi.kobayashi.entities.Comment;
import jp.waseda.asagi.kobayashi.entities.Listener;
import jp.waseda.asagi.kobayashi.entities.Tip;
import jp.waseda.asagi.kobayashi.entities.User;
import jp.waseda.asagi.kobayashi.exceptions.RoomCloseException;
import jp.waseda.asagi.kobayashi.utils.OriginalResult;
import jp.waseda.asagi.kobayashi.utils.ResponceParser;

public class RoomListenForStreamerRepository extends Thread {
  private final User user;
  private final String roomID;
  private boolean onStreaming = false;
  private final Consumer<OriginalResult<?>> callback;

  public RoomListenForStreamerRepository(User user,
      String roomID, Consumer<OriginalResult<?>> callback) {
    this.user = user;
    this.roomID = roomID;
    this.callback = callback;
  }

  @Override
  public void run() {
    onStreaming = true;
    while (onStreaming) {
      try {
        final Object roomData = listenRoomData(user, roomID);
        if (!onStreaming) {
          break;
        }
        if (roomData == null) {
          continue;
        }
        final OriginalResult<Object> result = new OriginalResult<Object>(roomData);
        callback.accept(result);
      } catch (IOException e) {
        onStreaming = false;
        break;
      } catch (RoomCloseException e) {
        onStreaming = false;
        final OriginalResult<Object> result = new OriginalResult<Object>(new RoomCloseException());
        callback.accept(result);
        break;
      }
    }
  }

  public void dispose() {
    onStreaming = false;
    RoomClient.getInstance().closeRoomSocket();
  }

  static private Object listenRoomData(User user, String roomID) throws IOException, RoomCloseException {
    final String response = RoomClient.getInstance().read();
    if (response.matches("#comment#(.*)")) {
      final Comment comment = ResponceParser.listenComment(response);
      return comment;
    }
    if (response.matches("#tip#(.*)")) {
      final Tip tip = ResponceParser.listenTip(response);
      return tip;
    }
    if (response.matches("#startListen#(.*)")) {
      final Listener listener = ResponceParser.startListen(response);
      return listener;
    }
    if (response.matches("#stopListen#(.*)")) {
      final String id = ResponceParser.stopListen(response);
      return id;
    }
    if (response.matches("#LiveIsStopped#(.*)")) {
      throw new RoomCloseException();
    }
    return null;
  }
}
