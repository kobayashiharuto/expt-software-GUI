package jp.waseda.asagi.kobayashi.states;

import jp.waseda.asagi.kobayashi.entities.User;

public class UserState extends ReactiveState<User> {
    private UserState(User initValue) {
        super(initValue);
    }

    private static UserState singleton = new UserState(null);

    public static UserState getInstance() {
        return singleton;
    }
}
