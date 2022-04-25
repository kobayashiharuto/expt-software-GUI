package views;

import javax.swing.*;

import router.Router;
import utils.ButtonActionAttacher;

import java.awt.*;

public class CreateRoomView extends JPanel {
  static public final String path = "createRoom";

  public final JButton enterButton;
  public final JTextField nameTextField;
  public final JLabel label;

  public CreateRoomView() {

    JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

    label = new JLabel("部屋番号を入力してください");

    nameTextField = new JTextField(20);

    enterButton = new JButton("部屋に入る");

    ButtonActionAttacher.attach(enterButton, () -> {
      Router.push(EnterRoomView.path);
    });

    // cardPanelとカード移動用ボタンをJFrameに配置
    panel.add(label, BorderLayout.CENTER);
    panel.add(nameTextField, BorderLayout.CENTER);
    panel.add(enterButton, BorderLayout.CENTER);
    add(panel, BorderLayout.CENTER);
  }
}
