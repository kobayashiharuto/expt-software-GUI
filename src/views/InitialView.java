package views;

import javax.swing.*;

import router.Router;

import java.awt.*;
import java.awt.event.*;

public class InitialView extends JPanel {
  static public final String path = "initial";

  public InitialView() {
    JButton firstButton = new JButton("ログイン");
    firstButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        Router.push(LoginView.path);
      }
    });

    JButton secondButton = new JButton("サインアップ");
    secondButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        Router.push(SignupView.path);
      }
    });

    // cardPanelとカード移動用ボタンをJFrameに配置
    add(firstButton, BorderLayout.CENTER);
    add(secondButton, BorderLayout.CENTER);
  }
}
