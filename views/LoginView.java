package views;

import javax.swing.*;

import controller.LoginViewController;
import utils.ButtonActionAttacher;
import utils.OriginalView;

import java.awt.*;
import java.util.Map;

public class LoginView extends OriginalView {
  static public final String path = "login";
  private final LoginViewController controller = new LoginViewController(this);

  public final JTextField nameTextField;
  public final JTextField passwordTextField;
  public final JLabel errorLabel;

  public LoginView() {
    super(path, true);

    JPanel grid = new JPanel();

    GridLayout layout = new GridLayout();
    layout.setRows(3);
    layout.setColumns(2);
    grid.setLayout(layout);

    nameTextField = new JTextField(20);
    passwordTextField = new JTextField(20);
    errorLabel = new JLabel();

    JLabel nameLabel = new JLabel("名前");
    JLabel passLabel = new JLabel("パスワード");

    JButton submitButton = new JButton("ログイン");
    ButtonActionAttacher.attach(submitButton, () -> {
      controller.login();
    });

    grid.add(nameLabel);
    grid.add(nameTextField);
    grid.add(passLabel);
    grid.add(passwordTextField);
    grid.add(submitButton);

    JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

    add(grid, BorderLayout.PAGE_END);
  }

  @Override
  public void onAppear(Map<String, String> param) {
  }

  @Override
  public void onDisapper() {
  }
}
