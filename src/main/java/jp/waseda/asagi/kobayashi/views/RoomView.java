package jp.waseda.asagi.kobayashi.views;

import javax.swing.*;

import jp.waseda.asagi.kobayashi.controller.RoomViewController;
import jp.waseda.asagi.kobayashi.states.UserState;
import jp.waseda.asagi.kobayashi.utils.ButtonActionAttacher;
import jp.waseda.asagi.kobayashi.utils.OriginalView;

import java.awt.*;
import java.util.Map;

public class RoomView extends OriginalView {
  static public final String path = "room";
  private final RoomViewController controller = new RoomViewController(this);

  public final JPanel scrollPanel = new JPanel();
  public final JLabel roomNameLabel = new JLabel("部屋");
  public final JTextField commentTextField = new JTextField();
  public final JTextField tipTextField = new JTextField();
  public final JButton sendCommentButton = new JButton("送信");
  public final JButton sendTipButton = new JButton("送信");

  private boolean isCreated = false;

  public RoomView() {
    super(path, "部屋", true);

    final JLabel depositLabel = new JLabel("所持ポイント: 0");
    UserState.getInstance().listen((user) -> {
      if (user == null)
        return;
      depositLabel.setText("所持ポイント: " + user.deposit);
    });

    scrollPanel.setLayout(new BoxLayout(scrollPanel, BoxLayout.Y_AXIS));

    final JPanel panel = new JPanel();
    BorderLayout layout = new BorderLayout();
    panel.setLayout(layout);

    final JLabel commentLabel = new JLabel("コメント: ");
    ButtonActionAttacher.attach(sendCommentButton, () -> {
      controller.postCommnet();
    });
    final JLabel tipLabel = new JLabel("ポイント: ");
    ButtonActionAttacher.attach(sendTipButton, () -> {
      controller.postTip();
    });

    JScrollPane scrollpane = new JScrollPane(scrollPanel);

    JPanel textFilledsBoxPanel = new JPanel();
    JPanel tipBoxPanel = new JPanel();
    JPanel commentBoxPanel = new JPanel();

    textFilledsBoxPanel.setLayout(new BoxLayout(textFilledsBoxPanel, BoxLayout.Y_AXIS));
    tipBoxPanel.setLayout(new BoxLayout(tipBoxPanel, BoxLayout.X_AXIS));
    commentBoxPanel.setLayout(new BoxLayout(commentBoxPanel, BoxLayout.X_AXIS));

    commentBoxPanel.add(commentLabel);
    commentBoxPanel.add(commentTextField);
    commentBoxPanel.add(sendCommentButton);
    tipBoxPanel.add(tipLabel);
    tipBoxPanel.add(tipTextField);
    tipBoxPanel.add(sendTipButton);
    textFilledsBoxPanel.add(commentBoxPanel);
    textFilledsBoxPanel.add(tipBoxPanel);

    panel.add(depositLabel, BorderLayout.NORTH);
    panel.add(scrollpane, BorderLayout.CENTER);
    panel.add(textFilledsBoxPanel, BorderLayout.SOUTH);
    mainPanel.add(panel);
  }

  @Override
  public void onAppear(Map<String, String> param) {
    final String roomID = param.get("roomID");
    final String roomName = param.get("roomName");
    isCreated = param.get("isCreated") == "true";
    controller.attachRoomID(roomID);

    System.out.println("部屋に入りました");
    System.out.println(roomID);
    System.out.println(isCreated);

    roomNameLabel.setText(roomName);
    changeBackButtonText(isCreated ? "配信停止" : "部屋を出る");
    controller.listenSetup(isCreated, roomID);
    tipTextField.setEditable(!isCreated);
    sendTipButton.setEnabled(!isCreated);
  }

  @Override
  public void onDisapper() {
    System.out.println("部屋から退出しました");
    scrollPanel.removeAll();
    revalidate();
    controller.dispose(isCreated);
  }
}
