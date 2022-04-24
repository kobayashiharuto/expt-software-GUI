package views;

import javax.swing.*;

import router.Router;
import services.LoginViewService;
import utils.ButtonActionAttacher;

import java.awt.*;

public class LoginView extends JPanel {
  static public final String path = "login";
  public final LoginViewService service = new LoginViewService();

  public LoginView() {
    JPanel panel = new JPanel();
    GridLayout layout = new GridLayout();
    layout.setRows(5);
    layout.setColumns(3);
    panel.setLayout(layout);

    JTextField nameTextField = new JTextField(20);
    JTextField passTextField = new JTextField(20);
    JLabel nameLabel = new JLabel("名前");
    JLabel passLabel = new JLabel("パスワード");

    JButton returnButton = new JButton("戻る");
    ButtonActionAttacher.attach(returnButton, () -> {
      Router.push(InitialView.path);
    });

    JButton submitButton = new JButton("ログイン");
    ButtonActionAttacher.attach(submitButton, () -> {
      try {
        service.login(nameTextField.getText(), passTextField.getName());
      } catch (Exception e) {
        e.printStackTrace();
      }
    });

    panel.add(nameLabel);
    panel.add(nameTextField);
    panel.add(passLabel);
    panel.add(passTextField);
    panel.add(returnButton);
    panel.add(submitButton);

    add(panel, BorderLayout.PAGE_END);
  }
}
