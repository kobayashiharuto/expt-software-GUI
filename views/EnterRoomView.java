package views;

import javax.swing.*;

import controller.EnterRoomViewController;
import router.Router;
import utils.ButtonActionAttacher;
import utils.OriginalView;

import java.awt.*;

public class EnterRoomView extends OriginalView {
  static public final String path = "enterRoom";
  private final EnterRoomViewController controller = new EnterRoomViewController(this);

  public final JLabel errorLabel;
  public final JPanel panel = new JPanel();

  public EnterRoomView() {
    super(path, true);

    errorLabel = new JLabel();

    panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
    JButton searchButton = new JButton("検索");
    ButtonActionAttacher.attach(searchButton, () -> {
      controller.getRooms();
    });

    JLabel label = new JLabel("部屋一覧");

    // cardPanelとカード移動用ボタンをJFrameに配置
    panel.add(label, BorderLayout.CENTER);
    panel.add(searchButton, BorderLayout.CENTER);
    panel.add(errorLabel, BorderLayout.CENTER);
    add(panel, BorderLayout.CENTER);
  }
}
