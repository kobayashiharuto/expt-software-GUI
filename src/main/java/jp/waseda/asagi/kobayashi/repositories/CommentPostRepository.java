package jp.waseda.asagi.kobayashi.repositories;

import java.util.function.Consumer;

import jp.waseda.asagi.kobayashi.entities.User;
import jp.waseda.asagi.kobayashi.exceptions.UnknownException;
import jp.waseda.asagi.kobayashi.utils.OriginalResult;

public class CommentPostRepository extends Thread {
  private final User user;
  private final String comment;
  private final Consumer<OriginalResult<Boolean>> callback;

  public CommentPostRepository(User user, String comment, Consumer<OriginalResult<Boolean>> callback) {
    this.user = user;
    this.comment = comment;
    this.callback = callback;
  }

  @Override
  public void run() {
    try {
      System.out.println("comment send: " + comment);
      sendComment(user, comment);
      final OriginalResult<Boolean> result = new OriginalResult<Boolean>(true);
      callback.accept(result);
    } catch (InterruptedException e) {
      System.out.println("comment faild");
      final OriginalResult<Boolean> result = new OriginalResult<Boolean>(new UnknownException());
      callback.accept(result);
    }
  }

  static private void sendComment(User user, String comment) throws InterruptedException {
    Thread.sleep(1000); // コメント送信処理を記述
  }
}
