import java.io.IOException;

import javax.swing.*;

import client.ServerClient;
import router.Router;
import settings.Settings;

public class Main {

    public static void main(String[] args) {
        Router frame = Router.getInstance();
        frame.setTitle("音声通信アプリ");
        frame.setSize(Settings.PANEL_WIDTH, Settings.PANEL_HEIGHT);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        try {
            ServerClient.getInstance().initSetting();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}