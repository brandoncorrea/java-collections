package collections.linkedList;

public class LinkedNode<TValue> {
    TValue value;
    LinkedNode<TValue> next;
    LinkedNode<TValue> prev;
    LinkedNode(TValue value) { this.value = value; }
    LinkedNode() { }

    public void addAfter(TValue value) {
        LinkedNode<TValue> node = new LinkedNode<>(value);
        if (next != null)
            next.prev = node;
        node.prev = this;
        node.next = next;
        next = node;
    }

    public void addBefore(TValue value) {
        LinkedNode<TValue> node = new LinkedNode<>(value);
        if (prev != null)
            prev.next = node;
        node.next = this;
        node.prev = prev;
        prev = node;
    }
}
