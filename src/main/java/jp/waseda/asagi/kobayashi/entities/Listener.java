package jp.waseda.asagi.kobayashi.entities;

import java.net.InetAddress;

public class Listener {
  final public String id;
  final public Integer port;
  final public InetAddress address;

  public Listener(String id, Integer port, InetAddress address) {
    this.id = id;
    this.port = port;
    this.address = address;
  }

}
