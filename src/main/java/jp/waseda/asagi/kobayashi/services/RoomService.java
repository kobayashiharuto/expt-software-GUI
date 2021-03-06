package jp.waseda.asagi.kobayashi.services;

import java.util.List;
import java.util.function.Consumer;

import jp.waseda.asagi.kobayashi.repositories.RoomCreateRepository;
import jp.waseda.asagi.kobayashi.repositories.RoomEnterRepository;
import jp.waseda.asagi.kobayashi.repositories.RoomListRepository;
import jp.waseda.asagi.kobayashi.repositories.RoomQuitRepository;
import jp.waseda.asagi.kobayashi.repositories.StreamingStopRepository;
import jp.waseda.asagi.kobayashi.utils.OriginalResult;
import jp.waseda.asagi.kobayashi.entities.*;

public class RoomService {

  public void enter(Room room, User user, Consumer<OriginalResult<Room>> callback) {
    RoomEnterRepository roomEnterRepository = new RoomEnterRepository(user, room, callback);
    roomEnterRepository.start();
  }

  public void create(String uid, int myListenPort, String roomName, Consumer<OriginalResult<Room>> callback) {
    RoomCreateRepository roomCreateRepository = new RoomCreateRepository(uid, myListenPort, roomName, callback);
    roomCreateRepository.start();
  }

  public void getRooms(Consumer<OriginalResult<List<Room>>> callback) {
    RoomListRepository roomListRepository = new RoomListRepository(callback);
    roomListRepository.start();
  }

  public void quitRoom(String roomID, String userID, Consumer<OriginalResult<Boolean>> callback) {
    RoomQuitRepository roomQuitRepository = new RoomQuitRepository(roomID, userID, callback);
    roomQuitRepository.start();
  }

  public void stopStreaming(String roomID, Consumer<OriginalResult<Boolean>> callback) {
    StreamingStopRepository streamingStopRepository = new StreamingStopRepository(roomID, callback);
    streamingStopRepository.start();
  }
}
