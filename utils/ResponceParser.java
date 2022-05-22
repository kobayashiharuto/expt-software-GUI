package utils;

import java.util.List;

import entities.Room;
import entities.User;
import exceptions.ForbiddenException;

public class ResponceParser {

  public static List<Room> getRooms(String responce) {
    return Room.generateMockRooms();
  }

  public static User login(String responce, String name, String password) throws ForbiddenException {
    System.out.println("responce: " + responce == "#notexist#");
    if (responce.equals("#notexist#") || responce.equals("#wrongpasswd#")) {
      throw new ForbiddenException();
    }
    return new User("1231", name, password, 100);
  }
}
