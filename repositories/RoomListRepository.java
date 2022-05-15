package repositories;

import java.util.List;
import java.util.function.Consumer;

import entities.Room;
import exceptions.UnknownException;
import utils.OriginalResult;
import utils.RequestParser;
import utils.ResponceParser;

public class RoomListRepository extends Thread {
  private final Consumer<OriginalResult<List<Room>>> callback;

  public RoomListRepository(Consumer<OriginalResult<List<Room>>> callback) {
    this.callback = callback;
  }

  @Override
  public void run() {
    try {
      System.out.println("get rooms start");
      final String request = RequestParser.getroooms();
      final List<Room> rooms = generateMockRoomList(request);
      final OriginalResult<List<Room>> result = new OriginalResult<List<Room>>(rooms);
      callback.accept(result);
    } catch (InterruptedException e) {
      System.out.println("enter room faild");
      final OriginalResult<List<Room>> result = new OriginalResult<List<Room>>(new UnknownException());
      callback.accept(result);
    }
  }

  private static List<Room> generateMockRoomList(String reqeust) throws InterruptedException {
    Thread.sleep(1000);
    final List<Room> rooms = ResponceParser.getRooms("");
    return rooms;
  }
}
