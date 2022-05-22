package jp.waseda.asagi.kobayashi.controller;

import java.util.HashMap;
import java.util.List;

import jp.waseda.asagi.kobayashi.components.RoomCell;
import jp.waseda.asagi.kobayashi.entities.Room;
import jp.waseda.asagi.kobayashi.entities.User;
import jp.waseda.asagi.kobayashi.router.Router;
import jp.waseda.asagi.kobayashi.services.RoomService;
import jp.waseda.asagi.kobayashi.states.UserState;
import jp.waseda.asagi.kobayashi.utils.CustomDialog;
import jp.waseda.asagi.kobayashi.utils.OriginalResult;
import jp.waseda.asagi.kobayashi.views.EnterRoomView;
import jp.waseda.asagi.kobayashi.views.RoomView;

public class EnterRoomViewController {
  private final EnterRoomView view;
  private final RoomService roomService = new RoomService();

  public EnterRoomViewController(EnterRoomView view) {
    this.view = view;
  }

  public void load() {
    view.scrollPanel.removeAll();
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
        break;
    }
  }

  private void getRoomsCallback(OriginalResult<List<Room>> result) {
    switch (result.type) {
      case success:
        System.out.println("get success");
        final List<Room> rooms = result.value;
        for (Room room : rooms) {
          System.out.println("get " + room.name);
          final RoomCell roomCell = new RoomCell(room, (_room) -> enterRoom(_room));
          view.scrollPanel.add(roomCell);
          view.revalidate();
        }
        break;
      case failure:
        System.out.println("get fail");
        CustomDialog.showError("エラー", result.error.message);
        break;
    }
  }
}
