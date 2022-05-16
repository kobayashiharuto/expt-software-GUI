package services;

import java.util.function.Consumer;

import entities.Comment;
import entities.User;
import repositories.CommentListenRepository;
import utils.OriginalResult;

public class CommentListenService {
  CommentListenRepository listenRepo;

  public void listen(User user,
      String roomID, Consumer<OriginalResult<Comment>> callback) {
    listenRepo = new CommentListenRepository(user, roomID, callback);
    listenRepo.start();
  }

  public void dispose() {
    listenRepo.dispose();
  }
}
