package jp.waseda.asagi.kobayashi.exceptions;

public class NetworkException extends OriginalException {
  public NetworkException() {
    super("ネットワークエラーが発生しました");
  }
}
