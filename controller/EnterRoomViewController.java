package controller;

import java.util.HashMap;
import java.util.List;

import javax.swing.JButton;

import entities.Room;
import entities.User;
import router.Router;
import services.RoomService;
import states.UserState;
import utils.ButtonActionAttacher;
import utils.CustomDialog;
import utils.OriginalResult;
import views.EnterRoomView;
import views.RoomView;

public class EnterRoomViewController {
  private final EnterRoomView view;
  private final RoomService roomService = new RoomService();

  public EnterRoomViewController(EnterRoomView view) {
    this.view = view;
  }

  public void getRooms() {
    view.loadingLabel.setText("部屋一覧を取得中...");
    roomService.getRooms((result) -> getRoomsCallback(result));
  }

  public void enterRoom(Room room) {
    final User user = UserState.getInstance().get();
    roomService.enter(room, user, (result) -> enterCallback(result));
  }

  private void enterCallback(OriginalResult<Room> result) {
    switch (result.type) {
      case success:
        System.out.println("enter success: " + result.value.id);
        HashMap<String, String> map = new HashMap<String, String>() {
          {
            put("roomID", result.value.id);
            put("roomName", result.value.name);
            put("isCreated", "false");
          }
        };
        Router.push(RoomView.path, map);
        break;
      case failure:
        System.out.println("enter fail");
        CustomDialog.showError("エラー", result.error.message);
        view.errorLabel.setText(result.error.message);
        break;
    }
  }

  private void getRoomsCallback(OriginalResult<List<Room>> result) {
    view.loadingLabel.setText("");
    switch (result.type) {
      case success:
        System.out.println("get success");
        final List<Room> rooms = result.value;
        for (Room room : rooms) {
          System.out.println("get " + room.name);
          final JButton button = new JButton(room.name);
          ButtonActionAttacher.attach(button, () -> {
            enterRoom(room);
          });
          view.panel.add(button);
          view.revalidate();
        }
        break;
      case failure:
        System.out.println("get fail");
        CustomDialog.showError("エラー", result.error.message);
        view.errorLabel.setText(result.error.message);
        break;
    }
  }
}
