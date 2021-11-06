package collections.arrayList;

import java.util.Iterator;

public class ArrayList<T> implements Iterable<T> {

    private final T[] values;

    public ArrayList(T[] values) {
        this.values = values;
    }

    public Iterator<T> iterator() {
        return new ArrayIterator<>(values);
    }
}
