package views;

import javax.swing.*;

import router.Router;
import utils.ButtonActionAttacher;

import java.awt.*;
import java.awt.event.*;

public class HomeView extends JPanel {
  static public final String path = "home";

  public final JButton enterButton;
  public final JButton createButton;
  public final JLabel label;

  public HomeView() {
    JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

    label = new JLabel("ようこそ");

    enterButton = new JButton("部屋に入る");
    ButtonActionAttacher.attach(enterButton, () -> {
      Router.push(EnterRoomView.path);
    });

    createButton = new JButton("部屋を作る");
    ButtonActionAttacher.attach(enterButton, () -> {
      Router.push(CreateRoomView.path);
    });

    // cardPanelとカード移動用ボタンをJFrameに配置
    panel.add(label, BorderLayout.CENTER);
    panel.add(enterButton, BorderLayout.CENTER);
    panel.add(createButton, BorderLayout.CENTER);
    add(panel, BorderLayout.CENTER);
  }
}
