package utils;

import javax.swing.*;

import exceptions.OriginalException;

public class CustomDialog extends JFrame {

  static public void showInfo(String title, String message) {
    JOptionPane.showMessageDialog(
        null,
        message,
        title,
        JOptionPane.INFORMATION_MESSAGE,
        null);
  }

  static public void showError(OriginalException error) {
    JOptionPane.showMessageDialog(
        null,
        error.message,
        "エラー",
        JOptionPane.ERROR_MESSAGE,
        null);
  }
}
