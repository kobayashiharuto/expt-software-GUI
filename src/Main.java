import javax.swing.*;

import router.Router;
import setting.Setting;

public class Main {

    public static void main(String[] args) {
        Router frame = Router.getInstance();
        frame.setTitle("音声通信アプリ");
        frame.setSize(Setting.PANEL_WIDTH, Setting.PANEL_HEIGHT);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}