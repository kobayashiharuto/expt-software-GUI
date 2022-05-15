package entities;

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
    final Room room1 = new Room("1", "room1", "pass", 8080);
    final Room room2 = new Room("2", "room2", "pass", 8080);
    return new java.util.ArrayList<Room>() {
      {
        add(room1);
        add(room2);
      }
    };
  }
}