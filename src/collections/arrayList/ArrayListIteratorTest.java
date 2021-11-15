package collections.arrayList;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.ListIterator;

public class ArrayListIteratorTest {

    @Test
    public void newArrayListIterator() {
        ListIterator<Integer> iterator = new ArrayListIterator<>();
        Assert.assertFalse(iterator.hasNext());
        Assert.assertFalse(iterator.hasPrevious());
        Assert.assertEquals(0, iterator.nextIndex());
        Assert.assertEquals(-1, iterator.previousIndex());

        ArrayList<Integer> list = new ArrayList<>();
        list.addAll(Arrays.asList(1, 2, 3));
        iterator = new ArrayListIterator<>(list);
        Assert.assertTrue(iterator.hasNext());
        Assert.assertFalse(iterator.hasPrevious());

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

    @Test(expected = IllegalStateException.class)
    public void setThrowsIfNextOrPreviousHasNotBeenCalled() {
        ListIterator<Integer> iterator = new ArrayListIterator<>(new Integer[]{1, 2, 3});
        iterator.set(1);
    }

    @Test(expected = IllegalStateException.class)
    public void setThrowsIfAddWasCalled() {
        ListIterator<Integer> iterator = new ArrayListIterator<>(new Integer[]{1, 2, 3});
        iterator.next();
        iterator.add(4);
        iterator.set(5);
    }

    @Test(expected = IllegalStateException.class)
    public void setThrowsIfRemoveWasCalled() {
        ListIterator<Integer> iterator = new ArrayListIterator<>(new Integer[]{1, 2, 3});
        iterator.next();
        iterator.remove();
        iterator.set(5);
    }

    @Test
    public void setsLastItemReturnedByNextOrPrevious() {
        ListIterator<Integer> iterator = new ArrayListIterator<>(new Integer[]{1, 2, 3});
        iterator.next();
        iterator.set(4);
        Assert.assertEquals(4, (int)iterator.previous());
        Assert.assertEquals(4, (int)iterator.next());
        Assert.assertEquals(2, (int)iterator.next());
        Assert.assertEquals(3, (int)iterator.next());
        iterator.set(5);
        Assert.assertEquals(5, (int)iterator.previous());
        Assert.assertEquals(2, (int)iterator.previous());
        Assert.assertEquals(4, (int)iterator.previous());
        iterator.set(6);
        Assert.assertEquals(6, (int)iterator.next());
        Assert.assertEquals(2, (int)iterator.next());
        Assert.assertEquals(5, (int)iterator.next());
    }

    @Test(expected = IllegalStateException.class)
    public void removeThrowsIfNextOrPreviousNotCalled() {
        ListIterator<Integer> iterator = new ArrayListIterator<>(new Integer[]{1, 2, 3});
        iterator.remove();
    }

    @Test(expected = IllegalStateException.class)
    public void removeThrowsIfAddHasBeenCalled() {
        ListIterator<Integer> iterator = new ArrayListIterator<>(new Integer[] {1, 2, 3});
        iterator.next();
        iterator.add(4);
        iterator.remove();
    }

    @Test
    public void removesLastElementReturned() {
        ListIterator<Integer> iterator = new ArrayListIterator<>(new Integer[] {1, 2, 3});
        iterator.next();
        iterator.remove();
        Assert.assertFalse(iterator.hasPrevious());
        Assert.assertEquals(2, (int)iterator.next());
        Assert.assertEquals(3, (int)iterator.next());
        Assert.assertFalse(iterator.hasNext());
        Assert.assertEquals(3, (int)iterator.previous());
        iterator.remove();
        Assert.assertFalse(iterator.hasNext());
        Assert.assertEquals(2, (int)iterator.previous());
        Assert.assertFalse(iterator.hasPrevious());
    }

    @Test
    public void addsElementsBeforeNext() {
        ListIterator<Integer> iterator = new ArrayListIterator<>();
        iterator.add(1);
        Assert.assertFalse(iterator.hasNext());
        Assert.assertEquals(1, (int)iterator.previous());
        iterator.add(2);
        Assert.assertTrue(iterator.hasNext());
        Assert.assertTrue(iterator.hasPrevious());
        Assert.assertEquals(2, (int)iterator.previous());
        Assert.assertFalse(iterator.hasPrevious());
        Assert.assertEquals(2, (int)iterator.next());
        Assert.assertEquals(1, (int)iterator.next());
        Assert.assertFalse(iterator.hasNext());
    }
}
