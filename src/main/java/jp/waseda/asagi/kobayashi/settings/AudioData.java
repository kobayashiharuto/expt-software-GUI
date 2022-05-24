package jp.waseda.asagi.kobayashi.settings;

import javax.sound.sampled.AudioFormat;

public class AudioData {
  private static final AudioFormat.Encoding encoding = AudioFormat.Encoding.PCM_SIGNED; // エンコード形式
  private static final float rate = 44100.0f; // サンプリングレート (音楽業界の標準レートが44100Hz)
  private static final int sampleSizeInBits = 16; // サンプルサイズ(8 or 16)
  private static final int channels = 2; // 音声のチャンネル数
  private static final boolean bigEndian = true; // ビッグエンディアンかどうか (データの送信方向。送受信間で統一されていれば問題ない)
  private static final int frameSize = channels * (sampleSizeInBits / 8); // 1フレームあたりのバイト数

  // 音声フォーマット形式
  public static final AudioFormat format = new AudioFormat(
      encoding,
      rate,
      sampleSizeInBits,
      channels,
      frameSize,
      rate,
      bigEndian);

  public static final int bufferSize = frameSize * 1024 * 2; // 音声送受信用バッファサイズ（どれくらいが適当か不明）
}