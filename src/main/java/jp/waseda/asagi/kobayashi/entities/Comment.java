package jp.waseda.asagi.kobayashi.entities;

public class Comment {
  public String name;
  public String comment;

  public Comment(String name, String comment) {
    this.name = name;
    this.comment = comment;
  }

  static public Comment generateMockComment() {
    return new Comment("koba", "hello");
  }

}
