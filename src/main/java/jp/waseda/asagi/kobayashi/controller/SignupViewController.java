package jp.waseda.asagi.kobayashi.controller;

import jp.waseda.asagi.kobayashi.entities.User;
import jp.waseda.asagi.kobayashi.router.Router;
import jp.waseda.asagi.kobayashi.services.AuthService;
import jp.waseda.asagi.kobayashi.settings.Settings;
import jp.waseda.asagi.kobayashi.states.UserState;
import jp.waseda.asagi.kobayashi.utils.CustomDialog;
import jp.waseda.asagi.kobayashi.utils.OriginalResult;
import jp.waseda.asagi.kobayashi.utils.Validation;
import jp.waseda.asagi.kobayashi.views.HomeView;
import jp.waseda.asagi.kobayashi.views.SignupView;

public class SignupViewController {
  private final SignupView view;
  private final AuthService authService = new AuthService();

  public SignupViewController(SignupView view) {
    this.view = view;
  }

  public void signup() {
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

    authService.signup(name, password, (result) -> signupCallback(result));
  }

  private void signupCallback(OriginalResult<User> result) {
    switch (result.type) {
      case success:
        System.out.println("signup success: " + result.value.id);
        UserState.getInstance().change(result.value);
        Router.push(HomeView.path, null);
        break;
      case failure:
        System.out.println("signup fail");
        CustomDialog.showError("エラー", result.error.message);
        view.errorLabel.setText(result.error.message);
        break;
    }
  }
}
