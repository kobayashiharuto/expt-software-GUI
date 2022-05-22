package jp.waseda.asagi.kobayashi.repositories;

import java.io.IOException;
import java.util.function.Consumer;

import jp.waseda.asagi.kobayashi.client.ServerClient;
import jp.waseda.asagi.kobayashi.entities.Room;
import jp.waseda.asagi.kobayashi.exceptions.NetworkException;
import jp.waseda.asagi.kobayashi.utils.OriginalResult;
import jp.waseda.asagi.kobayashi.utils.RequestParser;
import jp.waseda.asagi.kobayashi.utils.ResponceParser;

public class RoomCreateRepository extends Thread {
  private final int myListenPort;
  private final String roomName;
  private final Consumer<OriginalResult<Room>> callback;

  public RoomCreateRepository(int myListenPort, String name, Consumer<OriginalResult<Room>> callback) {
    this.myListenPort = myListenPort;
    this.roomName = name;
    this.callback = callback;
  }

  @Override
  public void run() {
    try {
      System.out.println("room create start: " + roomName);
      final Room room = createRoom(roomName, myListenPort);
      final OriginalResult<Room> result = new OriginalResult<Room>(room);
      callback.accept(result);
    } catch (IOException e) {
      System.out.println("create room faild");
      final OriginalResult<Room> result = new OriginalResult<Room>(new NetworkException());
      callback.accept(result);
    }
  }

  // static private Room createRoom(String name) throws InterruptedException {
  // Thread.sleep(1000); // 部屋作成処理を記述
  // final Room room = new Room(name, "1231", Settings.CLIENT_IP,
  // Settings.CLIENT_SEND_PORT);
  // return room;
  // }

  static private Room createRoom(String roomname, int port) throws IOException {
    final String request = RequestParser.startstreamming(roomname, port);
    final String responce = ServerClient.getInstance().send(request);
    final Room room = ResponceParser.startStreamming(responce, roomname);
    return room;
  }
}
