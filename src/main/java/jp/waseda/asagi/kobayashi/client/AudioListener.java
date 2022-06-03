package jp.waseda.asagi.kobayashi.client;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.SourceDataLine;

import jp.waseda.asagi.kobayashi.settings.AudioData;
import jp.waseda.asagi.kobayashi.settings.Settings;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class AudioListener extends Thread {
  private DatagramSocket socket;
  private SourceDataLine line;
  private boolean onLisntening = false;

  @Override
  public void run() {
    onLisntening = true;
    listenStart();
  }

  public void listenStart() {
    AudioFormat format = AudioData.format; // 音声フォーマットを指定

    try {
      line = AudioSystem.getSourceDataLine(format); // スピーカーから音声データを出力するラインを取得

      socket = new DatagramSocket(Settings.CLIENT_LISTEN_PORT); // ポート番号を指定してデータグラムソケットを作成

      int bufferSize = AudioData.bufferSize; // バッファサイズ
      byte[] buf = new byte[bufferSize]; // データを格納するバッファ

      line.open(format); // 音声データを出力するラインを開く
      line.start(); // 流れてきたデータをスピーカーに出力する状態に

      // データが流れてくるまで待機 -> データが送られてきたらアウトプットデータラインに流す を繰り返す
      while (true) {
        if (!onLisntening) {
          break;
        }
        DatagramPacket receivePacket = new DatagramPacket(buf, buf.length); // 受け取り用バッファを格納したパケットクラスを作成
        socket.receive(receivePacket); // パケットを受信
        line.write(buf, 0, buf.length); // バッファをアウトプットデータラインに流す（再生）
      }
    } catch (Exception e) {
      System.out.println(e);
    } finally {
      System.out.println("音声受信終了");
    }
  }

  public void listenStop() {
    onLisntening = false;
    line.stop();
    line.close();
    socket.close();
  }
}