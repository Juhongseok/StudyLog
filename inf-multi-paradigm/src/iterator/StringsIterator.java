package iterator;

public class StringsIterator implements Iterator<String> {
    private int index = 0;
    private String[] array;

    public StringsIterator(String[] array) {
        this.array = array;
    }

    @Override
    public IteratorResult<String> next() {
        return this.index == array.length
                ? IteratorResult.returnResult()
                : IteratorResult.yieldResult(array[index++]);
    }

}
