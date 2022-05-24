package jp.waseda.asagi.kobayashi.repositories;

import java.io.IOException;
import java.util.function.Consumer;

import jp.waseda.asagi.kobayashi.client.ServerClient;
import jp.waseda.asagi.kobayashi.entities.User;
import jp.waseda.asagi.kobayashi.exceptions.NotEnougnPointException;
import jp.waseda.asagi.kobayashi.exceptions.UnknownException;
import jp.waseda.asagi.kobayashi.utils.OriginalResult;
import jp.waseda.asagi.kobayashi.utils.RequestParser;
import jp.waseda.asagi.kobayashi.utils.ResponceParser;

public class TipPostRepository extends Thread {
  private final User user;
  private final String roomID;
  private final int amount;
  private final Consumer<OriginalResult<Integer>> callback;

  public TipPostRepository(User user, String roomID, int amount, Consumer<OriginalResult<Integer>> callback) {
    this.user = user;
    this.roomID = roomID;
    this.amount = amount;
    this.callback = callback;
  }

  @Override
  public void run() {
    try {
      final int balance = sendTip(user, amount, roomID);
      final OriginalResult<Integer> result = new OriginalResult<Integer>(balance);
      callback.accept(result);
    } catch (NotEnougnPointException e) {
      final OriginalResult<Integer> result = new OriginalResult<Integer>(new NotEnougnPointException());
      callback.accept(result);
    } catch (IOException e) {
      final OriginalResult<Integer> result = new OriginalResult<Integer>(new UnknownException());
      callback.accept(result);
    } catch (UnknownException e) {
      final OriginalResult<Integer> result = new OriginalResult<Integer>(new UnknownException());
      callback.accept(result);
    }
  }

  static private Integer sendTip(User user, int amout, String roomID)
      throws IOException, UnknownException, NotEnougnPointException {
    final String request = RequestParser.tip(user.id, user.name, roomID, amout);
    final String responce = ServerClient.getInstance().send(request);
    final Integer result = ResponceParser.postTip(responce);
    return result;
  }
}
