package collections.linkedList;

public class LinkedNode<TValue> {
    TValue value;
    LinkedNode<TValue> next;
    LinkedNode<TValue> prev;
    LinkedNode(TValue value) { this.value = value; }
    LinkedNode() { }
}
