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

  public static String comment(String uid, String roomid, String comment) {
    final String request = "#comment#" + "{\"" + uid + "\",\"" + roomid + "\",\"" + comment + "\"}";
    return request;
  }

  public static String tip(String uid, Integer roomid, Integer amount) {
    final String request = "#tip#" + "{\"" + uid + "\",\"" + roomid + "\",\"" + amount + "\"}";
    return request;
  }

  public static String getroooms() {
    final String request = "#getrooms#";
    return request;
  }

  public static String getroomip(String roomid) {
    final String request = "#getroomip#" + "{\"" + roomid + "\"}";
    return request;
  }

  public static String quitroom(String roomid) {
    final String request = "#quitroom#" + "{\"" + roomid + "\"}";
    return request;
  }

  public static String startstreamming(String roomname, Integer port) {
    final String portStr = port.toString();
    final String request = "#startstreamming#" + "{\"" + roomname + "\":\"" + portStr + "\"}";
    return request;
  }

  public static String stop(String roomid) {
    final String request = "#stop#" + "{\"" + roomid + "\"}";
    return request;
  }
}
