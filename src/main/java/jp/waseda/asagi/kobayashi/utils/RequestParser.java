package jp.waseda.asagi.kobayashi.utils;

public class RequestParser {

  public static String registration(String username, String password) {
    final String request = "#registration#" + "{\"" + username + "\":\"" + password + "\"}";
    return request;
  }

  public static String login(String username, String password) {
    final String request = "#login#" + "{\"" + username + "\":\"" + password + "\"}";
    return request;
  }

  public static String comment(String uid, String username, String roomid, String comment) {
    final String request = "#comment#" + "{\"" + uid + "\",\"" + username + "\",\"" + roomid + "\",\"" + comment
        + "\"}";
    return request;
  }

  public static String tip(String uid, String name, String roomid, Integer amount) {
    final String request = "#tip#" + "{\"" + uid + "\",\"" + name + "\",\"" + roomid + "\",\"" + amount + "\"}";
    return request;
  }

  public static String getroooms() {
    final String request = "#getrooms#";
    return request;
  }

  public static String listenroom(String roomid, String uid, String ip, int port) {
    final String request = "#listenroom#" + "{\"" + roomid + "\",\"" + uid + "\",\"" + ip + "\",\"" + port + "\"}";
    return request;
  }

  public static String quitroom(String roomid, String uid) {
    final String request = "#quitroom#" + "{\"" + roomid + "\",\"" + uid + "\"}";
    return request;
  }

  public static String startstreamming(String uid, String roomname, Integer port) {
    final String portStr = port.toString();
    final String request = "#startstreamming#" + "{\"" + uid + "\":\"" + roomname + "\":\"" + portStr + "\"}";
    return request;
  }

  public static String stop(String roomid) {
    final String request = "#stop#" + "{\"" + roomid + "\"}";
    return request;
  }
}
