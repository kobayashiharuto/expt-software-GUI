package jp.waseda.asagi.kobayashi.exceptions;

public class RoomCloseException extends OriginalException {
  public RoomCloseException() {
    super("部屋が閉じられました");
  }
}
