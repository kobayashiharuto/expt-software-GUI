package controller;

import entities.User;
import router.Router;
import services.AuthService;
import settings.Settings;
import states.UserState;
import utils.CustomDialog;
import utils.OriginalResult;
import utils.Validation;
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

    if (!Validation.check(name, Settings.USER_NAME_LIMIT_MIN, Settings.USER_NAME_LIMIT_MAX)) {
      final String message = "ユーザー名は" + Settings.USER_NAME_LIMIT_MIN + "以上" + Settings.USER_NAME_LIMIT_MAX
          + "以下で入力してください";
      view.errorLabel.setText(message);
      CustomDialog.showError("エラー", message);
      return;
    }

    if (!Validation.check(password, Settings.PASSWORD_LIMIT_MIN, Settings.PASSWORD_LIMIT_MAX)) {
      final String message = "パスワードは" + Settings.PASSWORD_LIMIT_MIN + "以上" + Settings.PASSWORD_LIMIT_MAX
          + "以下で入力してください";
      view.errorLabel.setText(message);
      CustomDialog.showError("エラー", message);
      return;
    }

    AuthService.login(name, password, (result) -> loginCallback(result));
  }

  private void loginCallback(OriginalResult<User> result) {
    switch (result.type) {
      case success:
        view.errorLabel.setText("");
        System.out.println("login success: " + result.value.id);
        UserState.getInstance().change(result.value);
        Router.push(HomeView.path);
        break;
      case failure:
        System.out.println("login fail");
        CustomDialog.showError("エラー", result.error.message);
        view.errorLabel.setText(result.error.message);
        break;
    }
  }
}
