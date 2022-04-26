package entities;

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
}