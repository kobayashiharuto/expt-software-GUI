package controller;

import entities.Room;
import entities.User;
import router.Router;
import services.RoomService;
import utils.OriginalResult;
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
    RoomService.enter(roomNum, user, (result) -> enterCallback(result));
  }

  private static void enterCallback(OriginalResult<Room> result) {
    switch (result.type) {
      case success:
        System.out.println("enter success: " + result.value.id);
        Router.push(RoomView.path);
        break;
      case failure:
        System.out.println("enter fail");
        break;
    }
  }
}
