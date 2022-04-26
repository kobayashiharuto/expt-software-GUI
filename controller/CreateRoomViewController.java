package controller;

import entities.Room;
import entities.User;
import router.Router;
import services.RoomService;
import utils.OriginalResult;
import views.CreateRoomView;
import views.RoomView;

public class CreateRoomViewController {
  private final CreateRoomView view;

  public CreateRoomViewController(CreateRoomView view) {
    this.view = view;
  }
  
  public void createRoom() {
    final String roomNum = view.roomNumTextField.getText();
    final User user = User.generateMockUser();
    RoomService.create(roomNum, user, (result) -> createCallback(result));
  }

  private static void createCallback(OriginalResult<Room> result) {
    switch (result.type) {
      case success:
        System.out.println("login success: " + result.value.id);
        Router.push(RoomView.path);
        break;
      case failure:
        System.out.println("login fail");
        break;
    }
  }
}
