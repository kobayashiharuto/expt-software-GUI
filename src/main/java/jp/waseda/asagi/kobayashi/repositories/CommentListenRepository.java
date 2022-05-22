package jp.waseda.asagi.kobayashi.repositories;

import java.io.IOException;
import java.util.function.Consumer;

import jp.waseda.asagi.kobayashi.client.ServerClient;
import jp.waseda.asagi.kobayashi.entities.Comment;
import jp.waseda.asagi.kobayashi.entities.User;
import jp.waseda.asagi.kobayashi.exceptions.NetworkException;
import jp.waseda.asagi.kobayashi.utils.OriginalResult;
import jp.waseda.asagi.kobayashi.utils.ResponceParser;

public class CommentListenRepository extends Thread {
  private final User user;
  private final String roomID;
  private boolean onStreaming = false;
  private final Consumer<OriginalResult<Comment>> callback;

  public CommentListenRepository(User user,
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
        System.out.println("listen comments faild");
        final OriginalResult<Comment> result = new OriginalResult<Comment>(new NetworkException());
        callback.accept(result);
      }
    }
  }

  public void dispose() {
    onStreaming = false;
  }

  // static private Comment streamingComment(User user, String roomID) throws
  // InterruptedException {
  // Thread.sleep(1000); // TCP通信で待機する
  // final Comment comment = new Comment("awda", user.name, "awdawdad");
  // return comment;
  // }

  static private Comment streamingComment(User user, String roomID) throws IOException {
    final String response = ServerClient.getInstance().receiveLine.readLine();
    if (!response.matches("#comment#(.*)")) {
      return null;
    }
    final Comment comment = ResponceParser.listenComment(response);
    return comment;
  }
}
