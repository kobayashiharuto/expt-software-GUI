package jp.waseda.asagi.kobayashi.controller;

import java.util.HashMap;

import jp.waseda.asagi.kobayashi.entities.Room;
import jp.waseda.asagi.kobayashi.entities.User;
import jp.waseda.asagi.kobayashi.router.Router;
import jp.waseda.asagi.kobayashi.services.RoomService;
import jp.waseda.asagi.kobayashi.settings.Settings;
import jp.waseda.asagi.kobayashi.utils.CustomDialog;
import jp.waseda.asagi.kobayashi.utils.OriginalResult;
import jp.waseda.asagi.kobayashi.utils.Validation;
import jp.waseda.asagi.kobayashi.views.CreateRoomView;
import jp.waseda.asagi.kobayashi.views.RoomView;

public class CreateRoomViewController {
  private final CreateRoomView view;
  private final RoomService roomService = new RoomService();

  public CreateRoomViewController(CreateRoomView view) {
    this.view = view;
  }

  public void createRoom() {
    final String roomName = view.roomNameTextField.getText();
    final int myListenPort = Settings.CLIENT_LISTEN_PORT;

    if (!Validation.check(roomName, Settings.ROOM_NAME_LIMIT_MIN, Settings.ROOM_NAME_LIMIT_MAX)) {
      final String message = "部屋の名前は" + Settings.ROOM_NAME_LIMIT_MIN + "以上" + Settings.ROOM_NAME_LIMIT_MAX
          + "以下で入力してください";
      CustomDialog.showError("エラー", message);
      return;
    }

    roomService.create(myListenPort, roomName, (result) -> createCallback(result));
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
