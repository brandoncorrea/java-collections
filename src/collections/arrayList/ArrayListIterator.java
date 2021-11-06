package collections.arrayList;

import java.util.ListIterator;

public class ArrayListIterator<T> implements ListIterator<T> {

    private final T[] values;
    private int index = 0;

    public ArrayListIterator() {
        values = (T[])new Object[0];
    }

    public ArrayListIterator(T[] values) {
        this.values = values;
    }

    public boolean hasNext() {
        return index < values.length;
    }

    public T next() {
        return values[index++];
    }

    public boolean hasPrevious() {
        return index > 0;
    }

    public T previous() {
        return values[index-- - 1];
    }

    public int nextIndex() {
        return index;
    }

    public int previousIndex() {
        return index - 1;
    }

    public void remove() {
        throw new UnsupportedOperationException();
    }

    public void set(T t) {
        throw new UnsupportedOperationException();
    }

    public void add(T t) {
        throw new UnsupportedOperationException();
    }
}
