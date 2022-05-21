package controller;

import javax.swing.JLabel;

import entities.Comment;
import entities.User;
import services.CommentListenService;
import services.CommentPostService;
import states.UserState;
import utils.CustomDialog;
import utils.OriginalResult;
import views.RoomView;

public class RoomViewController {
  private final RoomView view;
  private final CommentListenService commentListenService = new CommentListenService();
  private final CommentPostService commentPostService = new CommentPostService();

  public RoomViewController(RoomView view) {
    this.view = view;
  }

  public void postCommnet() {
    final String comment = view.commentTextField.getText();
    view.commentTextField.setText("");
    final User user = UserState.getInstance().get();
    commentPostService.post(user, comment, (result) -> commentPostCallback(result));
  }

  public void listenComment(String roomID) {
    final User user = UserState.getInstance().get();
    commentListenService.listen(user, roomID, (result) -> commentGetCallback(result));
  }

  public void dispose() {
    commentListenService.dispose();
  }

  private void commentGetCallback(OriginalResult<Comment> result) {
    switch (result.type) {
      case success:
        System.out.println("comment: " + result.value.name + ", " + result.value.comment);
        final JLabel commentLabel = new JLabel(result.value.name + ": " + result.value.comment);
        view.commentList.add(commentLabel);
        view.revalidate();
        break;
      case failure:
        CustomDialog.showError("エラー", result.error.message);
        System.out.println("comment listen fail");
        break;
    }
  }

  private void commentPostCallback(OriginalResult<Boolean> result) {
    switch (result.type) {
      case success:
        System.out.println("comment send success");
        break;
      case failure:
        System.out.println("comment send fail");
        CustomDialog.showError("エラー", result.error.message);
        break;
    }
  }
}
