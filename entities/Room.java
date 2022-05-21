package entities;

import java.util.ArrayList;
import java.util.List;

final public class Room {
  public String id;
  public String name;
  public String ip;
  public int port;

  public Room(String id, String name, String ip, int port) {
    this.id = id;
    this.name = name;
    this.ip = ip;
    this.port = port;
  }

  static public Room generateMockRoom() {
    return new Room("1", "hoge", "pass", 8080);
  }

  static public List<Room> generateMockRooms() {
    final List<Room> rooms = new ArrayList<Room>();

    for (int i = 0; i < 10; i++) {
      final String count = Integer.valueOf(i).toString();
      final Room room = new Room(count, "room" + count, "pass", 8080);
      rooms.add(room);
    }

    return rooms;
  }
}