package views;

import javax.swing.*;

import router.Router;
import utils.ButtonActionAttacher;
import utils.OriginalView;

import java.awt.*;
import java.util.Map;

public class InitialView extends OriginalView {
  static public final String path = "initial";

  public InitialView() {
    super(path, false);

    // ログインボタンの作成
    JButton loginButton = new JButton("ログイン");
    ButtonActionAttacher.attach(loginButton, () -> {
      Router.push(LoginView.path, null);
    });

    // サインアップボタンの生成
    JButton signupButton = new JButton("サインアップ");
    ButtonActionAttacher.attach(signupButton, () -> {
      Router.push(SignupView.path, null);
    });

    add(loginButton, BorderLayout.CENTER);
    add(signupButton, BorderLayout.CENTER);
  }

  @Override
  public void onAppear(Map<String, String> param) {
  }

  @Override
  public void onDisapper() {
  }
}
