package collections.linkedList;

public class LinkedList<TValue> {
    private LinkedNode<TValue> first;
    private int size = 0;

    public boolean add(TValue value) {
        if (first == null)
            first = new LinkedNode(value);
        else {
            LinkedNode<TValue> tail = first;
            while (tail.next != null) tail = tail.next;
            tail.next = new LinkedNode(value);
            tail.next.prev = tail;
        }
        size++;
        return true;
    }

    public boolean remove(TValue value) {
        for (LinkedNode<TValue> cur = first;
             cur != null;
             cur = cur.next)
            if (valuesEqual(cur.value, value))
                return removeNode(cur);
        return false;
    }

    private boolean removeNode(LinkedNode<TValue> node) {
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
        LinkedNode<TValue> node = nodeAt(index);
        removeNode(node);
        return node.value;
    }

    public TValue pop() {
        return removeAt(0);
    }

    private LinkedNode<TValue> nodeAt(int index) {
        if (index < 0)
            throw new IndexOutOfBoundsException();
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
