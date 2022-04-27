package controller;

import entities.User;
import router.Router;
import services.AuthService;
import states.UserState;
import utils.OriginalResult;
import views.HomeView;
import views.LoginView;

public class LoginViewController {
  private final LoginView view;

  public LoginViewController(LoginView view) {
    this.view = view;
  }

  public void login() {
    final String name = view.nameTextField.getText();
    final String password = view.passwordTextField.getText();
    AuthService.login(name, password, (result) -> loginCallback(result));
  }

  static void loginCallback(OriginalResult<User> result) {
    switch (result.type) {
      case success:
        System.out.println("login success: " + result.value.id);
        UserState.getInstance().change(result.value);
        Router.push(HomeView.path);
        break;
      case failure:
        System.out.println("login fail");
        break;
    }
  }
}
