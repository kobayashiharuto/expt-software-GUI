package views;

import javax.swing.*;

import router.Router;

import java.awt.*;
import java.awt.event.*;

public class LoginView extends JPanel {
  static public final String path = "login";

  public LoginView() {
    JButton firstButton = new JButton("戻る");
    firstButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        Router.push(InitialView.path);
      }
    });

    add(firstButton, BorderLayout.CENTER);
  }
}
