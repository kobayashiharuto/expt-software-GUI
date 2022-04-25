package views;

import javax.swing.*;

import router.Router;
import services.LoginViewService;
import utils.ButtonActionAttacher;

import java.awt.*;

public class LoginView extends JPanel {
  static public final String path = "login";
  public final LoginViewService service = new LoginViewService();

  public final JTextField nameTextField;
  public final JTextField passwordTextField;
  public final JLabel errorLabel;

  public LoginView() {
    JPanel grid = new JPanel();

    GridLayout layout = new GridLayout();
    layout.setRows(5);
    layout.setColumns(3);
    grid.setLayout(layout);

    nameTextField = new JTextField(20);
    passwordTextField = new JTextField(20);
    JLabel nameLabel = new JLabel("名前");
    JLabel passLabel = new JLabel("パスワード");

    JButton returnButton = new JButton("戻る");
    ButtonActionAttacher.attach(returnButton, () -> {
      Router.push(InitialView.path);
    });

    JButton submitButton = new JButton("ログイン");
    ButtonActionAttacher.attach(submitButton, () -> {
      try {
        service.login(nameTextField.getText(), passwordTextField.getName());
      } catch (Exception e) {
        e.printStackTrace();
      }
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
