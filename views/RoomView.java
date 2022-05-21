package views;

import javax.swing.*;

import controller.RoomViewController;
import utils.ButtonActionAttacher;
import utils.OriginalView;

import java.awt.*;
import java.util.Map;

public class RoomView extends OriginalView {
  static public final String path = "room";
  private final RoomViewController controller = new RoomViewController(this);

  public final JPanel scrollPanel = new JPanel();
  public final JLabel roomNameLabel = new JLabel("部屋");
  public final JTextField commentTextField = new JTextField();

  public RoomView() {
    super(path, "部屋", true);

    scrollPanel.setLayout(new BoxLayout(scrollPanel, BoxLayout.Y_AXIS));

    JPanel panel = new JPanel();
    BorderLayout layout = new BorderLayout();
    panel.setLayout(layout);

    final JButton sendButton = new JButton("送信");
    ButtonActionAttacher.attach(sendButton, () -> {
      controller.postCommnet();
    });

    JScrollPane scrollpane = new JScrollPane(scrollPanel);

    JPanel boxPanel = new JPanel();
    boxPanel.setLayout(new BoxLayout(boxPanel, BoxLayout.X_AXIS));
    boxPanel.add(commentTextField);
    boxPanel.add(sendButton);

    panel.add(scrollpane, BorderLayout.CENTER);
    panel.add(boxPanel, BorderLayout.SOUTH);
    mainPanel.add(panel);
  }

  @Override
  public void onAppear(Map<String, String> param) {
    final String roomID = param.get("roomID");
    final String roomName = param.get("roomName");
    final boolean isCreated = param.get("isCreated") == "true";
    System.out.println("部屋に入りました");
    System.out.println(roomID);
    System.out.println(isCreated);
    roomNameLabel.setText(roomName);
    controller.listenComment(roomID);
  }

  @Override
  public void onDisapper() {
    System.out.println("部屋から退出しました");
    scrollPanel.removeAll();
    revalidate();
    controller.dispose();
  }
}
