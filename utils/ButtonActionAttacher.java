package utils;

import javax.swing.*;

import java.awt.event.*;

public class ButtonActionAttacher {
  private ButtonActionAttacher() {
  }

  public static void attach(JButton button, Runnable action) {
    button.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        action.run();
      }
    });
  }
}
