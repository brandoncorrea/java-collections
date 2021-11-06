package collections.arrayList;

import org.junit.Assert;
import org.junit.Test;

import java.util.Iterator;
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
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals(1, (int)iterator.next());
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals(2, (int)iterator.next());
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals(3, (int)iterator.next());
        Assert.assertFalse(iterator.hasNext());
    }

    @Test
    public void addsItemsToEmptyCollection() {
        ArrayList<Integer> list = new ArrayList<>();
        Assert.assertTrue(list.isEmpty());
        Assert.assertTrue(list.add(1));
        Assert.assertFalse(list.isEmpty());
        Assert.assertTrue(list.add(2));
        Assert.assertFalse(list.isEmpty());
        Assert.assertTrue(list.add(3));
        Assert.assertFalse(list.isEmpty());
        Iterator<Integer> iterator = list.iterator();
        Assert.assertEquals(1, (int)iterator.next());
        Assert.assertEquals(2, (int)iterator.next());
        Assert.assertEquals(3, (int)iterator.next());
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
        list.add(1);
        list.add(2);
        list.add(3);
        list.clear();
        Assert.assertArrayEquals(new Integer[0], list.toArray());
        Assert.assertEquals(0, list.size());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void removeThrowsUnsupported() {
        ArrayList<Integer> list = new ArrayList<>();
        Boolean __ = list.remove(1);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void removeAllThrowsUnsupported() {
        ArrayList<Integer> list = new ArrayList<>();
        Boolean __ = list.removeAll(new Vector<Integer>());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void addAllThrowsUnsupported() {
        ArrayList<Integer> list = new ArrayList<>();
        Boolean __ = list.addAll(new Vector<>());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void retainAllThrowsUnsupported() {
        ArrayList<Integer> list = new ArrayList<>();
        Boolean __ = list.retainAll(new Vector<Integer>());
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
}
