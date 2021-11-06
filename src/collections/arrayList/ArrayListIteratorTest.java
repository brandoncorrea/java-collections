package collections.arrayList;

import org.junit.Assert;
import org.junit.Test;

import java.util.ListIterator;

public class ArrayListIteratorTest {

    @Test
    public void newArrayListIterator() {
        ListIterator<Integer> iterator = new ArrayListIterator<>();
        Assert.assertFalse(iterator.hasNext());
        Assert.assertFalse(iterator.hasPrevious());
        Assert.assertEquals(0, iterator.nextIndex());
        Assert.assertEquals(-1, iterator.previousIndex());

        iterator = new ArrayListIterator<>(new Integer[] {1, 2, 3});
        Assert.assertTrue(iterator.hasNext());
        Assert.assertFalse(iterator.hasPrevious());
        Assert.assertEquals(0, iterator.nextIndex());
        Assert.assertEquals(-1, iterator.previousIndex());

        iterator = new ArrayListIterator<>(new Integer[] {1, 2, 3}, 1);
        Assert.assertTrue(iterator.hasNext());
        Assert.assertTrue(iterator.hasPrevious());
        Assert.assertEquals(1, iterator.nextIndex());
        Assert.assertEquals(0, iterator.previousIndex());
    }

    @Test
    public void walksUpAndDownArray() {
        ListIterator<Number> iterator = new ArrayListIterator<>(new Integer[] {1, 2, 3});
        Assert.assertTrue(iterator.hasNext());
        Assert.assertFalse(iterator.hasPrevious());
        Assert.assertEquals(0, iterator.nextIndex());
        Assert.assertEquals(-1, iterator.previousIndex());

        Assert.assertEquals(1, (int)iterator.next());
        Assert.assertTrue(iterator.hasNext());
        Assert.assertTrue(iterator.hasPrevious());
        Assert.assertEquals(0, iterator.previousIndex());
        Assert.assertEquals(1, iterator.nextIndex());

        Assert.assertEquals(2, (int)iterator.next());
        Assert.assertTrue(iterator.hasNext());
        Assert.assertTrue(iterator.hasPrevious());
        Assert.assertEquals(1, iterator.previousIndex());
        Assert.assertEquals(2, iterator.nextIndex());

        Assert.assertEquals(3, (int)iterator.next());
        Assert.assertFalse(iterator.hasNext());
        Assert.assertTrue(iterator.hasPrevious());
        Assert.assertEquals(2, iterator.previousIndex());
        Assert.assertEquals(3, iterator.nextIndex());

        Assert.assertEquals(3, (int)iterator.previous());
        Assert.assertTrue(iterator.hasNext());
        Assert.assertTrue(iterator.hasPrevious());
        Assert.assertEquals(1, iterator.previousIndex());
        Assert.assertEquals(2, iterator.nextIndex());

        Assert.assertEquals(2, (int)iterator.previous());
        Assert.assertTrue(iterator.hasNext());
        Assert.assertTrue(iterator.hasPrevious());
        Assert.assertEquals(0, iterator.previousIndex());
        Assert.assertEquals(1, iterator.nextIndex());

        Assert.assertEquals(1, (int)iterator.previous());
        Assert.assertTrue(iterator.hasNext());
        Assert.assertFalse(iterator.hasPrevious());
        Assert.assertEquals(-1, iterator.previousIndex());
        Assert.assertEquals(0, iterator.nextIndex());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void setThrowsUnsupported() {
        ListIterator<Integer> iterator = new ArrayListIterator<>();
        iterator.set(1);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void removeThrowsUnsupported() {
        ListIterator<Integer> iterator = new ArrayListIterator<>();
        iterator.remove();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void addThrowsUnsupported() {
        ListIterator<Integer> iterator = new ArrayListIterator<>();
        iterator.add(1);
    }
}
