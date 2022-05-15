package settings;

public class Settings {
  private Settings() {
  }

  // レイアウト設定
  public static final int PANEL_WIDTH = 900;
  public static final int PANEL_HEIGHT = 600;

  // ユーザー設定
  public static final int USER_INIT_POINT = 300;

  // バリデーション用
  public static final int USER_NAME_LIMIT_MIN = 3;
  public static final int USER_NAME_LIMIT_MAX = 10;
  public static final int PASSWORD_LIMIT_MIN = 4;
  public static final int PASSWORD_LIMIT_MAX = 12;
  public static final int ROOM_NUM_LIMIT_MIN = 3;
  public static final int ROOM_NUM_LIMIT_MAX = 10;

  // サーバーのエンドポイント
  public static final String SERVER_IP = "localhost";
  public static final int SERVER_PORT = 8080;
}
