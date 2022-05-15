package services;

import java.util.List;
import java.util.function.Consumer;

import repositories.RoomCreateRepository;
import repositories.RoomEnterRepository;
import repositories.RoomListRepository;
import utils.OriginalResult;
import entities.*;

public class RoomService {
  private RoomService() {
  }

  static public void enter(Room room, User user, Consumer<OriginalResult<Room>> callback) {
    RoomEnterRepository roomEnterRepository = new RoomEnterRepository(user, room, callback);
    roomEnterRepository.start();
  }

  static public void create(String name, User user, Consumer<OriginalResult<Room>> callback) {
    RoomCreateRepository roomCreateRepository = new RoomCreateRepository(user, name, callback);
    roomCreateRepository.start();
  }

  static public void getRooms(Consumer<OriginalResult<List<Room>>> callback) {
    RoomListRepository roomListRepository = new RoomListRepository(callback);
    roomListRepository.start();
  }
}
