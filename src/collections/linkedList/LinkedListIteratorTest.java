package collections.linkedList;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class LinkedListIteratorTest {

    @Test
    public void newIterator() {
        LinkedNode<String> first = new LinkedNode<>("a");
        first.next = new LinkedNode<>("b");
        first.next.next = new LinkedNode<>("c");
        List<String> expected = new Vector<>();
        Assert.assertEquals(expected.iterator().hasNext(), new LinkedListIterator<>().hasNext());
        Collections.addAll(expected, "a", "b", "c");
        Iterator<String> listIterator = new LinkedListIterator<>(first);
        Iterator<String> vectorIterator = expected.iterator();
        Assert.assertEquals(listIterator.hasNext(), vectorIterator.hasNext());
        Assert.assertEquals(listIterator.next(), vectorIterator.next());
        Assert.assertEquals(listIterator.hasNext(), vectorIterator.hasNext());
        Assert.assertEquals(listIterator.next(), vectorIterator.next());
        Assert.assertEquals(listIterator.hasNext(), vectorIterator.hasNext());
        Assert.assertEquals(listIterator.next(), vectorIterator.next());
        Assert.assertEquals(listIterator.hasNext(), vectorIterator.hasNext());
    }

    @Test(expected = NoSuchElementException.class)
    public void iteratorThrowsOnNextForEmptyList() {
        new LinkedListIterator<>().next();
    }
}
