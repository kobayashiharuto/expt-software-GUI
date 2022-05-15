package utils;

import javax.swing.*;

public class CustomDialog extends JFrame {

  static public void showInfo(String title, String message) {
    JOptionPane.showMessageDialog(
        null,
        message,
        title,
        JOptionPane.INFORMATION_MESSAGE,
        null);
  }

  static public void showError(String title, String message) {
    JOptionPane.showMessageDialog(
        null,
        message,
        title,
        JOptionPane.ERROR_MESSAGE,
        null);
  }
}
