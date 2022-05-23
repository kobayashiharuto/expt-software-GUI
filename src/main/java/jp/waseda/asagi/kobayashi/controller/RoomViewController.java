package jp.waseda.asagi.kobayashi.controller;

import javax.swing.JLabel;

import jp.waseda.asagi.kobayashi.entities.Comment;
import jp.waseda.asagi.kobayashi.entities.User;
import jp.waseda.asagi.kobayashi.services.RoomListenService;
import jp.waseda.asagi.kobayashi.services.RoomService;
import jp.waseda.asagi.kobayashi.services.CommentPostService;
import jp.waseda.asagi.kobayashi.states.UserState;
import jp.waseda.asagi.kobayashi.utils.CustomDialog;
import jp.waseda.asagi.kobayashi.utils.OriginalResult;
import jp.waseda.asagi.kobayashi.views.RoomView;

public class RoomViewController {
  private final RoomView view;
  private final RoomListenService roomListenService = new RoomListenService();
  private final RoomService roomService = new RoomService();
  private final CommentPostService commentPostService = new CommentPostService();
  private String roomID;

  public RoomViewController(RoomView view) {
    this.view = view;
  }

  public void attachRoomID(String roomID) {
    this.roomID = roomID;
  }

  public void postCommnet() {
    final String comment = view.commentTextField.getText();
    view.commentTextField.setText("");
    final User user = UserState.getInstance().get();
    commentPostService.post(user, roomID, comment, (result) -> commentPostCallback(result));
  }

  public void listenComment(String roomID) {
    final User user = UserState.getInstance().get();
    roomListenService.listen(user, roomID, (result) -> commentGetCallback(result));
  }

  public void dispose(boolean isCreated) {
    roomListenService.dispose();
    if (isCreated) {
      roomService.stopStreaming(roomID, (result) -> exitCallback(result));
    } else {
      roomService.quitRoom(roomID, (result) -> exitCallback(result));
    }
  }

  private void commentGetCallback(OriginalResult<Comment> result) {
    switch (result.type) {
      case success:
        System.out.println("comment: " + result.value.name + ", " + result.value.comment);
        final JLabel commentLabel = new JLabel(result.value.name + ": " + result.value.comment);
        view.scrollPanel.add(commentLabel, 0);
        view.revalidate();
        break;
      case failure:
        CustomDialog.showError("エラー", result.error.message);
        break;
    }
  }

  private void commentPostCallback(OriginalResult<Boolean> result) {
    switch (result.type) {
      case success:
        break;
      case failure:
        CustomDialog.showError("エラー", result.error.message);
        break;
    }
  }

  private void exitCallback(OriginalResult<Boolean> result) {
    switch (result.type) {
      case success:
        break;
      case failure:
        CustomDialog.showError("エラー", result.error.message);
        break;
    }
  }
}
