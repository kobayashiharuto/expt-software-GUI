import javax.swing.*;

import router.Router;
import settings.Settings;

public class Main {

    public static void main(String[] args) {
        Router frame = Router.getInstance();
        frame.setTitle("音声通信アプリ");
        frame.setSize(Settings.PANEL_WIDTH, Settings.PANEL_HEIGHT);
        frame.setLocationRelativeTo(null);
        frame.setResizable(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}