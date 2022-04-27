package states;

import java.util.ArrayList;
import java.util.function.Consumer;

abstract class ReactiveState<T> {
    protected T value;
    private final ArrayList<Consumer<T>> onChanges;

    protected ReactiveState(T initValue) {
        value = initValue;
        onChanges = new ArrayList<Consumer<T>>();
    }

    public void listen(Consumer<T> onChange) {
        onChanges.add(onChange);
    }

    public void change(T newValue) {
        value = newValue;
        for (Consumer<T> onChange: onChanges) {
            onChange.accept(value);
        }
    }

    public T get() {
        return value;
    }
}
