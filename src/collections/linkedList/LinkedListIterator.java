package collections.linkedList;

import java.util.ListIterator;
import java.util.NoSuchElementException;

public class LinkedListIterator<TValue> implements ListIterator<TValue> {

    private int index = 0;
    private boolean receivedNext = false;
    private boolean receivedPrevious = false;
    private final LinkedList<TValue> list;

    public LinkedListIterator(LinkedList<TValue> list) {
        this.list = list;
    }

    public LinkedListIterator(LinkedList<TValue> list, int index) {
        this.list = list;
        this.index = index;
    }

    public boolean hasNext() { return index < list.size(); }

    public TValue next() {
        if (!hasNext())
            throw new NoSuchElementException();
        receivedNext = true;
        receivedPrevious = false;
        return list.get(index++);
    }

    public boolean hasPrevious() { return index > 0; }

    public TValue previous() {
        if (!hasPrevious())
            throw new NoSuchElementException();
        receivedNext = false;
        receivedPrevious = true;
        return list.get(--index);
    }

    public int nextIndex() {
        return index;
    }

    public int previousIndex() {
        return index - 1;
    }

    public void remove() {
        if (receivedNext)
            list.remove(--index);
        else if (receivedPrevious)
            list.remove(index);
        else
            throw new IllegalStateException();
        receivedNext = false;
        receivedPrevious = false;
    }

    public void set(TValue value) {
        if (receivedNext)
            list.set(previousIndex(), value);
        else if (receivedPrevious)
            list.set(nextIndex(), value);
        else
            throw new IllegalStateException();
    }

    public void add(TValue value) {
        list.add(index++, value);
        receivedNext = false;
        receivedPrevious = false;
    }
}
