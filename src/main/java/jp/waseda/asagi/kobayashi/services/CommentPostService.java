package jp.waseda.asagi.kobayashi.services;

import java.util.function.Consumer;

import jp.waseda.asagi.kobayashi.entities.User;
import jp.waseda.asagi.kobayashi.repositories.CommentPostRepository;
import jp.waseda.asagi.kobayashi.utils.OriginalResult;

public class CommentPostService {

  public void post(User user,
      String comment, Consumer<OriginalResult<Boolean>> callback) {
    CommentPostRepository postRepo = new CommentPostRepository(user, comment, callback);
    postRepo.start();
  }
}
