package jp.waseda.asagi.kobayashi.router;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import javax.swing.*;

import jp.waseda.asagi.kobayashi.utils.OriginalView;
import jp.waseda.asagi.kobayashi.views.CreateRoomView;
import jp.waseda.asagi.kobayashi.views.EnterRoomView;
import jp.waseda.asagi.kobayashi.views.HomeView;
import jp.waseda.asagi.kobayashi.views.InitialView;
import jp.waseda.asagi.kobayashi.views.LoginView;
import jp.waseda.asagi.kobayashi.views.RoomView;
import jp.waseda.asagi.kobayashi.views.SignupView;

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

  private void _push(String path, Map<String, String> param) {
    pageTransitionHistoryStack.push(path);
    System.out.println("route stack: " + pageTransitionHistoryStack);
    routes.get(path).onAppear(param);
    layout.show(cardPanel, path);
  }

  private void _pop() {
    final String currPath = pageTransitionHistoryStack.lastElement();
    routes.get(currPath).onDisapper();
    pageTransitionHistoryStack.pop();
    final String path = pageTransitionHistoryStack.lastElement();
    routes.get(path).onReturn();
    layout.show(cardPanel, path);
  }

  static public void push(String path, Map<String, String> param) {
    singleton._push(path, param);
  }

  static public void pop() {
    singleton._pop();
  }

}