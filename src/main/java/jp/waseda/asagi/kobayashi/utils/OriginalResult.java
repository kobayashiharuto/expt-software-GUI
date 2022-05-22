package jp.waseda.asagi.kobayashi.utils;

import jp.waseda.asagi.kobayashi.exceptions.OriginalException;

public class OriginalResult<T> {
  public final ResultType type;
  public final T value;
  public final OriginalException error;

  public OriginalResult(T value) {
    this.type = ResultType.success;
    this.value = value;
    this.error = null;
  }

  public OriginalResult(OriginalException error) {
    this.type = ResultType.failure;
    this.value = null;
    this.error = error;
  }
}
