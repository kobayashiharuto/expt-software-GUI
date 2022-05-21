package controller;

import java.util.HashMap;

import entities.Room;
import entities.User;
import router.Router;
import services.RoomService;
import settings.Settings;
import utils.CustomDialog;
import utils.OriginalResult;
import utils.Validation;
import views.CreateRoomView;
import views.RoomView;

public class CreateRoomViewController {
  private final CreateRoomView view;
  private final RoomService roomService = new RoomService();

  public CreateRoomViewController(CreateRoomView view) {
    this.view = view;
  }

  public void createRoom() {
    final String roomName = view.roomNameTextField.getText();
    final User user = User.generateMockUser();

    if (!Validation.check(roomName, Settings.ROOM_NAME_LIMIT_MIN, Settings.ROOM_NAME_LIMIT_MAX)) {
      final String message = "部屋の名前は" + Settings.ROOM_NAME_LIMIT_MIN + "以上" + Settings.ROOM_NAME_LIMIT_MAX
          + "以下で入力してください";
      CustomDialog.showError("エラー", message);
      return;
    }

    roomService.create(roomName, user, (result) -> createCallback(result));
  }

  private void createCallback(OriginalResult<Room> result) {
    switch (result.type) {
      case success:
        System.out.println("create success: " + result.value.id);
        HashMap<String, String> map = new HashMap<String, String>() {
          {
            put("roomID", result.value.id);
            put("roomName", result.value.name);
            put("isCreated", "true");
          }
        };
        Router.push(RoomView.path, map);
        break;
      case failure:
        System.out.println("create fail");
        CustomDialog.showError("エラー", result.error.message);
        break;
    }
  }
}
