package entities;

final public class User {
  public final String name;

  User(String name) {
    this.name = name;
    final User user = new User(null);
  }
}