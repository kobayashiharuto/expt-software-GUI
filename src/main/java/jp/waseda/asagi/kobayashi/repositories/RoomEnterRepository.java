package jp.waseda.asagi.kobayashi.repositories;

import java.io.IOException;
import java.util.function.Consumer;

import jp.waseda.asagi.kobayashi.client.ServerClient;
import jp.waseda.asagi.kobayashi.entities.Room;
import jp.waseda.asagi.kobayashi.entities.User;
import jp.waseda.asagi.kobayashi.exceptions.NetworkException;
import jp.waseda.asagi.kobayashi.exceptions.NotExistRoomException;
import jp.waseda.asagi.kobayashi.utils.OriginalResult;
import jp.waseda.asagi.kobayashi.utils.RequestParser;
import jp.waseda.asagi.kobayashi.utils.ResponceParser;

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
      final Room room_ = enterRoom(user, room);
      final OriginalResult<Room> result = new OriginalResult<Room>(room_);
      callback.accept(result);
    } catch (IOException e) {
      final OriginalResult<Room> result = new OriginalResult<Room>(new NetworkException());
      callback.accept(result);
    } catch (NotExistRoomException e) {
      final OriginalResult<Room> result = new OriginalResult<Room>(new NotExistRoomException());
      callback.accept(result);
    }
  }

  static private Room enterRoom(User user, Room room) throws IOException, NotExistRoomException {
    final String request = RequestParser.getroomip(room.id);
    final String responce = ServerClient.getInstance().send(request);
    ResponceParser.getroomip(responce);
    return room;
  }
}
