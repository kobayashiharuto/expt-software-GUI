package jp.waseda.asagi.kobayashi.controller;

import java.awt.Color;

import javax.swing.JLabel;

import jp.waseda.asagi.kobayashi.entities.Comment;
import jp.waseda.asagi.kobayashi.entities.Listener;
import jp.waseda.asagi.kobayashi.entities.Tip;
import jp.waseda.asagi.kobayashi.entities.User;
import jp.waseda.asagi.kobayashi.router.Router;
import jp.waseda.asagi.kobayashi.services.RoomListenService;
import jp.waseda.asagi.kobayashi.services.RoomService;
import jp.waseda.asagi.kobayashi.services.RoomPostService;
import jp.waseda.asagi.kobayashi.states.UserState;
import jp.waseda.asagi.kobayashi.utils.CustomDialog;
import jp.waseda.asagi.kobayashi.utils.OriginalResult;
import jp.waseda.asagi.kobayashi.views.RoomView;

public class RoomViewController {
  private final RoomView view;
  private final RoomListenService roomListenService = new RoomListenService();
  private final RoomService roomService = new RoomService();
  private final RoomPostService roomPostService = new RoomPostService();
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
    roomPostService.postComment(user, roomID, comment, (result) -> commentPostCallback(result));
  }

  public void postTip() {
    final String tip = view.tipTextField.getText();
    view.tipTextField.setText("");
    final User user = UserState.getInstance().get();

    if (tip.isEmpty()) {
      CustomDialog.showError("エラー", "チップを入力してください");
      return;
    }
    if (!tip.matches("^[0-9]+$")) {
      CustomDialog.showError("エラー", "チップは数字で入力してください");
      return;
    }
    final int amount = Integer.parseInt(tip);
    if (amount < 1) {
      CustomDialog.showError("エラー", "チップは1以上の数字で入力してください");
      return;
    }
    if (amount > user.deposit) {
      CustomDialog.showError("エラー", "チップが足りません");
      return;
    }
    roomPostService.postTip(user, roomID, amount, (result) -> tipPostCallback(result));
  }

  public void listenSetup(boolean isCreated, String roomID) {
    final User user = UserState.getInstance().get();
    if (isCreated) {
      roomListenService.setupForStreamer(user, roomID, (result) -> streamerCallback(result));
    } else {
      roomListenService.setupForListener(user, roomID, (result) -> listenerCallback(result));
    }
  }

  public void dispose(boolean isCreated) {
    roomListenService.dispose();
    final User user = UserState.getInstance().get();
    if (isCreated) {
      roomService.stopStreaming(roomID, (result) -> exitCallback(result));
    } else {
      roomService.quitRoom(roomID, user.id, (result) -> exitCallback(result));
    }
  }

  private void listenerCallback(OriginalResult<?> result) {
    switch (result.type) {
      case success:
        if (result.value instanceof Comment) {
          final Comment comment = (Comment) result.value;
          final JLabel commentLabel = new JLabel(comment.name + ": " + comment.comment);
          view.scrollPanel.add(commentLabel, 0);
          view.revalidate();
          return;
        }
        if (result.value instanceof Tip) {
          final Tip tip = (Tip) result.value;
          final JLabel commentLabel = new JLabel(tip.name + "さんが" + tip.amount + "ポイントを送りました");
          commentLabel.setForeground(Color.RED);
          view.scrollPanel.add(commentLabel, 0);
          view.revalidate();
          return;
        }
      case failure:
        CustomDialog.showError("エラー", result.error.message);
        Router.pop();
        break;
    }
  }

  private void streamerCallback(OriginalResult<?> result) {
    switch (result.type) {
      case success:
        if (result.value instanceof Comment) {
          final Comment comment = (Comment) result.value;
          System.out.println("comment: " + comment.name + ", " + comment.comment);
          final JLabel commentLabel = new JLabel(comment.name + ": " + comment.comment);
          view.scrollPanel.add(commentLabel, 0);
          view.revalidate();
          return;
        }
        if (result.value instanceof Tip) {
          final Tip tip = (Tip) result.value;
          final JLabel commentLabel = new JLabel(tip.name + "さんが" + tip.amount + "ポイントを送りました");
          commentLabel.setForeground(Color.RED);
          final User user = UserState.getInstance().get();
          user.deposit += tip.amount;
          UserState.getInstance().change(user);
          view.scrollPanel.add(commentLabel, 0);
          view.revalidate();
          return;
        }
        if (result.value instanceof Listener) {
          final Listener listener = (Listener) result.value;
          roomListenService.addlistener(listener);
          return;
        }
        if (result.value instanceof String) {
          final String uid = (String) result.value;
          roomListenService.removeLisnter(uid);
          return;
        }
        break;
      case failure:
        CustomDialog.showError("エラー", result.error.message);
        Router.pop();
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

  private void tipPostCallback(OriginalResult<Integer> result) {
    switch (result.type) {
      case success:
        final int amount = result.value;
        final User user = UserState.getInstance().get();
        user.deposit = amount;
        UserState.getInstance().change(user);
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
