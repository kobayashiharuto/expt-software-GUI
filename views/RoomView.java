package views;

import javax.swing.*;

// import controller.RoomViewController;
import router.Router;
import utils.ButtonActionAttacher;
import utils.OriginalView;

import java.awt.*;

public class RoomView extends OriginalView {
  static public final String path = "home";
  // private final RoomViewController controller = new RoomViewController(this);

  public RoomView() {
    super(path);

    JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

    JLabel label = new JLabel("部屋");

    JButton backButton = new JButton("戻る");
    ButtonActionAttacher.attach(backButton, () -> {
      Router.pop();
    });
    
    panel.add(label, BorderLayout.CENTER);
    panel.add(backButton, BorderLayout.CENTER);
    add(panel, BorderLayout.CENTER);
  }
}
