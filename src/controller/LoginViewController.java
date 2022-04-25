package controller;

import entities.User;
import router.Router;
import services.LoginViewService;
import utils.OriginalResult;
import views.HomeView;
import views.LoginView;

public class LoginViewController {
  private final LoginView view;
  private final LoginViewService service = new LoginViewService();

  public LoginViewController(LoginView view) {
    this.view = view;
  }

  public void login() {
    final String name = view.nameTextField.getText();
    final String password = view.passwordTextField.getText();
    service.login(name, password, (result) -> loginCallback(result));
  }

  static void loginCallback(OriginalResult<User> result) {
    switch (result.type) {
      case success:
        System.out.println("login success: " + result.value.id);
        Router.push(HomeView.path);
        break;
      case failure:
        System.out.println("login fail");
        break;
    }
  }
}
