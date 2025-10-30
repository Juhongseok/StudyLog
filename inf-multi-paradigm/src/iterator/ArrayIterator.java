package iterator;

public class ArrayIterator<T> implements Iterator<T> {
    private int index = 0;
    private T[] array;

    public ArrayIterator(T[] array) {
        this.array = array;
    }

    @Override
    public IteratorResult<T> next() {
        return this.index == array.length
                ? IteratorResult.returnResult()
                : IteratorResult.yieldResult(array[index++]);
    }

}
