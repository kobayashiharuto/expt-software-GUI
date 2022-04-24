import javax.swing.*;

import router.Router;

public class Main {

    public static void main(String[] args) {
        Router frame = Router.getInstance();
        frame.setTitle("音声通信アプリ");
        frame.setSize(900, 600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}