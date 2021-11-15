package collections.arrayList;

import java.util.ListIterator;

public class ArrayListIterator<T> implements ListIterator<T> {

    private int index = 0;
    private final ArrayList<T> list;
    private boolean previousCalled = false;
    private boolean nextCalled = false;

    public ArrayListIterator() {
        list = new ArrayList<>();
    }

    public ArrayListIterator(T[] values) {
        list = new ArrayList<>(values);
    }

    public ArrayListIterator(T[] values, int nextIndex) {
        list = new ArrayList<>(values);
        index = nextIndex;
    }

    public ArrayListIterator(ArrayList<T> list) { this.list = list; }

    public boolean hasNext() { return index < list.size(); }

    public T next() {
        previousCalled = false;
        nextCalled = true;
        return list.get(index++);
    }

    public boolean hasPrevious() { return index > 0; }

    public T previous() {
        nextCalled = false;
        previousCalled = true;
        return list.get(index-- - 1); }

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
        if (nextCalled)
            list.set(index - 1, t);
        else if (previousCalled)
            list.set(index, t);
        else
            throw new IllegalStateException();
    }

    public void add(T t) {
        list.add(index++, t);
    }
}
