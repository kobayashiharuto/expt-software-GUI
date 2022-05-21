package views;

import javax.swing.*;

import controller.CreateRoomViewController;
import utils.ButtonActionAttacher;
import utils.OriginalView;

import java.awt.*;
import java.util.Map;

public class CreateRoomView extends OriginalView {
  static public final String path = "createRoom";
  private final CreateRoomViewController controller = new CreateRoomViewController(this);

  public final JButton createButton;
  public final JTextField roomNameTextField;
  public final JLabel errorLabel;

  public CreateRoomView() {
    super(path, "部屋作成", true);

    JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

    JLabel label = new JLabel("部屋の名前を入力してください");
    errorLabel = new JLabel("");
    roomNameTextField = new JTextField(20);
    createButton = new JButton("部屋を作る");

    ButtonActionAttacher.attach(createButton, () -> {
      controller.createRoom();
    });

    // cardPanelとカード移動用ボタンをJFrameに配置
    panel.add(label, BorderLayout.CENTER);
    panel.add(errorLabel, BorderLayout.CENTER);
    panel.add(roomNameTextField, BorderLayout.CENTER);
    panel.add(createButton, BorderLayout.CENTER);
    add(panel, BorderLayout.CENTER);
  }

  @Override
  public void onAppear(Map<String, String> param) {
  }

  @Override
  public void onDisapper() {
  }
}
