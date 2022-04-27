package controller;

import entities.User;
import router.Router;
import services.AuthService;
import utils.OriginalResult;
import views.HomeView;
import views.SignupView;

public class SignupViewController {
    private final SignupView view;
  
    public SignupViewController(SignupView view) {
      this.view = view;
    }

    public void signup() {
      final String name = view.nameTextField.getText();
      final String password = view.passwordTextField.getText();
      AuthService.signup(name, password, (result) -> signupCallback(result));
    }
  
    static void signupCallback(OriginalResult<User> result) {
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
