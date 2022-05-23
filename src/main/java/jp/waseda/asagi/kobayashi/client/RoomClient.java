package jp.waseda.asagi.kobayashi.client;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

import jp.waseda.asagi.kobayashi.settings.Settings;

public class RoomClient {
  private BufferedReader receiveLine;
  private PrintWriter sendLine;
  private Socket socket;

  private static RoomClient singleton = new RoomClient();

  private RoomClient() {
  }

  public void connectRoomSocket() throws IOException {
    final InetAddress addr = InetAddress.getByName(Settings.SERVER_IP);
    socket = new Socket(addr, Settings.SERVER_PORT);
    receiveLine = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    sendLine = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
    System.out.println("ルームとの接続完了");

    // 終了時にクライアントを閉じる
    final Thread hook = new Thread() {
      public void run() {
        closeRoomSocket();
      }
    };
    Runtime.getRuntime().addShutdownHook(hook);
  }

  public static RoomClient getInstance() {
    return singleton;
  }

  public String send(String request) throws IOException {
    sendLine.println(request);
    System.out.println(request);
    final String response = receiveLine.readLine();
    System.out.println(response);
    return response;
  }

  public String read() throws IOException {
    final String response = receiveLine.readLine();
    System.out.println(response);
    return response;
  }

  public void closeRoomSocket() {
    try {
      System.out.println("ルームとの接続終了");
      socket.close();
      sendLine.close();
      receiveLine.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
