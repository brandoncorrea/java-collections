package collections.arrayList;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Vector;

public class ArrayListTest {

    @Test
    public void newArrayList() {
        ArrayList<Integer> list = new ArrayList<>(new Integer[0]);
        Iterator<Integer> iterator = list.iterator();
        Assert.assertFalse(iterator.hasNext());
        Assert.assertEquals(0, list.size());
        Assert.assertTrue(list.isEmpty());

        list = new ArrayList<>(new Integer[]{1, 2, 3});
        Assert.assertFalse(list.isEmpty());
        Assert.assertEquals(3, list.size());
        iterator = list.iterator();
        for (int i = 1; i <= 3; i++) {
            Assert.assertTrue(iterator.hasNext());
            Assert.assertEquals(i, (int)iterator.next());
        }
        Assert.assertFalse(iterator.hasNext());
    }

    @Test
    public void addsItemsToEmptyCollection() {
        ArrayList<Integer> list = new ArrayList<>();
        Assert.assertTrue(list.isEmpty());
        for(int value : new Integer[] {1, 2, 3}) {
            Assert.assertTrue(list.add(value));
            Assert.assertFalse(list.isEmpty());
        }
        Iterator<Integer> iterator = list.iterator();
        for (int i = 1; i <= 3; i++)
            Assert.assertEquals(i, (int)iterator.next());
        Assert.assertFalse(iterator.hasNext());
    }

    @Test
    public void toArrayReturnsOnlyElementsAddedToList() {
        ArrayList<Integer> list = new ArrayList<>();
        Assert.assertArrayEquals(new Integer[0], list.toArray());
        list.add(1);
        Assert.assertArrayEquals(new Integer[]{1}, list.toArray());
        list.add(2);
        Assert.assertArrayEquals(new Integer[]{1, 2}, list.toArray());
        list.add(3);
        Assert.assertArrayEquals(new Integer[]{1, 2, 3}, list.toArray());
    }

    @Test
    public void clearsListOfEveryElement() {
        ArrayList<Integer> list = new ArrayList<>();
        list.clear();
        Assert.assertArrayEquals(new Integer[0], list.toArray());
        Assert.assertEquals(0, list.size());
        list.add(1);
        list.clear();
        Assert.assertArrayEquals(new Integer[0], list.toArray());
        Assert.assertEquals(0, list.size());
        list.addAll(Arrays.asList(1, 2, 3));
        list.clear();
        Assert.assertArrayEquals(new Integer[0], list.toArray());
        Assert.assertEquals(0, list.size());
    }

    @Test
    public void removesElementsFromList() {
        ArrayList<String> list = new ArrayList<>();
        Assert.assertFalse(list.remove("a"));
        list.add("a");
        Assert.assertTrue(list.remove("a"));
        Assert.assertArrayEquals(new String[0], list.toArray());
        Assert.assertFalse(list.remove("a"));
        list.addAll(Arrays.asList("a", "b"));
        Assert.assertTrue(list.remove("b"));
        Assert.assertArrayEquals(new String[] {"a"}, list.toArray());
        list.addAll(Arrays.asList("b", "c"));
        Assert.assertTrue(list.remove("b"));
        Assert.assertArrayEquals(new String[] {"a", "c"}, list.toArray());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void throwsWhenRemovingIndexOfEmptyList() {
        ArrayList<String> list = new ArrayList<>();
        list.remove(0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void throwsWhenRemovingNegativeIndex() {
        ArrayList<String> list = new ArrayList<>();
        list.addAll(Arrays.asList("a", "b", "c"));
        list.remove(-1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void throwsWhenRemovingOutOfBoundsIndex() {
        ArrayList<String> list = new ArrayList<>();
        list.addAll(Arrays.asList("a", "b", "c"));
        list.remove(3);
    }

    @Test
    public void removesItemAtSpecifiedIndex() {
        ArrayList<String> list = new ArrayList<>();
        list.add("a");
        Assert.assertEquals("a", list.remove(0));
        Assert.assertArrayEquals(new String[0], list.toArray());
        list.addAll(Arrays.asList("a", "b"));
        Assert.assertEquals("a", list.remove(0));
        Assert.assertArrayEquals(new String[] {"b"}, list.toArray());
        list.clear();
        list.addAll(Arrays.asList("a", "b", "c"));
        Assert.assertEquals("b", list.remove(1));
        Assert.assertArrayEquals(new String[] {"a", "c"}, list.toArray());
    }

    @Test
    public void removesAllElementsInSpecifiedCollection() {
        ArrayList<Integer> list = new ArrayList<>();
        Vector<Integer> vector = new Vector<>();
        Assert.assertFalse(list.removeAll(vector));
        list.add(1);
        Assert.assertFalse(list.removeAll(vector));
        Assert.assertArrayEquals(new Integer[] {1}, list.toArray());
        vector.add(1);
        Assert.assertTrue(list.removeAll(vector));
        Assert.assertArrayEquals(new Integer[0], list.toArray());
        vector.add(2);
        list.addAll(Arrays.asList(1, 1, 2, 2, 3));
        Assert.assertTrue(list.removeAll(vector));
        Assert.assertArrayEquals(new Integer[] {3}, list.toArray());
    }

    @Test
    public void addsAllItemsInCollectionToArray() {
        ArrayList<Integer> list = new ArrayList<>();
        Vector<Integer> vector = new Vector<>();
        Assert.assertFalse(list.addAll(vector));
        vector.add(1);
        Assert.assertTrue(list.addAll(vector));
        Assert.assertArrayEquals(new Integer[] {1}, list.toArray());
        vector.addAll(Arrays.asList(1, 2, 3));
        Assert.assertTrue(list.addAll(vector));
        Assert.assertArrayEquals(new Integer[] {1, 1, 1, 2, 3}, list.toArray());
    }

    @Test
    public void retainsAllElementsInCollection() {
        ArrayList<Integer> list = new ArrayList<>();
        Vector<Integer> vector = new Vector<>();
        Assert.assertFalse(list.retainAll(vector));
        list.add(1);
        Assert.assertTrue(list.retainAll(vector));
        Assert.assertArrayEquals(new Integer[0], list.toArray());
        list.addAll(Arrays.asList(1, 2, 3));
        vector.addAll(Arrays.asList(1, 2, 3));
        Assert.assertFalse(list.retainAll(vector));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void addAllThrowsOutOfBoundsForEmptyList() {
        ArrayList<Integer> list = new ArrayList<>();
        list.addAll(0, new Vector<>());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void addAllThrowsForNegativeIndex() {
        ArrayList<Integer> list = new ArrayList<>();
        list.addAll(Arrays.asList(1, 2, 3));
        list.addAll(-1, new Vector<>());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void addAllThrowsForOutOfBoundsIndex() {
        ArrayList<Integer> list = new ArrayList<>();
        list.addAll(Arrays.asList(1, 2, 3));
        list.addAll(-1, new Vector<>());
    }

    @Test
    public void searchesListForValue() {
        ArrayList<Number> list = new ArrayList<>();
        Assert.assertFalse(list.contains(1));
        list.add(1);
        Assert.assertTrue(list.contains(1));
        list.add(2);
        Assert.assertTrue(list.contains(2));
    }

    @Test
    public void checksIfListContainsSublistOfItems() {
        ArrayList<Integer> list = new ArrayList<>();
        Vector<Integer> vector = new Vector<>();
        Assert.assertTrue(list.containsAll(vector));
        vector.add(1);
        Assert.assertFalse(list.containsAll(vector));
        list.add(1);
        Assert.assertTrue(list.containsAll(vector));
        list.add(2);
        Assert.assertTrue(list.containsAll(vector));
        vector.add(2);
        Assert.assertTrue(list.containsAll(vector));
        vector.add(3);
        list.add(3);
        Assert.assertTrue(list.containsAll(vector));
        vector.add(4);
        Assert.assertFalse(list.containsAll(vector));
        list.add(4);
        list.add(4);
        Assert.assertTrue(list.containsAll(vector));
    }

    @Test
    public void copiesElementsToArray() {
        ArrayList<Integer> list = new ArrayList<>();
        Assert.assertArrayEquals(new Number[0], list.toArray(new Number[0]));
        list.add(1);
        Assert.assertArrayEquals(new Number[] {1}, list.toArray(new Number[0]));
        Assert.assertArrayEquals(new Number[] {1}, list.toArray(new Number[] {2}));
        Assert.assertArrayEquals(new Number[] {1, null}, list.toArray(new Number[] {2, 3}));
        Assert.assertArrayEquals(new Number[] {1, null, 4, 5}, list.toArray(new Number[] {2, 3, 4, 5}));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void throwsOutOfBoundsWhenGettingNegativeIndex() {
        int __ = new ArrayList<>(new Integer[] { 1 }).get(-1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void throwsOutOfBoundsForIndexEqualToSize() {
        int __ = new ArrayList<>(new Integer[] { 1, 2, 3 }).get(3);
    }

    @Test
    public void getsElementsAtSpecifiedIndex() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        Assert.assertEquals(1, (int)list.get(0));
        list.add(2);
        Assert.assertEquals(1, (int)list.get(0));
        Assert.assertEquals(2, (int)list.get(1));
        list.add(3);
        for (int i = 0, e = 1; i < 3; i++, e++)
            Assert.assertEquals(i + 1, (int)list.get(i));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void setThrowsUnsupported() {
        int __ = new ArrayList<Integer>().set(0, 0);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void addAtIndexThrowsUnsupported() {
        new ArrayList<>().add(0, 0);
    }

    @Test
    public void findsFirstIndexOfElement() {
        ArrayList<Integer> list = new ArrayList<>();
        Assert.assertEquals(-1, list.indexOf(1));
        list.add(1);
        Assert.assertEquals(0, list.indexOf(1));
        Assert.assertEquals(-1, list.indexOf(2));
        list.add(2);
        Assert.assertEquals(0, list.indexOf(1));
        Assert.assertEquals(1, list.indexOf(2));
        Assert.assertEquals(-1, list.indexOf(3));
        list.add(3);
        for (int i = 0; i < 3; i++)
            Assert.assertEquals(i, list.indexOf(i + 1));
        Assert.assertEquals(-1, list.indexOf(4));
    }

    @Test
    public void findsLastIndexOfElement() {
        ArrayList<Integer> list = new ArrayList<>();
        Assert.assertEquals(-1, list.lastIndexOf(1));
        list.add(1);
        Assert.assertEquals(0, list.lastIndexOf(1));
        list.add(2);
        Assert.assertEquals(0, list.lastIndexOf(1));
        Assert.assertEquals(1, list.lastIndexOf(2));
        Assert.assertEquals(-1, list.lastIndexOf(3));
        list.add(1);
        Assert.assertEquals(2, list.lastIndexOf(1));
        list.add(2);
        Assert.assertEquals(3, list.lastIndexOf(2));
    }

    @Test
    public void createsListIterator() {
        ArrayList<Integer> list = new ArrayList<>(new Integer[] {1, 2, 3});
        ListIterator<Integer> iterator = list.listIterator();
        Assert.assertTrue(iterator.hasNext());
        Assert.assertFalse(iterator.hasPrevious());
        for (int i = 1; i <= 3; i++)
            Assert.assertEquals(i, (int)iterator.next());
        Assert.assertFalse(iterator.hasNext());
        Assert.assertTrue(iterator.hasPrevious());
    }

    @Test
    public void createsListIteratorAtIndex() {
        ArrayList<Integer> list = new ArrayList<>(new Integer[] {1, 2, 3});
        ListIterator<Integer> iterator = list.listIterator(1);
        Assert.assertTrue(iterator.hasNext());
        Assert.assertTrue(iterator.hasPrevious());
        Assert.assertEquals(1, (int)iterator.previous());
        Assert.assertFalse(iterator.hasPrevious());
        for (int i = 1; i <= 3; i++)
            Assert.assertEquals(i, (int)iterator.next());
        Assert.assertFalse(iterator.hasNext());
        Assert.assertTrue(iterator.hasPrevious());

        iterator = list.listIterator(0);
        Assert.assertTrue(iterator.hasNext());
        Assert.assertFalse(iterator.hasPrevious());

        iterator = list.listIterator(3);
        Assert.assertFalse(iterator.hasNext());
        Assert.assertTrue(iterator.hasPrevious());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void indexedListIteratorThrowsWhenNegative() {
        ArrayList<Integer> list = new ArrayList<>();
        list.addAll(Arrays.asList(1, 2, 3));
        list.listIterator(-1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void indexedListIteratorThrowsWhenOutOfBounds() {
        ArrayList<Integer> list = new ArrayList<>();
        list.addAll(Arrays.asList(1, 2, 3));
        list.listIterator(4);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void subListThrowsWhenFromIndexIsNegative() {
        new ArrayList<>(new Integer[]{1, 2, 3}).subList(-1, 1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void subListThrowsWhenToIndexIsGreaterThanSize() {
        new ArrayList<>(new Integer[]{1, 2, 3}).subList(0, 4);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void subListThrowsWhenIndicesCross() {
        new ArrayList<>(new Integer[]{1, 2, 3}).subList(1, 0);
    }

    @Test
    public void subListCreatesNewList() {
        ArrayList<Integer> list = new ArrayList<>();
        list.addAll(Arrays.asList(1, 2, 3));
        Assert.assertArrayEquals(new Integer[] {1, 2, 3}, list.subList(0, 3).toArray());
        Assert.assertArrayEquals(new Integer[] {1, 2}, list.subList(0, 2).toArray());
        Assert.assertArrayEquals(new Integer[] {2, 3}, list.subList(1, 3).toArray());
        Assert.assertArrayEquals(new Integer[0], list.subList(1, 1).toArray());
    }
}
