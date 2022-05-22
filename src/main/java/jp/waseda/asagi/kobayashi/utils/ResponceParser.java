package jp.waseda.asagi.kobayashi.utils;

import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

import jp.waseda.asagi.kobayashi.entities.Room;
import jp.waseda.asagi.kobayashi.entities.User;
import jp.waseda.asagi.kobayashi.exceptions.DuplicatedException;
import jp.waseda.asagi.kobayashi.exceptions.ForbiddenException;

public class ResponceParser {

  public static List<Room> getRooms(String responce) {
    return Room.generateMockRooms();
  }

  public static User login(String responce, String name, String password) throws ForbiddenException {
    if (responce.equals("#notexist#") || responce.equals("#wrongpasswd#")) {
      throw new ForbiddenException();
    }
    final Map<String, String> map = new Gson().fromJson(responce, Map.class);
    final String uid = map.get("uid");
    return new User(uid, name, password, 100);
  }

  public static User signup(String responce, String name, String password) throws DuplicatedException {
    if (responce.equals("#failed#")) {
      throw new DuplicatedException();
    }
    final Map<String, String> map = new Gson().fromJson(responce, Map.class);
    final String uid = map.get("uid");
    return new User(uid, name, password, 100);
  }
}
