package jp.waseda.asagi.kobayashi.services;

import java.util.function.Consumer;

import jp.waseda.asagi.kobayashi.client.AudioListener;
import jp.waseda.asagi.kobayashi.client.AudioStreamer;
import jp.waseda.asagi.kobayashi.entities.Listener;
import jp.waseda.asagi.kobayashi.entities.User;
import jp.waseda.asagi.kobayashi.repositories.RoomListenForListenerRepository;
import jp.waseda.asagi.kobayashi.repositories.RoomListenForStreamerRepository;
import jp.waseda.asagi.kobayashi.utils.OriginalResult;

public class RoomListenService {
  private RoomListenForListenerRepository listenForListenerRepo;
  private RoomListenForStreamerRepository listenForStreamerRepo;
  private AudioStreamer audioStreamer;
  private AudioListener audioListener;

  public void setupForListener(User user,
      String roomID, Consumer<OriginalResult<?>> callback) {
    audioListener = new AudioListener();
    audioListener.start();
    listenForListenerRepo = new RoomListenForListenerRepository(user, roomID, callback);
    listenForListenerRepo.start();
  }

  public void setupForStreamer(User user,
      String roomID, Consumer<OriginalResult<?>> callback) {
    audioStreamer = new AudioStreamer();
    audioStreamer.start();
    listenForStreamerRepo = new RoomListenForStreamerRepository(user, roomID, callback);
    listenForStreamerRepo.start();
  }

  public void addlistener(Listener listener) {
    audioStreamer.addlistener(listener);
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
    if (audioListener != null) {
      audioListener.listenStop();
      audioListener = null;
    }
  }
}
