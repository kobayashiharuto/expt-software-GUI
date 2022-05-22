package jp.waseda.asagi.kobayashi.exceptions;

public class DuplicatedException extends OriginalException {
  public DuplicatedException() {
    super("すでにその名前は使われています");
  }
}
