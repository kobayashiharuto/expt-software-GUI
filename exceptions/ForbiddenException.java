package exceptions;

public class ForbiddenException extends OriginalException {
  public ForbiddenException() {
    super("パスワードかIDが間違っています");
  }
}
