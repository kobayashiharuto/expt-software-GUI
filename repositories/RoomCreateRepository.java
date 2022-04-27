package repositories;

import java.util.function.Consumer;

import entities.Room;
import entities.User;
import exceptions.UnknownException;
import utils.OriginalResult;

public class RoomCreateRepository extends Thread {
  private final User user;
  private final Consumer<OriginalResult<Room>> callback;

  public RoomCreateRepository(User user, Consumer<OriginalResult<Room>> callback) {
    this.user = user;
    this.callback = callback;
  }

  @Override
  public void run() {
    try {
      System.out.println("room create start: " + user.name);
      Thread.sleep(1000); // 部屋作成処理を記述
      final Room room = Room.generateMockRoom();
      final OriginalResult<Room> result = new OriginalResult<Room>(room);
      callback.accept(result);
    } catch (InterruptedException e) {
      System.out.println("create room faild");
      final OriginalResult<Room> result = new OriginalResult<Room>(new UnknownException());
      callback.accept(result);
    }
  }
}
