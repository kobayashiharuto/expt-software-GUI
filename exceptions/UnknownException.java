package exceptions;

public class UnknownException extends OriginalException {
  public UnknownException() {
    super("不明なエラーが発生しました");
  }
}
