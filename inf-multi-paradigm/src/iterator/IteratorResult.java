package iterator;

public class IteratorResult<T> {

    private final T value;
    private final boolean done;

    private IteratorResult(T value, boolean done) {
        this.value = value;
        this.done = done;
    }

    public static <T> IteratorResult<T> yieldResult(T value) {
        return new IteratorResult<>(value, false);
    }

    public static <T> IteratorResult<T> returnResult() {
        return new IteratorResult<>(null, true);
    }

    public T getValue() {
        return value;
    }

    public boolean isDone() {
        return done;
    }

    @Override
    public String toString() {
        return "IteratorResult{" +
                "value=" + value +
                ", done=" + done +
                '}';
    }

}
