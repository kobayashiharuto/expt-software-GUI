package jp.waseda.asagi.kobayashi.entities;

final public class User {
  public String id;
  public String name;
  public String password;
  public int deposit;

  public User(String id, String name, String password, int deposit) {
    this.id = id;
    this.name = name;
    this.password = password;
    this.deposit = deposit;
  }

  static public User generateMockUser() {
    return new User("1", "hoge", "pass", 1000);
  }
}