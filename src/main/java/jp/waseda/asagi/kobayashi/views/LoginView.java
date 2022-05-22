package jp.waseda.asagi.kobayashi.views;

import javax.swing.*;

import jp.waseda.asagi.kobayashi.controller.LoginViewController;
import jp.waseda.asagi.kobayashi.utils.ButtonActionAttacher;
import jp.waseda.asagi.kobayashi.utils.OriginalView;

import java.awt.*;
import java.util.Map;

public class LoginView extends OriginalView {
  static public final String path = "login";
  private final LoginViewController controller = new LoginViewController(this);

  public final JTextField nameTextField;
  public final JTextField passwordTextField;
  public final JLabel errorLabel;

  public LoginView() {
    super(path, "ログイン", true);

    JPanel gridPanel = new JPanel();

    GridBagLayout gridLayout = new GridBagLayout();
    gridPanel.setLayout(gridLayout);
    GridBagConstraints gbc = new GridBagConstraints();

    nameTextField = new JTextField(20);
    passwordTextField = new JTextField(20);
    errorLabel = new JLabel();

    JLabel nameLabel = new JLabel("名前");
    JLabel passLabel = new JLabel("パスワード");

    JButton submitButton = new JButton("ログイン");
    ButtonActionAttacher.attach(submitButton, () -> {
      controller.login();
    });

    gbc.insets = new Insets(7, 3, 0, 3);

    gbc.gridx = 0;
    gbc.gridy = 0;
    gridLayout.setConstraints(nameLabel, gbc);
    gridPanel.add(nameLabel);

    nameTextField.setSize(200, 20);
    gbc.gridx = 1;
    gbc.gridy = 0;
    gridLayout.setConstraints(nameTextField, gbc);
    gridPanel.add(nameTextField);

    gbc.gridx = 0;
    gbc.gridy = 1;
    gridLayout.setConstraints(passLabel, gbc);
    gridPanel.add(passLabel);

    passwordTextField.setSize(200, 20);
    gbc.gridx = 1;
    gbc.gridy = 1;
    gridLayout.setConstraints(passwordTextField, gbc);
    gridPanel.add(passwordTextField);

    gbc.insets = new Insets(10, 0, 0, 0);
    gbc.gridx = 0;
    gbc.gridy = 2;
    gbc.gridwidth = 2;
    gridLayout.setConstraints(submitButton, gbc);

    gridPanel.add(submitButton);
    mainPanel.add(gridPanel);
  }

  @Override
  public void onAppear(Map<String, String> param) {
  }

  @Override
  public void onDisapper() {
  }
}
