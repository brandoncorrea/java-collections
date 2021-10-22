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
        return nodeAt(index).value;
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
        LinkedNode<TValue> prev = nodeAt(index - 1);
        if (prev.next == null || index < 0)
            throw new IndexOutOfBoundsException();
        TValue temp = prev.next.value;
        prev.next = prev.next.next;
        return temp;
    }

    public TValue pop() {
        LinkedNode<TValue> node = nodeAt(0);
        TValue value = node.value;
        first = node.next;
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
