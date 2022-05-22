package jp.waseda.asagi.kobayashi.repositories;

import java.util.function.Consumer;

import jp.waseda.asagi.kobayashi.entities.Room;
import jp.waseda.asagi.kobayashi.entities.User;
import jp.waseda.asagi.kobayashi.exceptions.UnknownException;
import jp.waseda.asagi.kobayashi.settings.Settings;
import jp.waseda.asagi.kobayashi.utils.OriginalResult;

public class RoomCreateRepository extends Thread {
  private final User user;
  private final String name;
  private final Consumer<OriginalResult<Room>> callback;

  public RoomCreateRepository(User user, String name, Consumer<OriginalResult<Room>> callback) {
    this.user = user;
    this.name = name;
    this.callback = callback;
  }

  @Override
  public void run() {
    try {
      System.out.println("room create start: " + user.name);
      final Room room = getRoom(name);
      final OriginalResult<Room> result = new OriginalResult<Room>(room);
      callback.accept(result);
    } catch (InterruptedException e) {
      System.out.println("create room faild");
      final OriginalResult<Room> result = new OriginalResult<Room>(new UnknownException());
      callback.accept(result);
    }
  }

  static private Room getRoom(String name) throws InterruptedException {
    Thread.sleep(1000); // 部屋作成処理を記述
    final Room room = new Room(name, "1231", Settings.CLIENT_IP, Settings.CLIENT_SEND_PORT);
    return room;
  }
}