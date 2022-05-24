package jp.waseda.asagi.kobayashi.services;

import java.util.function.Consumer;

import jp.waseda.asagi.kobayashi.entities.Comment;
import jp.waseda.asagi.kobayashi.entities.User;
import jp.waseda.asagi.kobayashi.repositories.RoomListenForListenerRepository;
import jp.waseda.asagi.kobayashi.repositories.RoomListenForStreamerRepository;
import jp.waseda.asagi.kobayashi.utils.OriginalResult;

public class RoomListenService {
  RoomListenForListenerRepository listenForListenerRepo;
  RoomListenForStreamerRepository listenForStreamerRepo;

  public void listenForListener(User user,
      String roomID, Consumer<OriginalResult<Comment>> callback) {
    listenForListenerRepo = new RoomListenForListenerRepository(user, roomID, callback);
    listenForListenerRepo.start();
  }

  public void listenForStremaer(User user,
      String roomID, Consumer<OriginalResult<Comment>> callback) {
    listenForStreamerRepo = new RoomListenForStreamerRepository(user, roomID, callback);
    listenForStreamerRepo.start();
  }

  public void dispose() {
    if (listenForListenerRepo != null) {
      listenForListenerRepo.dispose();
      listenForListenerRepo = null;
    }
    if (listenForStreamerRepo != null) {
      listenForStreamerRepo.dispose();
      listenForStreamerRepo = null;
    }
  }
}
