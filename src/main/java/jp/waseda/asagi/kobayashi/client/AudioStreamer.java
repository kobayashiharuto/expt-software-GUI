package jp.waseda.asagi.kobayashi.client;

import javax.sound.sampled.*;

import jp.waseda.asagi.kobayashi.settings.AudioData;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

public class AudioStreamer {
​
  private List<Integer> ports = new ArrayList<>();// 送信先のポート番号のリスト
  private List<InetAddress> addresses = new ArrayList<>(); // 送信先のアドレスのリスト
  
  public void addListner(int port, String ip) {
    ports.add(port);//ポート番号リストへ要素の追加
    addresses.add(InetAddress.getByName(ip));//アドレスリストへ要素の追加
  }
  
  public void start() {
    DatagramSocket socket = new DatagramSocket(); // データグラムソケットを作成

    AudioFormat format = AudioData.format; // 音声フォーマット形式の作成
    TargetDataLine line = AudioSystem.getTargetDataLine(format); // マイクリソースから音声を取得するラインを作成

    int bufferSize = AudioData.bufferSize; // バッファサイズ
    byte[] buf = new byte[bufferSize]; // データを格納するバッファ

    try {
      line.open(format); // インプット用ラインを開く
      line.start(); // 音声の取得開始

      // バッファサイズまで音声データ格納 -> 送信を繰り返す
      while (true) {
        line.read(buf, 0, buf.length); // バッファに音声データを書き込む
        DatagramPacket sendPacket = new DatagramPacket(buf, buf.length, address, port); // パケットを作成
        socket.send(sendPacket); // パケットを送信
      }

    } finally {
      line.close(); // データラインを閉じる
      socket.close(); // データグラムソケットを閉じる
    }
  }
}