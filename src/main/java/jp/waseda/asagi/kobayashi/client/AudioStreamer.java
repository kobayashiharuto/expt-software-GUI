package jp.waseda.asagi.kobayashi.client;

import javax.sound.sampled.*;

import jp.waseda.asagi.kobayashi.entities.Listener;
import jp.waseda.asagi.kobayashi.settings.AudioData;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.ArrayList;
import java.util.List;

public class AudioStreamer {
  private final List<Listener> listeners = new ArrayList<Listener>();
  private DatagramSocket socket;
  private TargetDataLine line;

  public void addListner(Listener listener) {
    listeners.add(listener);
  }

  public void removeListner(String id) {
    listeners.removeIf((lisnter) -> lisnter.id.equals(id));
  }

  public void start() throws LineUnavailableException, IOException {
    AudioFormat format = AudioData.format; // 音声フォーマット形式の作成
    socket = new DatagramSocket(); // データグラムソケットを作成
    line = AudioSystem.getTargetDataLine(format); // マイクリソースから音声を取得するラインを作成

    int bufferSize = AudioData.bufferSize; // バッファサイズ
    byte[] buf = new byte[bufferSize]; // データを格納するバッファ

    line.open(format); // インプット用ラインを開く
    line.start(); // 音声の取得開始

    // バッファサイズまで音声データ格納 -> 送信を繰り返す
    while (true) {
      line.read(buf, 0, buf.length); // バッファに音声データを書き込む
      System.out.println("read");
      for (Listener listener : listeners) {
        DatagramPacket packet = new DatagramPacket(buf, buf.length, listener.address, listener.port);
        socket.send(packet);
      }
    }
  }

  public void stop() {
    line.stop();
    line.close(); // データラインを閉じる
    socket.close(); // データグラムソケットを閉じる
  }
}