package collections.arrayList;

import java.util.*;

public class ArrayList<T> implements List<T> {

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

    public ListIterator<T> listIterator() {
        return listIterator(0);
    }

    public ListIterator<T> listIterator(int index) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException();
        return new ArrayListIterator<>((T[])toArray(), index);
    }

    public List<T> subList(int fromIndex, int toIndex) {
        if (fromIndex < 0 || toIndex > size || fromIndex > toIndex)
            throw new IndexOutOfBoundsException();
        List<T> list = new ArrayList<>();
        while (fromIndex < toIndex)
            list.add(values[fromIndex++]);
        return list;
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

    private void shiftLeft(int index) {
        while (++index < size)
            values[index - 1] = values[index];
        size--;
    }

    private void shiftRight(int fromIndex, int count) {
        while (size + count > values.length)
            grow();
        System.arraycopy(values, fromIndex, values, fromIndex + count, size - fromIndex);
        size += count;
    }

    public boolean containsAll(Collection<?> items) {
        for (Object item : items)
            if (!contains(item))
                return false;
        return true;
    }

    public boolean addAll(Collection<? extends T> c) {
        int originalSize = size;
        for (T item : c) add(item);
        return size != originalSize;
    }

    public boolean addAll(int index, Collection<? extends T> c) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException();
        int oldSize = size;
        shiftRight(index, c.size());
        for (T value : c)
            values[index++] = value;
        return oldSize != size;
    }

    public boolean removeAll(Collection<?> items) {
        int originalSize = size;
        for (Object item : items)
            for (int i = 0; i < size; i++)
                if (Objects.equals(values[i], item))
                    remove(i--);
        return size != originalSize;
    }

    public boolean retainAll(Collection<?> c) {
        int originalSize = size;
        for (int i = 0; i < size; i++)
            if (!c.contains(values[i]))
                remove(i--);
        return size != originalSize;
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
