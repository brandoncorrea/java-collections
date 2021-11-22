package collections.linkedList;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class LinkedListIteratorTest {

    private <T> LinkedListIterator<T> createIterator(T... elements) {
        LinkedList<T> list = new LinkedList<T>();
        Collections.addAll(list, elements);
        return new LinkedListIterator<>(list);
    }

    @Test
    public void newLinkedListIterator() {
        LinkedList<String> list = new LinkedList<>();
        LinkedListIterator<String> iterator = new LinkedListIterator<>(list);
        Assert.assertFalse(iterator.hasPrevious());
        Assert.assertFalse(iterator.hasNext());
        list.add("a");
        iterator = new LinkedListIterator<>(list);
        Assert.assertFalse(iterator.hasPrevious());
        Assert.assertTrue(iterator.hasNext());

        list.add("b");
        list.add("c");
        iterator = new LinkedListIterator<>(list, 1);
        Assert.assertTrue(iterator.hasPrevious());
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals(0, iterator.previousIndex());
        Assert.assertEquals(1, iterator.nextIndex());
        Assert.assertEquals("a", iterator.previous());
        Assert.assertEquals("a", iterator.next());
    }

    @Test
    public void retrievesNextValues() {
        LinkedList<String> list = new LinkedList<>();
        list.addAll(Arrays.asList("a", "b", "c"));
        Iterator<String> iterator = new LinkedListIterator<>(list);
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

    @Test(expected = NoSuchElementException.class)
    public void iteratorThrowsOnPreviousForEmptyList() {
        new LinkedListIterator<>(new LinkedList<>()).previous();
    }

    @Test
    public void iteratorRetrievesPreviousElement() {
        LinkedList<String> list = new LinkedList<>();
        list.addAll(Arrays.asList("a", "b", "c"));
        ListIterator<String> iterator = new LinkedListIterator<>(list);
        iterator.next();
        iterator.next();
        iterator.next();
        Assert.assertTrue(iterator.hasPrevious());
        Assert.assertEquals("c", iterator.previous());
        Assert.assertTrue(iterator.hasPrevious());
        Assert.assertEquals("b", iterator.previous());
        Assert.assertTrue(iterator.hasPrevious());
        Assert.assertEquals("a", iterator.previous());
        Assert.assertFalse(iterator.hasPrevious());
    }

    @Test
    public void retrievesIndexOfNextElement() {
        LinkedList<String> list = new LinkedList<>();
        Assert.assertEquals(0, new LinkedListIterator<>(list).nextIndex());
        list.addAll(Arrays.asList("a", "b", "c"));
        ListIterator<String> iterator = list.listIterator();
        Assert.assertEquals(0, iterator.nextIndex());
        iterator.next();
        Assert.assertEquals(1, iterator.nextIndex());
        iterator.next();
        Assert.assertEquals(2, iterator.nextIndex());
        iterator.next();
        Assert.assertEquals(3, iterator.nextIndex());
    }

    @Test
    public void retrievesIndexOfPreviousElement() {
        LinkedList<String> list = new LinkedList<>();
        Assert.assertEquals(-1, new LinkedListIterator<>(list).previousIndex());
        list.addAll(Arrays.asList("a", "b", "c"));
        ListIterator<String> iterator = list.listIterator();
        iterator.next();
        iterator.next();
        Assert.assertEquals(1, iterator.previousIndex());
        iterator.previous();
        Assert.assertEquals(0, iterator.previousIndex());
        iterator.previous();
        Assert.assertEquals(-1, iterator.previousIndex());
    }

    @Test
    public void addsItemsToIterator() {
        LinkedList<String> list = new LinkedList<>();
        LinkedListIterator<String> iterator = new LinkedListIterator<>(list);
        iterator.add("a");
        Assert.assertTrue(iterator.hasPrevious());
        Assert.assertFalse(iterator.hasNext());
        Assert.assertEquals(1, iterator.nextIndex());
        Assert.assertEquals(0, iterator.previousIndex());
        iterator.add("b");
        Assert.assertFalse(iterator.hasNext());
        Assert.assertEquals(2, iterator.nextIndex());
        Assert.assertEquals(1, iterator.previousIndex());
        Assert.assertEquals("b", iterator.previous());
        Assert.assertEquals("a", iterator.previous());
        Assert.assertEquals(0, iterator.nextIndex());
        Assert.assertEquals(-1, iterator.previousIndex());
        iterator.next();
        iterator.add("d");
        Assert.assertEquals(1, iterator.previousIndex());
        Assert.assertEquals(2, iterator.nextIndex());
        Assert.assertEquals("b", iterator.next());
        iterator.previous();
        Assert.assertEquals("d", iterator.previous());
    }

    @Test(expected = IllegalStateException.class)
    public void removeThrowsWhenNextOrPreviousHaveNotBeenCalled() {
        LinkedListIterator<String> iterator = createIterator("a", "b", "c");
        iterator.remove();
    }

    @Test(expected = IllegalStateException.class)
    public void removeThrowsAfterRemoval() {
        LinkedList<String> list = new LinkedList<>();
        list.addAll(Arrays.asList("a", "b", "c"));
        ListIterator<String> iterator = new LinkedListIterator<>(list);
        iterator.next();
        iterator.remove();
        iterator.remove();
    }

    @Test(expected = IllegalStateException.class)
    public void removeThrowsWhenAddition() {
        LinkedListIterator<String> iterator = createIterator("a", "b", "c");
        iterator.next();
        iterator.add("d");
        iterator.remove();
    }

    @Test
    public void removesFirstElement() {
        LinkedList<String> list = new LinkedList<>();
        list.addAll(Arrays.asList("a", "b", "c"));
        ListIterator<String> iterator = list.listIterator();
        Assert.assertEquals("a", iterator.next());
        iterator.remove();
        Assert.assertFalse(iterator.hasPrevious());
        Assert.assertEquals(0, iterator.nextIndex());
        Assert.assertEquals(-1, iterator.previousIndex());
        Assert.assertEquals("b", iterator.next());
        Assert.assertEquals("c", iterator.next());
        Assert.assertEquals("c", iterator.previous());
        Assert.assertEquals("b", iterator.previous());
        Assert.assertEquals("b", iterator.next());
        Assert.assertEquals("c", iterator.next());
    }

    @Test
    public void removesSecondElement() {
        LinkedListIterator<String> iterator = createIterator("a", "b", "c");
        iterator.next();
        iterator.next();
        iterator.remove();
        Assert.assertEquals(1, iterator.nextIndex());
        Assert.assertEquals(0, iterator.previousIndex());
        Assert.assertEquals("a", iterator.previous());
        Assert.assertEquals("a", iterator.next());
        Assert.assertEquals("c", iterator.next());
        Assert.assertEquals("c", iterator.previous());
        Assert.assertEquals("a", iterator.previous());
    }

    @Test
    public void removesLastElement() {
        LinkedListIterator<String> iterator = createIterator("a", "b", "c");
        iterator.next();
        iterator.next();
        iterator.next();
        iterator.remove();
        Assert.assertEquals(2, iterator.nextIndex());
        Assert.assertEquals(1, iterator.previousIndex());
        Assert.assertEquals("b", iterator.previous());
        Assert.assertEquals("a", iterator.previous());
        Assert.assertEquals("a", iterator.next());
        Assert.assertEquals("b", iterator.next());
        Assert.assertEquals("b", iterator.previous());
        Assert.assertEquals("a", iterator.previous());
    }

    @Test
    public void removesAfterPrevious() {
        LinkedListIterator<String> iterator = createIterator("a", "b", "c");
        iterator.next();
        iterator.next();
        iterator.previous();
        iterator.remove();
        Assert.assertEquals(1, iterator.nextIndex());
        Assert.assertEquals(0, iterator.previousIndex());
    }

    @Test(expected = IllegalStateException.class)
    public void setThrowsAfterAddition() {
        LinkedListIterator<String> iterator = createIterator("a", "b", "c");
        iterator.next();
        iterator.add("d");
        iterator.set("e");
    }

    @Test(expected = IllegalStateException.class)
    public void setThrowsAfterRemoval() {
        LinkedListIterator<String> iterator = createIterator("a", "b", "c");
        iterator.next();
        iterator.remove();
        iterator.set("e");
    }

    @Test(expected = IllegalStateException.class)
    public void setThrowsWithoutPreviousOrNext() {
        LinkedListIterator<String> iterator = createIterator("a", "b", "c");
        iterator.set("d");
    }

    @Test
    public void setsItemsInIterator() {
        LinkedListIterator<String> iterator = createIterator("a", "b", "c");
        iterator.next();
        iterator.set("d");
        Assert.assertEquals("d", iterator.previous());
        iterator.next();
        iterator.next();
        iterator.set("e");
        Assert.assertEquals("e", iterator.previous());
        iterator.next();
        iterator.next();
        Assert.assertEquals("c", iterator.previous());
        iterator.set("f");
        Assert.assertEquals("f", iterator.next());
    }
}
