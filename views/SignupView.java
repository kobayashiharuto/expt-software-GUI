package views;

import javax.swing.*;

import controller.SignupViewController;
import utils.ButtonActionAttacher;
import utils.OriginalView;

import java.awt.*;
import java.util.Map;

public class SignupView extends OriginalView {
  static public final String path = "signup";
  private final SignupViewController controller = new SignupViewController(this);

  public final JTextField nameTextField;
  public final JTextField passwordTextField;
  public final JLabel errorLabel;

  public SignupView() {
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
      controller.signup();
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
