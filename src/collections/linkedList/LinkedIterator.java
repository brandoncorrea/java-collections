package collections.linkedList;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedIterator<TValue> implements Iterator<TValue> {

    private LinkedNode<TValue> currentNode;

    public LinkedIterator() { }
    public LinkedIterator(LinkedNode<TValue> node) {
        currentNode = node;
    }

    public boolean hasNext() {
        return currentNode != null;
    }

    public TValue next() {
        if (currentNode == null)
            throw new NoSuchElementException();
        TValue value = currentNode.value;
        currentNode = currentNode.next;
        return value;
    }
}
