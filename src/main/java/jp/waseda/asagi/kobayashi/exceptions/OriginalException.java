package jp.waseda.asagi.kobayashi.exceptions;

public class OriginalException extends Exception {
  final public String message;

  OriginalException(String msg) {
    super(msg);
    message = msg;
  }
}