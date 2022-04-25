package controller;

import router.Router;
import views.CreateRoomView;
import views.EnterRoomView;
import views.HomeView;

public class HomeViewController {
  private final HomeView view;

  public HomeViewController(HomeView view) {
    this.view = view;
  }

  public void transitionCreateRoomView() {
    Router.push(CreateRoomView.path);
  }

  public void transitionEnterRoomView() {
    Router.push(EnterRoomView.path);
  }

  static void loginCallback(boolean result) {
    if (result) {
      System.out.println("login success");
    } else {
      System.out.println("login fail");
    }
  }
}
