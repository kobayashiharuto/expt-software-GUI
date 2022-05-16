package router;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import javax.swing.*;

import utils.OriginalView;
import views.CreateRoomView;
import views.EnterRoomView;
import views.HomeView;
import views.InitialView;
import views.LoginView;
import views.RoomView;
import views.SignupView;

public class Router extends JFrame {
  private final JPanel cardPanel = new JPanel();
  private final Map<String, OriginalView> routes = new HashMap<>();
  private final CardLayout layout = new CardLayout();
  private final Stack<String> pageTransitionHistoryStack = new Stack<String>();

  private static Router singleton = new Router();

  private Router() {
    cardPanel.setLayout(layout);

    addRoute(new InitialView());
    pageTransitionHistoryStack.push(InitialView.path);

    addRoute(new LoginView());
    addRoute(new SignupView());
    addRoute(new CreateRoomView());
    addRoute(new EnterRoomView());
    addRoute(new HomeView());
    addRoute(new RoomView());

    Container contentPane = getContentPane();
    contentPane.add(cardPanel, BorderLayout.CENTER);
  }

  public static Router getInstance() {
    return singleton;
  }

  private void addRoute(OriginalView view) {
    cardPanel.add(view, view.path);
    routes.put(view.path, view);
  }

  private void _push(String path) {
    pageTransitionHistoryStack.push(path);
    System.out.println("route stack: " + pageTransitionHistoryStack);
    layout.show(cardPanel, path);
  }

  private void _pop() {
    pageTransitionHistoryStack.pop();
    final String path = pageTransitionHistoryStack.lastElement();
    layout.show(cardPanel, path);
  }

  static public void push(String path) {
    singleton._push(path);
  }

  static public void pop() {
    singleton._pop();
  }

}