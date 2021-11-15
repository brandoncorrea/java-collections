package collections.arrayList;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayIterator<T> implements Iterator<T> {

    private final T[] values;
    private int index = 0;
    private final int size;

    public ArrayIterator(T[] values) {
        this.values = values;
        this.size = values.length;
    }

    public ArrayIterator(T[] values, int size) {
        this.values  = values;
        this.size = size;
    }

    public boolean hasNext() { return size > index; }

    public T next() {
        if (hasNext())
            return values[index++];
        throw new NoSuchElementException();
    }
}
