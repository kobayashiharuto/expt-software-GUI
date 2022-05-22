package jp.waseda.asagi.kobayashi.views;

import javax.swing.*;

import jp.waseda.asagi.kobayashi.router.Router;
import jp.waseda.asagi.kobayashi.utils.ButtonActionAttacher;
import jp.waseda.asagi.kobayashi.utils.OriginalView;

import java.awt.*;
import java.util.Map;

public class InitialView extends OriginalView {
  static public final String path = "initial";

  public InitialView() {
    super(path, "ようこそ", false);

    final JPanel panel = new JPanel();

    final GridBagLayout layout = new GridBagLayout();
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(10, 10, 10, 10);

    panel.setLayout(layout);

    // ログインボタンの作成
    JButton loginButton = new JButton("ログイン");
    ButtonActionAttacher.attach(loginButton, () -> {
      Router.push(LoginView.path, null);
    });
    layout.setConstraints(loginButton, gbc);

    // 新規登録ボタンの生成
    JButton signupButton = new JButton("新規登録");
    ButtonActionAttacher.attach(signupButton, () -> {
      Router.push(SignupView.path, null);
    });
    layout.setConstraints(loginButton, gbc);

    panel.add(loginButton);
    panel.add(signupButton);
    mainPanel.add(panel);
  }

  @Override
  public void onAppear(Map<String, String> param) {
  }

  @Override
  public void onDisapper() {
  }
}
