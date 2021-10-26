package collections.linkedList;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.function.Function;

public class LinkedList<TValue> {
    private LinkedNode<TValue> first;
    private int size = 0;

    public boolean add(TValue value) {
        if (first == null)
            first = new LinkedNode<>(value);
        else {
            LinkedNode<TValue> tail = find(node -> node.next == null);
            tail.next = new LinkedNode<>(value);
            tail.next.prev = tail;
        }
        size++;
        return true;
    }

    public boolean add(int index, TValue value) {
        LinkedNode<TValue> node = nodeAt(index);
        LinkedNode<TValue> newNode = new LinkedNode<>(value);
        newNode.next = node;
        newNode.prev = node.prev;
        if (node.prev == null)
            first = newNode;
        else
            node.prev.next = newNode;
        node.prev = newNode;
        size++;
        return true;
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

    public Object[] toArray() {
        Object[] list = new Object[size];
        AtomicInteger index = new AtomicInteger(0);
        forEach(node -> list[index.getAndIncrement()] = node.value);
        return list;
    }

    public TValue[] toArray(TValue[] a) {
        int index = 0;
        for (LinkedNode<TValue> cur = first;
             cur != null && index < a.length;
             cur = cur.next)
            a[index++] = cur.value;
        while (index < a.length)
            a[index++] = null;
        return a;
    }

    public int indexOf(Object value) {
        AtomicInteger index = new AtomicInteger(-1);
        LinkedNode<TValue> found = find(node -> {
            index.getAndIncrement();
            return Objects.equals(node.value, value);
        });
        return found == null ? -1 : index.get();
    }

    public int lastIndexOf(Object value) {
        AtomicInteger index = new AtomicInteger(-1);
        AtomicInteger lastIndex = new AtomicInteger(-1);
        forEach(node -> {
            index.getAndIncrement();
            if (Objects.equals(node.value, value))
                lastIndex.set(index.get());
        });
        return lastIndex.get();
    }

    public int hashCode() {
        AtomicInteger hash = new AtomicInteger(1);
        forEach(node -> {
            int nodeHash = node == null || node.value == null ? 0 : node.value.hashCode();
            hash.set(31 * hash.get() + nodeHash);
        });
        return hash.get();
    }

    private boolean removeNode(LinkedNode<TValue> node) {
        if (node == null) return false;
        if (node.prev == null) {
            first = node.next;
            if (first != null)
                first.prev = null;
        }
        else
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

    private void forEach(Consumer<LinkedNode<TValue>> action) {
        for (LinkedNode<TValue> node = first; node != null; node = node.next)
            action.accept(node);
    }
}
