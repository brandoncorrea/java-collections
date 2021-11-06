package collections.arrayList;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayIterator<T> implements Iterator<T> {

    private final T[] values;
    private int index;

    public ArrayIterator(T[] values) {
        this.values = values;
        index = 0;
    }

    public boolean hasNext() {
        return values.length > index;
    }

    public T next() {
        if (hasNext())
            return values[index++];
        throw new NoSuchElementException();
    }
}
