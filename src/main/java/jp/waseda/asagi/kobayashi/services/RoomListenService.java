package jp.waseda.asagi.kobayashi.services;

import java.util.function.Consumer;

import jp.waseda.asagi.kobayashi.entities.Comment;
import jp.waseda.asagi.kobayashi.entities.User;
import jp.waseda.asagi.kobayashi.repositories.RoomListenRepository;
import jp.waseda.asagi.kobayashi.utils.OriginalResult;

public class RoomListenService {
  RoomListenRepository listenRepo;

  public void listen(User user,
      String roomID, Consumer<OriginalResult<Comment>> callback) {
    listenRepo = new RoomListenRepository(user, roomID, callback);
    listenRepo.start();
  }

  public void dispose() {
    listenRepo.dispose();
  }
}
