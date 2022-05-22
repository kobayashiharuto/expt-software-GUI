package client;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import settings.Settings;

public class ServerClient {
  private BufferedReader receiveLine;
  private PrintWriter sendLine;
  private Socket socket;

  private static ServerClient singleton = new ServerClient();

  private ServerClient() {
  }

  public void initSetting() throws IOException {
    final InetAddress addr = InetAddress.getByName(Settings.SERVER_IP);
    socket = new Socket(addr, Settings.SERVER_PORT);
    receiveLine = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    sendLine = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
    System.out.println("サーバーとの接続完了");

    // 終了時にクライアントを閉じる
    final Thread hook = new Thread() {
      public void run() {
        dispose();
      }
    };
    Runtime.getRuntime().addShutdownHook(hook);
  }

  public static ServerClient getInstance() {
    return singleton;
  }

  public String send(String request) throws IOException {
    sendLine.println(request);
    final String response = receiveLine.readLine();
    System.out.println(response);
    return response;
  }

  public void dispose() {
    try {
      System.out.println("サーバーとの接続終了");
      socket.close();
      sendLine.close();
      receiveLine.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
