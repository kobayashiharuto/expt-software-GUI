package router;

import java.awt.*;
import javax.swing.*;

import views.InitialView;
import views.LoginView;
import views.SignupView;

public class Router extends JFrame {
  private final JPanel cardPanel = new JPanel();
  private final CardLayout layout = new CardLayout();

  private static Router singleton = new Router();

  private Router() {
    cardPanel.setLayout(layout);

    cardPanel.add(new InitialView(), InitialView.path);
    cardPanel.add(new LoginView(), LoginView.path);
    cardPanel.add(new SignupView(), SignupView.path);

    Container contentPane = getContentPane();
    contentPane.add(cardPanel, BorderLayout.CENTER);
  }

  public static Router getInstance() {
    return singleton;
  }

  private void _push(String path) {
    layout.show(cardPanel, path);
  }

  static public void push(String path) {
    singleton._push(path);
  }
}