package jp.waseda.asagi.kobayashi.components;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.function.Consumer;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import jp.waseda.asagi.kobayashi.entities.Room;
import jp.waseda.asagi.kobayashi.utils.ButtonActionAttacher;

public class RoomCell extends JPanel {
  public RoomCell(Room room, Consumer<Room> action) {
    setLayout(new BorderLayout());
    final JLabel label = new JLabel(room.name);
    final JButton button = new JButton("入室");
    ButtonActionAttacher.attach(button, () -> {
      action.accept(room);
    });

    add(label, BorderLayout.LINE_START);
    add(button, BorderLayout.LINE_END);
    setMaximumSize(new Dimension(1000, 30));
  }
}
