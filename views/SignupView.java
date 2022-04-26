package views;

import javax.swing.*;

import controller.SignupViewController;
import router.Router;
import utils.ButtonActionAttacher;
import utils.OriginalView;

import java.awt.*;

public class SignupView extends OriginalView {
  static public final String path = "signup";
  private final SignupViewController controller = new SignupViewController(this);

  public final JTextField nameTextField;
  public final JTextField passwordTextField;
  public final JLabel errorLabel;

  public SignupView() {
    super(path);
    
    JPanel grid = new JPanel();

    GridLayout layout = new GridLayout();
    layout.setRows(5);
    layout.setColumns(3);
    grid.setLayout(layout);

    nameTextField = new JTextField(20);
    passwordTextField = new JTextField(20);
    errorLabel = new JLabel();

    JLabel nameLabel = new JLabel("名前");
    JLabel passLabel = new JLabel("パスワード");

    JButton returnButton = new JButton("戻る");
    ButtonActionAttacher.attach(returnButton, () -> {
      Router.pop();
    });

    JButton submitButton = new JButton("ログイン");
    ButtonActionAttacher.attach(submitButton, () -> {
        controller.signup();
    });

    grid.add(nameLabel);
    grid.add(nameTextField);
    grid.add(passLabel);
    grid.add(passwordTextField);
    grid.add(returnButton);
    grid.add(submitButton);

    JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

    add(grid, BorderLayout.PAGE_END);
  }
}
