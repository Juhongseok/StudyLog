import iterator.ArrayIterator;
import iterator.Iterator;
import iterator.IteratorResult;
import iterator.ReverseIterator;

import java.util.function.Function;

public class Main {

    public static void main(String[] args) {
        var numbersIterator = new ArrayIterator<>(new Integer[]{10, 20, 30});

        System.out.println(numbersIterator.next());
        System.out.println(numbersIterator.next());
        System.out.println(numbersIterator.next());
        System.out.println(numbersIterator.next());

        var stringsIterator = new ArrayIterator<>(new String[]{"A", "B", "C"});

        System.out.println(stringsIterator.next());
        System.out.println(stringsIterator.next());
        System.out.println(stringsIterator.next());
        System.out.println(stringsIterator.next());

        var iter2 = natural();
        System.out.println(iter2.next());
        System.out.println(iter2.next());
        System.out.println(iter2.next());

        var iter3 = new ReverseIterator<>(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8});
        var mapped = map(a -> a * 10, iter3);
        System.out.println(mapped.next().getValue());
        System.out.println(mapped.next().getValue());
    }

    public static Iterator<Integer> natural() {
        return new Iterator<>() {
            private int current = 0;

            @Override
            public IteratorResult<Integer> next() {
                return IteratorResult.yieldResult(current++);
            }
        };
    }

    public static <T, R> Iterator<R> map(Function<T, R> f, Iterator<T> iterator) {
        return () -> {
            var result = iterator.next();
            return result.isDone()
                    ? IteratorResult.returnResult()
                    : IteratorResult.yieldResult(f.apply(result.getValue()));
        };
    }

}
