package entities;

public class Comment {
  public String id;
  public String name;
  public String comment;

  public Comment(String id, String name, String comment) {
    this.id = id;
    this.name = name;
    this.comment = comment;
  }

  static public Comment generateMockComment() {
    return new Comment("1", "koba", "hello");
  }

}
