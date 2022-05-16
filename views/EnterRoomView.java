package views;

import javax.swing.*;

import controller.EnterRoomViewController;
import utils.OriginalView;

import java.awt.*;
import java.util.Map;

public class EnterRoomView extends OriginalView {
  static public final String path = "enterRoom";
  private final EnterRoomViewController controller = new EnterRoomViewController(this);

  public final JLabel errorLabel = new JLabel();
  public final JLabel loadingLabel = new JLabel();
  public final JPanel panel = new JPanel();

  public EnterRoomView() {
    super(path, true);

    panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

    // cardPanelとカード移動用ボタンをJFrameに配置
    panel.add(loadingLabel, BorderLayout.CENTER);
    panel.add(errorLabel, BorderLayout.CENTER);
    add(panel, BorderLayout.CENTER);
  }

  @Override
  public void onAppear(Map<String, String> param) {
    controller.getRooms();
  }

  @Override
  public void onDisapper() {
  }
}
