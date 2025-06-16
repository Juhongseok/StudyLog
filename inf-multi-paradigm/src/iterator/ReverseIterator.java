package iterator;

public class ReverseIterator<T> implements Iterator<T> {
    private int index;
    private T[] array;

    public ReverseIterator(T[] array) {
        this.array = array;
        this.index = array.length;
    }

    @Override
    public IteratorResult<T> next() {
        return this.index == 0
                ? IteratorResult.returnResult()
                : IteratorResult.yieldResult(array[--index]);
    }

}
