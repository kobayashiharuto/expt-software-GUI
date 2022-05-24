package jp.waseda.asagi.kobayashi.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

import jp.waseda.asagi.kobayashi.entities.Comment;
import jp.waseda.asagi.kobayashi.entities.Listener;
import jp.waseda.asagi.kobayashi.entities.Room;
import jp.waseda.asagi.kobayashi.entities.User;
import jp.waseda.asagi.kobayashi.exceptions.DuplicatedException;
import jp.waseda.asagi.kobayashi.exceptions.ForbiddenException;
import jp.waseda.asagi.kobayashi.exceptions.NotExistRoomException;
import jp.waseda.asagi.kobayashi.exceptions.UnknownException;

public class ResponceParser {

  public static Listener startListen(String responce) throws NumberFormatException, UnknownHostException {
    final String ujson = responce.replace("#startListen#", "");
    final Map<String, String> map = new Gson().fromJson(ujson, Map.class);
    final String id = map.get("id");
    final String ip = map.get("ip");
    final String port = map.get("port");
    return new Listener(id, Integer.parseInt(port), InetAddress.getByName(ip));
  }

  public static String stopListen(String responce) {
    final String ujson = responce.replace("#stopListen#", "");
    final Map<String, String> map = new Gson().fromJson(ujson, Map.class);
    final String id = map.get("id");
    return id;
  }

  public static Comment listenComment(String responce) {
    final String ujson = responce.replace("#comment#", "");
    System.out.println(ujson);
    final Map<String, String> map = new Gson().fromJson(ujson, Map.class);
    final String username = map.get("username");
    final String message = map.get("message");
    return new Comment(username, message);
  }

  public static boolean stop(String responce) throws UnknownException {
    System.out.println("stop: " + responce);
    return true;
  }

  public static boolean quitroom(String responce) throws UnknownException {
    System.out.println("quitroom: " + responce);
    return true;
  }

  public static boolean postComment(String responce) throws UnknownException {
    System.out.println("postComment: " + responce);
    return true;
  }

  public static boolean listenroom(String responce) throws NotExistRoomException {
    System.out.println("listenroom: " + responce);
    if (responce.equals("#nothisroom#")) {
      throw new NotExistRoomException();
    }
    return true;
  }

  public static Room startStreamming(String responce, String roomname) {
    final Map<String, String> map = new Gson().fromJson(responce, Map.class);
    final String roomID = map.get("roomID");
    return new Room(roomID, roomname);
  }

  public static List<Room> getRooms(String responce) {
    final Map<String, String> map = new Gson().fromJson(responce, Map.class);
    final List<Room> rooms = new ArrayList<Room>();
    map.forEach((id, name) -> {
      rooms.add(new Room(id, name));
    });
    return rooms;
  }

  public static User login(String responce, String name, String password) throws ForbiddenException {
    if (responce.equals("#notexist#") || responce.equals("#wrongpasswd#")) {
      throw new ForbiddenException();
    }
    final Map<String, String> map = new Gson().fromJson(responce, Map.class);
    final String uid = map.get("uid");
    final int deposit = Integer.parseInt(map.get("deposit"));
    return new User(uid, name, password, deposit);
  }

  public static User signup(String responce, String name, String password) throws DuplicatedException {
    if (responce.equals("#failed#")) {
      throw new DuplicatedException();
    }
    final Map<String, String> map = new Gson().fromJson(responce, Map.class);
    final String uid = map.get("uid");
    final int deposit = Integer.parseInt(map.get("deposit"));
    return new User(uid, name, password, deposit);
  }
}
