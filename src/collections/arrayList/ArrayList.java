package collections.arrayList;

import java.util.*;

public class ArrayList<T> implements Collection<T> {

    private T[] values;
    private int size = 0;

    public ArrayList() {
        values = allocateArray(1);
    }

    public ArrayList(T[] values) {
        if (values.length == 0)
            this.values = allocateArray(1);
        else {
            this.values = values;
            size = values.length;
        }
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean contains(Object value) {
        for (T item : values)
            if (Objects.equals(item, value))
                return true;
        return false;
    }

    public Iterator<T> iterator() {
        return new ArrayIterator<>((T[])toArray());
    }

    public Object[] toArray() {
        T[] subArray = allocateArray(size);
        copyTo(subArray);
        return subArray;
    }

    public <T1> T1[] toArray(T1[] a) {
        if (a.length <= size) return (T1[])toArray();
        copyTo(a);
        a[size] = null;
        return a;
    }

    public boolean add(T value) {
        if (values.length == size) grow();
        values[size++] = value;
        return true;
    }

    public void add(int index, T value) {
        throw new UnsupportedOperationException();
    }

    public T remove(int index) {
        throw new UnsupportedOperationException();
    }

    public boolean remove(Object value) {
        throw new UnsupportedOperationException();
    }

    public boolean containsAll(Collection<?> items) {
        for (Object item : items)
            if (!contains(item))
                return false;
        return true;
    }

    public boolean addAll(Collection<? extends T> c) {
        throw new UnsupportedOperationException();
    }

    public boolean addAll(int index, Collection<? extends T> c) {
        throw new UnsupportedOperationException();
    }

    public boolean removeAll(Collection<?> items) {
        throw new UnsupportedOperationException();
    }

    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException();
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
        throw new UnsupportedOperationException();
    }

    private void grow() {
        T[] oldList = values;
        values = allocateArray(values.length * 2);
        copyTo(oldList, values);
    }

    private T[] allocateArray(int length) {
        return (T[]) new Object[length];
    }

    private void copyTo(Object[] destination) {
        System.arraycopy(values, 0, destination, 0, size);
    }

    private void copyTo(T[] source, T[] destination) {
        System.arraycopy(source, 0, destination, 0, source.length);
    }

    public int indexOf(Object item) {
        for(int i = 0; i < size; i++)
            if (Objects.equals(values[i], item))
                return i;
        return -1;
    }

    public int lastIndexOf(Object item) {
        int lastIndex = -1;
        for (int i = 0; i < size; i++)
            if (Objects.equals(values[i], item))
                lastIndex = i;
        return lastIndex;
    }
}
