package controller;

import entities.Room;
import entities.User;
import router.Router;
import services.RoomService;
import settings.Settings;
import utils.CustomDialog;
import utils.OriginalResult;
import utils.Validation;
import views.EnterRoomView;
import views.RoomView;

public class EnterRoomViewController {
  private final EnterRoomView view;

  public EnterRoomViewController(EnterRoomView view) {
    this.view = view;
  }

  public void enterRoom() {
    final String roomNum = view.roomNumTextField.getText();
    final User user = User.generateMockUser();

    if (!Validation.check(roomNum, Settings.ROOM_NUM_LIMIT_MIN, Settings.ROOM_NUM_LIMIT_MAX)) {
      final String message = "部屋番号は" + Settings.ROOM_NUM_LIMIT_MIN + "以上" + Settings.ROOM_NUM_LIMIT_MAX + "以下で入力してください";
      view.errorLabel.setText(message);
      CustomDialog.showError("エラー", message);
      return;
    }

    RoomService.enter(roomNum, user, (result) -> enterCallback(result));
  }

  private void enterCallback(OriginalResult<Room> result) {
    switch (result.type) {
      case success:
        System.out.println("enter success: " + result.value.id);
        Router.push(RoomView.path);
        break;
      case failure:
        System.out.println("enter fail");
        CustomDialog.showError("エラー", result.error.message);
        view.errorLabel.setText(result.error.message);
        break;
    }
  }
}
