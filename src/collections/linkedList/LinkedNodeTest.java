package collections.linkedList;

import org.junit.Assert;
import org.junit.Test;

public class LinkedNodeTest {
    @Test
    public void newLinkedNode() {
        LinkedNode<String> node = new LinkedNode<>("a");
        Assert.assertEquals("a", node.value);
        Assert.assertNull(node.prev);
        Assert.assertNull(node.next);
        Assert.assertNull(new LinkedNode<String>().value);
    }

    @Test
    public void addsNewElementAfterTheCurrentElement() {
        LinkedNode<String> node = new LinkedNode<>("a");
        node.addAfter("b");
        Assert.assertEquals("b", node.next.value);
        Assert.assertEquals(node, node.next.prev);
        node.addAfter("c");
        Assert.assertEquals("c", node.next.value);
        Assert.assertEquals("b", node.next.next.value);
        Assert.assertEquals(node, node.next.prev);
        Assert.assertEquals(node.next, node.next.next.prev);
    }

    @Test
    public void addsNewElementsBeforeTheCurrentElement() {
        LinkedNode<String> node = new LinkedNode<>("a");
        node.addBefore("b");
        Assert.assertEquals("b", node.prev.value);
        Assert.assertEquals(node, node.prev.next);
        node.addBefore("c");
        Assert.assertEquals("c", node.prev.value);
        Assert.assertEquals("b", node.prev.prev.value);
        Assert.assertEquals(node, node.prev.next);
        Assert.assertEquals(node.prev, node.prev.prev.next);
    }
}
