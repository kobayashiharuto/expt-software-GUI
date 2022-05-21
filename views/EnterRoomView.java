package views;

import javax.swing.*;

import controller.EnterRoomViewController;
import utils.OriginalView;

import java.awt.*;
import java.util.Map;

public class EnterRoomView extends OriginalView {
  static public final String path = "enterRoom";
  private final EnterRoomViewController controller = new EnterRoomViewController(this);

  public final JPanel scrollPanel = new JPanel();

  public EnterRoomView() {
    super(path, "部屋一覧", true);

    scrollPanel.setLayout(new BoxLayout(scrollPanel, BoxLayout.Y_AXIS));

    final JPanel gridPanel = new JPanel();
    final GridLayout layout = new GridLayout();
    gridPanel.setLayout(layout);

    JScrollPane scrollpane = new JScrollPane(scrollPanel);

    gridPanel.add(scrollpane);
    mainPanel.add(gridPanel);
  }

  @Override
  public void onAppear(Map<String, String> param) {
    controller.load();
  }

  @Override
  public void onDisapper() {
  }
}
