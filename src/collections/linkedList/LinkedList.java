package collections.linkedList;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

public class LinkedList<TValue> implements Iterable<TValue> {

    private LinkedNode<TValue> first;
    private int size = 0;

    public boolean add(TValue value) {
        if (first == null)
            first = new LinkedNode<>(value);
        else
            find(node -> node.next == null).addAfter(value);
        size++;
        return true;
    }

    public void add(int index, TValue value) {
        LinkedNode<TValue> node = nodeAt(index);
        node.addBefore(value);
        if (node.prev.prev == null)
            first = node.prev;
        size++;
    }

    public TValue set(int index, TValue value) {
        LinkedNode<TValue> node = nodeAt(index);
        TValue prev = node.value;
        node.value = value;
        return prev;
    }

    public boolean remove(Object value) {
        return removeNode(findNodeWithValue(value));
    }

    public TValue remove(int index) {
        LinkedNode<TValue> node = nodeAt(index);
        removeNode(node);
        return node.value;
    }

    public void clear() {
        first = null;
        size = 0;
    }

    public TValue pop() { return remove(0); }
    public TValue get(int index) { return nodeAt(index).value; }
    public int size() { return size; }
    public boolean isEmpty() { return size == 0; }

    public boolean contains(Object value) {
        return findNodeWithValue(value) != null;
    }

    public boolean containsAll(Collection<?> items) {
        for (Object el : items)
            if (!contains(el))
                return false;
        return true;
    }

    public Iterator<TValue> iterator() {
        return new LinkedIterator<>(first);
    }

    public Object[] toArray() {
        Object[] list = new Object[size];
        int index = 0;
        for (TValue el : this) list[index++] = el;
        return list;
    }

    public <T> T[] toArray(T[] source) {
        if (source.length <= size)
            return (T[])toArray();
        int index = 0;
        for (TValue el : this) source[index++] = (T)el;
        source[index] = null;
        return source;
    }

    public int indexOf(Object value) {
        int index = 0;
        for (TValue el : this) {
            if (Objects.equals(el, value))
                return index;
            index++;
        }
        return -1;
    }

    public int lastIndexOf(Object value) {
        int index = -1;
        int lastIndex = -1;
        for (TValue el : this) {
            index++;
            if (Objects.equals(el, value))
                lastIndex = index;
        }
        return lastIndex;
    }

    public ListIterator<TValue> listIterator() {
        return new LinkedListIterator<>(first);
    }

    public int hashCode() {
        int hash = 1;
        for (TValue el : this)
            hash = 31 * hash + Objects.hashCode(el);
        return hash;
    }

    public boolean equals(Object list) {
        if (!(list instanceof List) || ((List<?>) list).size() != size)
            return false;
        Iterator<TValue> iterator = iterator();
        for (Object value : (List<?>)list)
            if (!Objects.equals(value, iterator.next()))
                return false;
        return true;
    }

    private boolean removeNode(LinkedNode<TValue> node) {
        if (node == null) return false;
        if (node.prev == null) {
            first = node.next;
            if (first != null)
                first.prev = null;
        } else
            node.prev.next = node.next;
        size--;
        return true;
    }

    private LinkedNode<TValue> nodeAt(int index) {
        AtomicInteger atomicIndex = new AtomicInteger(index);
        LinkedNode<TValue> node = find(m -> atomicIndex.getAndDecrement() <= 0);
        if (node == null || index < 0)
            throw new IndexOutOfBoundsException();
        return node;
    }

    private LinkedNode<TValue> findNodeWithValue(Object value) {
        return find(node -> Objects.equals(node.value, value));
    }

    private LinkedNode<TValue> find(Function<LinkedNode<TValue>, Boolean> predicate) {
        for (LinkedNode<TValue> node = first; node != null; node = node.next)
            if (predicate.apply(node))
                return node;
        return null;
    }
}
