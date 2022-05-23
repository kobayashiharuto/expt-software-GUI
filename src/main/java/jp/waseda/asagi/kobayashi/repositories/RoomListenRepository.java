package jp.waseda.asagi.kobayashi.repositories;

import java.io.IOException;
import java.util.function.Consumer;

import jp.waseda.asagi.kobayashi.client.RoomClient;
import jp.waseda.asagi.kobayashi.entities.Comment;
import jp.waseda.asagi.kobayashi.entities.User;
import jp.waseda.asagi.kobayashi.exceptions.RoomCloseException;
import jp.waseda.asagi.kobayashi.utils.OriginalResult;
import jp.waseda.asagi.kobayashi.utils.ResponceParser;

public class RoomListenRepository extends Thread {
  private final User user;
  private final String roomID;
  private boolean onStreaming = false;
  private final Consumer<OriginalResult<Comment>> callback;

  public RoomListenRepository(User user,
      String roomID, Consumer<OriginalResult<Comment>> callback) {
    this.user = user;
    this.roomID = roomID;
    this.callback = callback;
  }

  @Override
  public void run() {
    onStreaming = true;
    while (onStreaming) {
      try {
        System.out.println("listen comment start: " + user.name);
        final Comment comment = streamingComment(user, roomID);
        if (!onStreaming) {
          break;
        }
        if (comment == null) {
          continue;
        }
        final OriginalResult<Comment> result = new OriginalResult<Comment>(comment);
        callback.accept(result);
      } catch (IOException e) {
        onStreaming = false;
        break;
      } catch (RoomCloseException e) {
        onStreaming = false;
        final OriginalResult<Comment> result = new OriginalResult<Comment>(new RoomCloseException());
        callback.accept(result);
        break;
      }
    }
  }

  public void dispose() {
    onStreaming = false;
    RoomClient.getInstance().closeRoomSocket();
  }

  // static private Comment streamingComment(User user, String roomID) throws
  // InterruptedException {
  // Thread.sleep(1000); // TCP通信で待機する
  // final Comment comment = new Comment("awda", user.name, "awdawdad");
  // return comment;
  // }

  static private Comment streamingComment(User user, String roomID) throws IOException, RoomCloseException {
    final String response = RoomClient.getInstance().read();
    if (response.matches("#comment#(.*)")) {
      final Comment comment = ResponceParser.listenComment(response);
      return comment;
    }
    if (response.matches("#LiveIsStopped#(.*)")) {
      throw new RoomCloseException();
    }
    return null;
  }
}
