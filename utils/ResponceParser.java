package utils;

import java.util.List;

import entities.Room;

public class ResponceParser {

  public static List<Room> getRooms(String responce) {
    return Room.generateMockRooms();
  }
}
