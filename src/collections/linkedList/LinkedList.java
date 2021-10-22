package collections.linkedList;

public class LinkedList<TValue> {
    private LinkedNode<TValue> first;
    private int size = 0;

    public void add(TValue value) {
        if (first == null)
            first = new LinkedNode(value);
        else {
            LinkedNode<TValue> tail = first;
            while (tail.next != null) tail = tail.next;
            tail.next = new LinkedNode(value);
        }
        size++;
    }

    public TValue elementAt(int index) {
        return nodeAt(index).value;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean contains(Object o) {
        for (LinkedNode cur = first;
             cur != null;
             cur = cur.next)
            if (valuesEqual(cur.value, o))
                return true;
        return false;
    }

    public Object[] toArray() {
        Object[] list = new Object[size];
        LinkedNode cur = first;
        for (int index = 0; cur != null; cur = cur.next)
            list[index++] = cur.value;
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

    public TValue removeAt(int index) {
        if (index == 0) return pop();
        LinkedNode<TValue> prev = nodeAt(index - 1);
        if (prev.next == null || index < 0)
            throw new IndexOutOfBoundsException();
        TValue temp = prev.next.value;
        prev.next = prev.next.next;
        size--;
        return temp;
    }

    public TValue pop() {
        LinkedNode<TValue> node = nodeAt(0);
        TValue value = node.value;
        first = node.next;
        size--;
        return value;
    }

    private LinkedNode<TValue> nodeAt(int index) {
        LinkedNode<TValue> node = first;
        while (index-- > 0 && node != null) node = node.next;
        if (node == null)
            throw new IndexOutOfBoundsException();
        return node;
    }

    private boolean valuesEqual (Object a, Object b) {
        return a == null &&
                b == null ||
                a.equals(b);
    }
}
