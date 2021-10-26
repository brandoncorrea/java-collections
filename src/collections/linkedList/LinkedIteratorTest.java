package collections.linkedList;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class LinkedIteratorTest {

    @Test
    public void newIterator() {
        LinkedNode<String> first = new LinkedNode<>("a");
        Assert.assertTrue(new LinkedIterator<>(first).hasNext());
        Assert.assertFalse(new LinkedIterator<>().hasNext());
    }

    @Test
    public void retrievesNextValues() {
        LinkedNode<String> first = new LinkedNode<>("a");
        first.addAfter("b");
        first.next.addAfter("c");
        Iterator<String> iterator = new LinkedIterator<>(first);
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals("a", iterator.next());
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals("b", iterator.next());
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals("c", iterator.next());
        Assert.assertFalse(iterator.hasNext());
    }

    @Test(expected = NoSuchElementException.class)
    public void iteratorThrowsOnNextForEmptyList() {
        new LinkedIterator<>().next();
    }
}
