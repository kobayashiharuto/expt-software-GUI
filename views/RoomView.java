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

  public final JPanel panel = new JPanel();
  public final JPanel commentList = new JPanel();
  public final JLabel roomNameLabel = new JLabel("部屋");
  public final JLabel errorLabel = new JLabel();
  public final JTextField commentTextField = new JTextField();

  public RoomView() {
    super(path, true);

    final JButton sendButton = new JButton("送信");

    ButtonActionAttacher.attach(sendButton, () -> {
      controller.postCommnet();
    });

    panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
    panel.add(errorLabel, BorderLayout.CENTER);
    panel.add(roomNameLabel, BorderLayout.CENTER);
    panel.add(commentList, BorderLayout.CENTER);
    panel.add(commentTextField, BorderLayout.CENTER);
    panel.add(sendButton, BorderLayout.CENTER);
    add(panel, BorderLayout.CENTER);
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
    commentList.removeAll();
    revalidate();
    controller.dispose();
  }
}
