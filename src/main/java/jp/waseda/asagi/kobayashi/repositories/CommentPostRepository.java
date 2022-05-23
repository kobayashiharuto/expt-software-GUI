package jp.waseda.asagi.kobayashi.repositories;

import java.io.IOException;
import java.util.function.Consumer;

import jp.waseda.asagi.kobayashi.client.ServerClient;
import jp.waseda.asagi.kobayashi.entities.User;
import jp.waseda.asagi.kobayashi.exceptions.UnknownException;
import jp.waseda.asagi.kobayashi.utils.OriginalResult;
import jp.waseda.asagi.kobayashi.utils.RequestParser;
import jp.waseda.asagi.kobayashi.utils.ResponceParser;

public class CommentPostRepository extends Thread {
  private final User user;
  private final String roomID;
  private final String comment;
  private final Consumer<OriginalResult<Boolean>> callback;

  public CommentPostRepository(User user, String roomID, String comment, Consumer<OriginalResult<Boolean>> callback) {
    this.user = user;
    this.roomID = roomID;
    this.comment = comment;
    this.callback = callback;
  }

  @Override
  public void run() {
    try {
      System.out.println("comment send: " + comment);
      sendComment(user, comment, roomID);
      final OriginalResult<Boolean> result = new OriginalResult<Boolean>(true);
      callback.accept(result);
    } catch (IOException e) {
      final OriginalResult<Boolean> result = new OriginalResult<Boolean>(new UnknownException());
      callback.accept(result);
    } catch (UnknownException e) {
      final OriginalResult<Boolean> result = new OriginalResult<Boolean>(new UnknownException());
      callback.accept(result);
    }
  }

  // static private void sendComment(User user, String comment) throws
  // InterruptedException {
  // Thread.sleep(1000); // コメント送信処理を記述
  // }

  static private boolean sendComment(User user, String comment, String roomID) throws IOException, UnknownException {
    final String request = RequestParser.comment(user.id, user.name, roomID, comment);
    final String responce = ServerClient.getInstance().send(request);
    final boolean result = ResponceParser.postComment(responce);
    return result;
  }
}
