package collections.linkedList;

public class LinkedNode<TValue> {
    TValue value;
    LinkedNode<TValue> next;
    LinkedNode(TValue value) { this.value = value; }
    LinkedNode() { }
}
