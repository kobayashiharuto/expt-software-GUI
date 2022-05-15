package controller;

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

  public CreateRoomViewController(CreateRoomView view) {
    this.view = view;
  }

  public void createRoom() {
    final String roomName = view.roomNameTextField.getText();
    final User user = User.generateMockUser();

    if (!Validation.check(roomName, Settings.ROOM_NAME_LIMIT_MIN, Settings.ROOM_NAME_LIMIT_MAX)) {
      final String message = "部屋の名前は" + Settings.ROOM_NAME_LIMIT_MIN + "以上" + Settings.ROOM_NAME_LIMIT_MAX
          + "以下で入力してください";
      view.errorLabel.setText(message);
      CustomDialog.showError("エラー", message);
      return;
    }

    RoomService.create(roomName, user, (result) -> createCallback(result));
  }

  private void createCallback(OriginalResult<Room> result) {
    switch (result.type) {
      case success:
        System.out.println("create success: " + result.value.id);
        Router.push(RoomView.path);
        break;
      case failure:
        System.out.println("create fail");
        CustomDialog.showError("エラー", result.error.message);
        view.errorLabel.setText(result.error.message);
        break;
    }
  }
}
