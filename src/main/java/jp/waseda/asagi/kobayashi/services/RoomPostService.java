package jp.waseda.asagi.kobayashi.services;

import java.util.function.Consumer;

import jp.waseda.asagi.kobayashi.entities.User;
import jp.waseda.asagi.kobayashi.repositories.CommentPostRepository;
import jp.waseda.asagi.kobayashi.repositories.TipPostRepository;
import jp.waseda.asagi.kobayashi.utils.OriginalResult;

public class RoomPostService {

  public void postTip(User user, String roomID,
      int amount, Consumer<OriginalResult<Integer>> callback) {
    TipPostRepository postRepo = new TipPostRepository(user, roomID, amount, callback);
    postRepo.start();
  }

  public void postComment(User user, String roomID,
      String comment, Consumer<OriginalResult<Boolean>> callback) {
    CommentPostRepository postRepo = new CommentPostRepository(user, roomID, comment, callback);
    postRepo.start();
  }
}
