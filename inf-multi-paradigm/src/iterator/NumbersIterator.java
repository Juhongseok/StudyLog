package iterator;

public class NumbersIterator implements Iterator<Integer> {
    private int index = 0;
    private int[] array;

    public NumbersIterator(int[] array) {
        this.array = array;
    }

    @Override
    public IteratorResult<Integer> next() {
        return this.index == array.length
                ? IteratorResult.returnResult()
                : IteratorResult.yieldResult(array[index++]);
    }

}
