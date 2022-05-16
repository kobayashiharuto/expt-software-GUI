package services;

import java.util.function.Consumer;

import entities.User;
import repositories.CommentPostRepository;
import utils.OriginalResult;

public class CommentPostService {

  public void post(User user,
      String comment, Consumer<OriginalResult<Boolean>> callback) {
    CommentPostRepository postRepo = new CommentPostRepository(user, comment, callback);
    postRepo.start();
  }
}
