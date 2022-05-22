package jp.waseda.asagi.kobayashi.exceptions;

public class NotExistRoomException extends OriginalException {
  public NotExistRoomException() {
    super("部屋が存在しません");
  }
}
