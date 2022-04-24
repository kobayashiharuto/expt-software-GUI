package entities;

final public class User {
  public final String name;
  public final String password;
  public final int point;

  public User(String name, String password, int point) {
    this.name = name;
    this.password = password;
    this.point = point;
  }
}