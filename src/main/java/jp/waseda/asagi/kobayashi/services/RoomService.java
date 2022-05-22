package jp.waseda.asagi.kobayashi.services;

import java.util.List;
import java.util.function.Consumer;

import jp.waseda.asagi.kobayashi.repositories.RoomCreateRepository;
import jp.waseda.asagi.kobayashi.repositories.RoomEnterRepository;
import jp.waseda.asagi.kobayashi.repositories.RoomListRepository;
import jp.waseda.asagi.kobayashi.utils.OriginalResult;
import jp.waseda.asagi.kobayashi.entities.*;

public class RoomService {

  public void enter(Room room, User user, Consumer<OriginalResult<Room>> callback) {
    RoomEnterRepository roomEnterRepository = new RoomEnterRepository(user, room, callback);
    roomEnterRepository.start();
  }

  public void create(int myListenPort, String roomName, Consumer<OriginalResult<Room>> callback) {
    RoomCreateRepository roomCreateRepository = new RoomCreateRepository(myListenPort, roomName, callback);
    roomCreateRepository.start();
  }

  public void getRooms(Consumer<OriginalResult<List<Room>>> callback) {
    RoomListRepository roomListRepository = new RoomListRepository(callback);
    roomListRepository.start();
  }
}
