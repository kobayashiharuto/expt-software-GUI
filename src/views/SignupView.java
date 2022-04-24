package views;

import javax.swing.*;

import router.Router;

import java.awt.*;
import java.awt.event.*;

public class SignupView extends JPanel {
  static public final String path = "signup";

  public SignupView() {
    JButton firstButton = new JButton("aaawaaaaaaaaaaa");
    firstButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        Router.push(InitialView.path);
      }
    });

    add(firstButton, BorderLayout.CENTER);
  }
}
