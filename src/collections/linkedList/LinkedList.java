package collections.linkedList;

public class LinkedList<TValue> {
    private LinkedList<TValue> next;
    private TValue value;

    public void add(TValue value) {
        if (this.value == null)
            this.value = value;
        else {
            LinkedList<TValue> tail = this;
            while(tail.next != null)
                tail = tail.next;
            tail.next = new LinkedList();
            tail.next.value = value;
        }
    }

    public TValue elementAt(int index) {
        LinkedList<TValue> node = nodeAt(index);
        if (node.value == null)
            throw new IndexOutOfBoundsException();
        return node.value;
    }

    public int size() {
        if (value == null) return 0;
        LinkedList<TValue> cur = this;
        int total = 0;
        while(cur != null) {
            cur = cur.next;
            total++;
        }
        return total;
    }

    public boolean isEmpty() {
        return value == null;
    }

    public boolean contains(Object o) {
        for (LinkedList<TValue> cur = this;
             cur != null && cur.value != null;
             cur = cur.next)
            if (cur.value.equals(o))
                return true;
        return false;
    }

    public TValue removeAt(int index) {
        if (index == 0) return pop();
        LinkedList<TValue> prev = nodeAt(index - 1);
        if (prev.next == null || index < 0)
            throw new IndexOutOfBoundsException();
        TValue temp = prev.next.value;
        prev.next = prev.next.next;
        return temp;
    }

    public TValue pop() {
        if (value == null)
            throw new IndexOutOfBoundsException();
        TValue temp = value;
        this.value = null;
        if (next != null) {
            this.value = next.value;
            this.next = this.next.next;
        }
        return temp;
    }

    private LinkedList<TValue> nodeAt(int index) {
        LinkedList<TValue> node = this;
        while (index-- > 0 && node.next != null)
            node = node.next;
        return node;
    }
}
