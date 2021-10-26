package collections.linkedList;

import java.util.ListIterator;
import java.util.NoSuchElementException;

public class LinkedListIterator<TValue> implements ListIterator<TValue> {

    private int index;
    private LinkedNode<TValue> next;
    private LinkedNode<TValue> prev;
    private LinkedNode<TValue> lastPrevRealized;
    private LinkedNode<TValue> lastNextRealized;

    public LinkedListIterator() { }

    public LinkedListIterator(LinkedNode<TValue> next) {
        this.next = next;
        this.prev = next.prev;
    }

    public LinkedListIterator(LinkedNode<TValue> next, int nextIndex) {
        this.next = next;
        prev = next.prev;
        index = nextIndex;
    }

    public boolean hasNext() {
        return next != null;
    }

    public TValue next() {
        if (next == null)
            throw new NoSuchElementException();
        index++;
        lastNextRealized = next;
        lastPrevRealized = null;
        prev = next;
        next = next.next;
        return lastNextRealized.value;
    }

    public boolean hasPrevious() {
        return prev != null;
    }

    public TValue previous() {
        if (prev == null)
            throw new NoSuchElementException();
        index--;
        lastPrevRealized = prev;
        lastNextRealized = null;
        next = prev;
        prev = prev.prev;
        return lastPrevRealized.value;
    }

    public int nextIndex() {
        return index;
    }

    public int previousIndex() {
        return index - 1;
    }

    public void remove() {
        if (lastNextRealized != null) {
            prev = prev.prev;
            index--;
        }
        else if (lastPrevRealized != null)
            next = next.next;
        else
            throw new IllegalStateException();
        if (next != null) next.prev = prev;
        if (prev != null) prev.next = next;
        lastPrevRealized = null;
        lastNextRealized = null;
    }

    public void set(TValue value) {
        if (lastNextRealized != null)
            lastNextRealized.value = value;
        else if (lastPrevRealized != null)
            lastPrevRealized.value = value;
        else
            throw new IllegalStateException();
    }

    public void add(TValue value) {
        index++;
        if (prev != null) {
            prev.addAfter(value);
            prev = prev.next;
        } else
            prev = new LinkedNode<>(value);

        if (next != null)
            next.prev = prev;
        prev.next = next;
        lastNextRealized = null;
        lastPrevRealized = null;
    }
}
