package views;

import javax.swing.*;

import router.Router;
import states.UserState;
import utils.ButtonActionAttacher;
import utils.OriginalView;

import java.awt.*;
// import java.awt.event.*;

public class HomeView extends OriginalView {
  static public final String path = "home";

  public HomeView() {
    super(path, false);

    JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

    JLabel label = new JLabel("ようこそ");

    UserState.getInstance().listen((user) -> {
      if (user == null)
        return;
      label.setText("ようこそ" + user.name);
    });

    JButton enterButton = new JButton("部屋に入る");
    ButtonActionAttacher.attach(enterButton, () -> {
      Router.push(EnterRoomView.path);
    });

    JButton createButton = new JButton("部屋を作る");
    ButtonActionAttacher.attach(createButton, () -> {
      Router.push(CreateRoomView.path);
    });

    panel.add(label, BorderLayout.CENTER);
    panel.add(enterButton, BorderLayout.CENTER);
    panel.add(createButton, BorderLayout.CENTER);
    add(panel, BorderLayout.CENTER);
  }
}
