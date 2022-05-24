package jp.waseda.asagi.kobayashi.exceptions;

public class NotEnougnPointException extends OriginalException {
  public NotEnougnPointException() {
    super("ポイントが足りません");
  }
}
