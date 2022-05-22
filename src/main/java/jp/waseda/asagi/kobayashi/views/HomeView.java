package jp.waseda.asagi.kobayashi.views;

import javax.swing.*;

import jp.waseda.asagi.kobayashi.router.Router;
import jp.waseda.asagi.kobayashi.states.UserState;
import jp.waseda.asagi.kobayashi.utils.ButtonActionAttacher;
import jp.waseda.asagi.kobayashi.utils.OriginalView;

import java.awt.*;
import java.util.Map;

public class HomeView extends OriginalView {
  static public final String path = "home";

  public HomeView() {
    super(path, "ホーム", false);

    final JPanel panel = new JPanel();

    final GridBagLayout layout = new GridBagLayout();
    GridBagConstraints gbc = new GridBagConstraints();

    panel.setLayout(layout);

    JLabel label = new JLabel("ようこそ");

    UserState.getInstance().listen((user) -> {
      if (user == null)
        return;
      label.setText("ようこそ " + user.name + "さん");
    });

    JButton enterButton = new JButton("部屋に入る");
    ButtonActionAttacher.attach(enterButton, () -> {
      Router.push(EnterRoomView.path, null);
    });

    JButton createButton = new JButton("部屋を作る");
    ButtonActionAttacher.attach(createButton, () -> {
      Router.push(CreateRoomView.path, null);
    });

    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.gridwidth = 2;
    gbc.insets = new Insets(10, 10, 30, 10);
    layout.setConstraints(label, gbc);
    panel.add(label);

    gbc.gridx = 0;
    gbc.gridy = 1;
    gbc.gridwidth = 1;
    gbc.insets = new Insets(10, 10, 10, 10);
    layout.setConstraints(enterButton, gbc);
    panel.add(enterButton);

    gbc.gridx = 1;
    gbc.gridy = 1;
    gbc.gridwidth = 1;
    gbc.insets = new Insets(10, 10, 10, 10);
    layout.setConstraints(createButton, gbc);
    panel.add(createButton);

    mainPanel.add(panel);
  }

  @Override
  public void onAppear(Map<String, String> param) {
  }

  @Override
  public void onDisapper() {
  }
}
