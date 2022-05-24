package jp.waseda.asagi.kobayashi.services;

import java.util.function.Consumer;

import jp.waseda.asagi.kobayashi.client.AudioStreamer;
import jp.waseda.asagi.kobayashi.entities.Comment;
import jp.waseda.asagi.kobayashi.entities.Listener;
import jp.waseda.asagi.kobayashi.entities.User;
import jp.waseda.asagi.kobayashi.repositories.RoomListenForListenerRepository;
import jp.waseda.asagi.kobayashi.repositories.RoomListenForStreamerRepository;
import jp.waseda.asagi.kobayashi.utils.OriginalResult;

public class RoomListenService {
  private RoomListenForListenerRepository listenForListenerRepo;
  private RoomListenForStreamerRepository listenForStreamerRepo;
  private AudioStreamer audioStreamer;

  public void setupForListener(User user,
      String roomID, Consumer<OriginalResult<Comment>> callback) {
    listenForListenerRepo = new RoomListenForListenerRepository(user, roomID, callback);
    listenForListenerRepo.start();
  }

  public void setupForStreamer(User user,
      String roomID, Consumer<OriginalResult<?>> callback) {
    listenForStreamerRepo = new RoomListenForStreamerRepository(user, roomID, callback);
    audioStreamer = new AudioStreamer();
    audioStreamer.start();
    listenForStreamerRepo.start();
  }

  public void addLisnter(Listener lisnter) {
    audioStreamer.addListner(lisnter);
  }

  public void removeLisnter(String uid) {
    audioStreamer.removeListner(uid);
  }

  public void dispose() {
    if (listenForListenerRepo != null) {
      listenForListenerRepo.dispose();
      listenForListenerRepo = null;
    }
    if (audioStreamer != null) {
      audioStreamer.streamStop();
      audioStreamer = null;
    }
    if (listenForStreamerRepo != null) {
      listenForStreamerRepo.dispose();
      listenForStreamerRepo = null;
    }
  }
}
