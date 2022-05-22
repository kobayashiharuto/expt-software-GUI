package jp.waseda.asagi.kobayashi.repositories;

import java.util.function.Consumer;

import jp.waseda.asagi.kobayashi.entities.Room;
import jp.waseda.asagi.kobayashi.entities.User;
import jp.waseda.asagi.kobayashi.exceptions.UnknownException;
import jp.waseda.asagi.kobayashi.utils.OriginalResult;

public class RoomEnterRepository extends Thread {
  private final User user;
  private final Room room;
  private final Consumer<OriginalResult<Room>> callback;

  public RoomEnterRepository(User user, Room room, Consumer<OriginalResult<Room>> callback) {
    this.user = user;
    this.room = room;
    this.callback = callback;
  }

  @Override
  public void run() {
    try {
      System.out.println("enter room start -> user: " + user.name + " room: " + room.name);
      Thread.sleep(1000); // 入室処理を記述
      final OriginalResult<Room> result = new OriginalResult<Room>(room);
      callback.accept(result);
    } catch (InterruptedException e) {
      System.out.println("enter room faild");
      final OriginalResult<Room> result = new OriginalResult<Room>(new UnknownException());
      callback.accept(result);
    }
  }
}