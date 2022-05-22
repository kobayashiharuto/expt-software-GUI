package jp.waseda.asagi.kobayashi.utils;

public class Validation {
  static public boolean check(String target, int minLength, int maxLength) {
    return target.length() >= minLength && target.length() <= maxLength;
  }
}
