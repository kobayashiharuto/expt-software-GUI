package exceptions;

public class NetworkException extends OriginalException {
  public NetworkException() {
    super("ネットワークエラーが発生しました");
  }
}