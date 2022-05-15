package views;

import javax.swing.*;

// import controller.RoomViewController;
import utils.OriginalView;

import java.awt.*;

public class RoomView extends OriginalView {
  static public final String path = "room";
  // private final RoomViewController controller = new RoomViewController(this);

  public RoomView() {
    super(path, true);

    JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

    JLabel label = new JLabel("部屋");
    
    panel.add(label, BorderLayout.CENTER);
    add(panel, BorderLayout.CENTER);
  }
}
