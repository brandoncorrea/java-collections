package collections.linkedList;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

public class LinkedList<TValue> implements List<TValue> {

    private LinkedNode<TValue> first;
    private int size = 0;

    public boolean add(TValue value) {
        if (first == null)
            first = new LinkedNode<>(value);
        else
            last().addAfter(value);
        size++;
        return true;
    }

    private LinkedNode<TValue> last() {
        return find(node -> node.next == null);
    }

    public void add(int index, TValue value) {
        if (index == 0 && size == 0)
            add(value);
        else {
            LinkedNode<TValue> node = nodeAt(index);
            node.addBefore(value);
            if (node.prev.prev == null)
                first = node.prev;
            size++;
        }
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

    public boolean addAll(Collection<? extends TValue> c) {
        Iterator<? extends TValue> iterator = c.iterator();
        if (!iterator.hasNext())
            return false;
        if (isEmpty())
            add(iterator.next());
        LinkedNode<TValue> cur = last();
        while (iterator.hasNext()) {
            cur.next = new LinkedNode<>(iterator.next());
            cur.next.prev = cur;
            cur = cur.next;
            size++;
        }
        return true;
    }

    public boolean addAll(int index, Collection<? extends TValue> c) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException();
        Iterator<? extends TValue> newItems = c.iterator();
        if (!newItems.hasNext())
            return false;
        if (index == 0) {
            add(0, newItems.next());
            index++;
        }

        ListIterator<TValue> currentCollection = listIterator(index);
        while(newItems.hasNext()) {
            currentCollection.add(newItems.next());
            size++;
        }
        return true;
    }

    public boolean removeAll(Collection<?> c) {
        int oldSize = size;
        removeWhere(c::contains);
        return size != oldSize;
    }

    public boolean retainAll(Collection<?> c) {
        int oldSize = size;
        removeWhere(value -> !c.contains(value));
        return size != oldSize;
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

    public ListIterator<TValue> listIterator(int index) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException();
        ListIterator<TValue> iterator = listIterator();
        while (index-- > 0) iterator.next();
        return iterator;
    }

    public List<TValue> subList(int fromIndex, int toIndex) {
        if (toIndex > size || fromIndex > toIndex)
            throw new IndexOutOfBoundsException();
        Iterator<TValue> iterator = listIterator(fromIndex);
        LinkedList<TValue> list = new LinkedList<>();
        while (toIndex-- > fromIndex) list.add(iterator.next());
        return list;
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

    public void bubbleSort(BiFunction<TValue, TValue, Integer> comparer) {
        AtomicBoolean hasSwapped = new AtomicBoolean(true);
        while (hasSwapped.getAndSet(false))
            forEachNode(node -> {
                if (node.next != null && comparer.apply(node.value, node.next.value) == 1) {
                    swapValues(node, node.next);
                    hasSwapped.set(true);
                }
            });
    }

    private void swapValues(LinkedNode<TValue> a, LinkedNode<TValue> b) {
        TValue temp = a.value;
        a.value = b.value;
        b.value = temp;
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

    private void removeWhile(Function<TValue, Boolean> predicate) {
        while (first != null && predicate.apply(first.value)) {
            first = first.next;
            size--;
        }
        if (first != null)
            first.prev = null;
    }

    private void removeWhere(Function<TValue, Boolean> predicate) {
        removeWhile(predicate);
        ListIterator<TValue> iterator = listIterator();
        while (iterator.hasNext())
            if (predicate.apply(iterator.next())) {
                iterator.remove();
                size--;
            }
    }

    private void forEachNode(Consumer<LinkedNode<TValue>> func) {
        for (LinkedNode<TValue> node = first; node != null; node = node.next)
            func.accept(node);
    }

    private LinkedNode<TValue> find(Function<LinkedNode<TValue>, Boolean> predicate) {
        for (LinkedNode<TValue> node = first; node != null; node = node.next)
            if (predicate.apply(node))
                return node;
        return null;
    }
}
