package services;

import java.util.function.Consumer;

import repositories.RoomCreateRepository;
import repositories.RoomEnterRepository;
import utils.OriginalResult;
import entities.*;

public class RoomService {
  private RoomService() {
  }

  static public void enter(String roomNum, User user, Consumer<OriginalResult<Room>> callback) {
    RoomEnterRepository roomEnterRepository = new RoomEnterRepository(user, callback);
    roomEnterRepository.start();
  }
  
  static public void create(String roomNum, User user, Consumer<OriginalResult<Room>> callback) {
    RoomCreateRepository roomCreateRepository = new RoomCreateRepository(user, callback);
    roomCreateRepository.start();
  }
}
