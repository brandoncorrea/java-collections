package collections.linkedList;

public class LinkedList<TValue> {
    private LinkedNode<TValue> first;

    public void add(TValue value) {
        if (first == null)
            first = new LinkedNode(value);
        else {
            LinkedNode<TValue> tail = first;
            while (tail.next != null) tail = tail.next;
            tail.next = new LinkedNode(value);
        }
    }

    public TValue elementAt(int index) {
        LinkedNode<TValue> node = nodeAt(index);
        if (node == null)
            throw new IndexOutOfBoundsException();
        return node.value;
    }

    public int size() {
        LinkedNode<TValue> cur = first;
        int count = 0;
        for (; cur != null; cur = cur.next) count++;
        return count;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public boolean contains(Object o) {
        for (LinkedNode cur = first;
             cur != null;
             cur = cur.next)
            if (valuesEqual(cur.value, o))
                return true;
        return false;
    }

    public TValue removeAt(int index) {
        if (index == 0) return pop();
        LinkedNode<TValue> prev = first;
        while (--index > 0 && prev != null) prev = prev.next;
        if (prev == null || prev.next == null || index < 0)
            throw new IndexOutOfBoundsException();
        TValue temp = prev.next.value;
        prev.next = prev.next.next;
        return temp;
    }

    public TValue pop() {
        if (first == null)
            throw new IndexOutOfBoundsException();
        TValue value = first.value;
        first = first.next;
        return value;
    }

    private LinkedNode<TValue> nodeAt(int index) {
        LinkedNode<TValue> node = first;
        while (index-- > 0 && node != null)
            node = node.next;
        return node;
    }

    private boolean valuesEqual (Object a, Object b) {
        return a == null &&
                b == null ||
                a.equals(b);
    }
}
