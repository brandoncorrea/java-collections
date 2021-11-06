package collections.arrayList;

import org.junit.Assert;
import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayIteratorTest {

    @Test
    public void newArrayIterator() {
        Iterator<Integer> iterator = new ArrayIterator<>(new Integer[0]);
        Assert.assertFalse(iterator.hasNext());
    }

    @Test(expected = NoSuchElementException.class)
    public void throwsOnNextForEmptyList() {
        Iterator<Integer> iterator = new ArrayIterator<>(new Integer[0]);
        iterator.next();
    }

    @Test
    public void newIteratorOfOneElement() {
        Iterator<Integer> iterator = new ArrayIterator<>(new Integer[] {1});
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals(1, (int)iterator.next());
    }

    @Test(expected = NoSuchElementException.class)
    public void throwsOnNextAtEndOfList() {
        Iterator<Integer> iterator = new ArrayIterator<>(new Integer[] {1});
        iterator.next();
        Assert.assertFalse(iterator.hasNext());
        iterator.next();
    }

    @Test
    public void newIteratorOfThreeElements() {
        Iterator<Integer> iterator = new ArrayIterator<>(new Integer[] {1, 2, 3});
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals(1, (int)iterator.next());
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals(2, (int)iterator.next());
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals(3, (int)iterator.next());
        Assert.assertFalse(iterator.hasNext());
    }
}
