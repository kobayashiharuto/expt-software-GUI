package jp.waseda.asagi.kobayashi.entities;

import java.util.ArrayList;
import java.util.List;

final public class Room {
  public String id;
  public String name;

  public Room(String id, String name) {
    this.id = id;
    this.name = name;
  }

  static public Room generateMockRoom() {
    return new Room("1", "hoge");
  }

  static public List<Room> generateMockRooms() {
    final List<Room> rooms = new ArrayList<Room>();

    for (int i = 0; i < 10; i++) {
      final String count = Integer.valueOf(i).toString();
      final Room room = new Room(count, "room" + count);
      rooms.add(room);
    }

    return rooms;
  }
}