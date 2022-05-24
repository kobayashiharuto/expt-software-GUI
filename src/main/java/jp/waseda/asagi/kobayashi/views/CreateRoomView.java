package jp.waseda.asagi.kobayashi.views;

import javax.swing.*;

import jp.waseda.asagi.kobayashi.controller.CreateRoomViewController;
import jp.waseda.asagi.kobayashi.utils.ButtonActionAttacher;
import jp.waseda.asagi.kobayashi.utils.OriginalView;

import java.awt.*;

public class CreateRoomView extends OriginalView {
  static public final String path = "createRoom";
  private final CreateRoomViewController controller = new CreateRoomViewController(this);

  public final JButton createButton;
  public final JTextField roomNameTextField;

  public CreateRoomView() {
    super(path, "部屋作成", true);

    JPanel gridPanel = new JPanel();

    GridBagLayout gridLayout = new GridBagLayout();
    gridPanel.setLayout(gridLayout);
    GridBagConstraints gbc = new GridBagConstraints();

    JLabel label = new JLabel("作成したい部屋の名前を入力してください");
    roomNameTextField = new JTextField(20);
    createButton = new JButton("部屋を作る");
    ButtonActionAttacher.attach(createButton, () -> {
      controller.createRoom();
    });

    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.gridwidth = 2;
    gbc.insets = new Insets(10, 0, 30, 0);
    gridLayout.setConstraints(label, gbc);
    gridPanel.add(label);

    gbc.gridx = 0;
    gbc.gridy = 1;
    gbc.gridwidth = 1;
    gbc.insets = new Insets(0, 0, 15, 0);
    gridLayout.setConstraints(roomNameTextField, gbc);
    gridPanel.add(roomNameTextField);

    gbc.gridx = 0;
    gbc.gridy = 2;
    gbc.gridwidth = 1;
    gbc.insets = new Insets(0, 0, 0, 0);
    gridLayout.setConstraints(createButton, gbc);
    gridPanel.add(createButton);

    mainPanel.add(gridPanel);
  }
}
