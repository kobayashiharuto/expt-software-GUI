package views;

import javax.swing.*;

import router.Router;
import utils.ButtonActionAttacher;

import java.awt.*;
import java.awt.event.*;

public class LoginView extends JPanel {
  static public final String path = "login";

  public LoginView() {
    JPanel panel = new JPanel();

    JButton returnButton = new JButton("戻る");
    ButtonActionAttacher.attach(returnButton, () -> {
      Router.push(InitialView.path);
    });

    JButton submitButton = new JButton("ログイン");
    ButtonActionAttacher.attach(submitButton, () -> {
      System.out.println("submit");
    });

    JTextField nameTextField = new JTextField(20);
    JTextField passTextField = new JTextField(20);

    panel.add(nameTextField);
    panel.add(passTextField);
    panel.add(returnButton);
    panel.add(submitButton);

    add(panel, BorderLayout.CENTER);
  }
}
