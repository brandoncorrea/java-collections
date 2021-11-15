package collections.arrayList;

import java.util.*;
import java.util.function.Function;

public class ArrayList<T> implements List<T> {

    private T[] values;
    private int size = 0;

    public ArrayList() {
        values = (T[]) new Object[1];
    }

    public ArrayList(T[] values) {
        size = values.length;
        if (values.length == 0)
            this.values = (T[]) new Object[1];
        else
            this.values = values;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean contains(Object value) { return indexOf(value) >= 0; }

    public Iterator<T> iterator() {
        return new ArrayIterator<>(values, size);
    }

    public ListIterator<T> listIterator() {
        return listIterator(0);
    }

    public ListIterator<T> listIterator(int index) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException();
        return new ArrayListIterator<>(this, index);
    }

    public List<T> subList(int fromIndex, int toIndex) {
        if (fromIndex < 0 || toIndex > size || fromIndex > toIndex)
            throw new IndexOutOfBoundsException();
        return new ArrayList<>(Arrays.copyOfRange(values, fromIndex, toIndex));
    }

    public Object[] toArray() { return Arrays.copyOf(values, size); }

    public <T1> T1[] toArray(T1[] a) {
        if (a.length <= size) return (T1[])toArray();
        System.arraycopy(values, 0, a, 0, size);
        a[size] = null;
        return a;
    }

    public boolean add(T value) {
        if (values.length == size) grow();
        values[size++] = value;
        return true;
    }

    public void add(int index, T value) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException();
        shiftRight(index, 1);
        values[index] = value;
    }

    public T remove(int index) {
        T temp = get(index);
        shiftLeft(index);
        return temp;
    }

    public boolean remove(Object value) {
        int index = 0;
        while (index < size && !Objects.equals(value, values[index]))
            index++;
        if (index >= size) return false;
        shiftLeft(index);
        return true;
    }

    public boolean containsAll(Collection<?> items) {
        for (Object item : items)
            if (!contains(item))
                return false;
        return true;
    }

    public boolean addAll(Collection<? extends T> items) {
        return addAll(size, items);
    }

    public boolean addAll(int index, Collection<? extends T> items) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException();
        int originalSize = size;
        shiftRight(index, items.size());
        for (T value : items)
            values[index++] = value;
        return originalSize != size;
    }

    public boolean removeAll(Collection<?> items) {
        return removeWhere(items::contains);
    }

    public boolean retainAll(Collection<?> items) {
        return removeWhere(element -> !items.contains(element));
    }

    public void clear() {
        size = 0;
    }

    public T get(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException();
        return values[index];
    }

    public T set(int index, T element) {
        T value = get(index);
        values[index] = element;
        return value;
    }

    public int indexOf(Object item) {
        for(int i = 0; i < size; i++)
            if (Objects.equals(values[i], item))
                return i;
        return -1;
    }

    public int lastIndexOf(Object item) {
        for (int i = size - 1; i >= 0; i--)
            if (Objects.equals(values[i], item))
                return i;
        return -1;
    }

    private void grow() { values = Arrays.copyOf(values, values.length * 2); }

    private void shiftLeft(int index) {
        while (++index < size)
            values[index - 1] = values[index];
        size--;
    }

    private boolean removeWhere(Function<T, Boolean> predicate) {
        int oldSize = size;
        for (int i = 0; i < size; i++)
            if (predicate.apply(values[i]))
                remove(i--);
        return size != oldSize;
    }

    private void shiftRight(int fromIndex, int count) {
        while (size + count > values.length)
            grow();
        System.arraycopy(values, fromIndex, values, fromIndex + count, size - fromIndex);
        size += count;
    }
}
