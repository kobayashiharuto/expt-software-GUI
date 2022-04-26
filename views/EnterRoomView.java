package views;

import javax.swing.*;

import controller.EnterRoomViewController;
import utils.ButtonActionAttacher;
import utils.OriginalView;

import java.awt.*;

public class EnterRoomView extends OriginalView {
  static public final String path = "enterRoom";
  private final EnterRoomViewController controller = new EnterRoomViewController(this);

  public final JButton enterButton;
  public final JTextField roomNumTextField;
  public final JLabel errorLabel;

  public EnterRoomView() {
    super(path, true);

    JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

    JLabel label = new JLabel("部屋番号を入力してください");
    errorLabel = new JLabel("");
    roomNumTextField = new JTextField(20);
    enterButton = new JButton("部屋に入る");

    ButtonActionAttacher.attach(enterButton, () -> {
      controller.enterRoom();
    });

    // cardPanelとカード移動用ボタンをJFrameに配置
    panel.add(label, BorderLayout.CENTER);
    panel.add(errorLabel, BorderLayout.CENTER);
    panel.add(roomNumTextField, BorderLayout.CENTER);
    panel.add(enterButton, BorderLayout.CENTER);
    add(panel, BorderLayout.CENTER);
  }
}
