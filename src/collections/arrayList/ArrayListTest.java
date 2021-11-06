package collections.arrayList;

import org.junit.Assert;
import org.junit.Test;

import java.sql.Array;
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
    public void removeElementThrowsUnsupported() {
        ArrayList<String> list = new ArrayList<>();
        Boolean __ = list.remove("a");
    }

    @Test(expected = UnsupportedOperationException.class)
    public void removeIndexThrowsUnsupported() {
        ArrayList<String> list = new ArrayList<>();
        String __ = list.remove(1);
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

    @Test(expected = UnsupportedOperationException.class)
    public void addAllFromIndexThrowsUnsupported() {
        ArrayList<Integer> list = new ArrayList<>();
        Boolean __ = list.addAll(0, new Vector<Integer>());
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
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.get(-1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void throwsOutOfBoundsForIndexEqualToSize() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.get(3);
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
        Assert.assertEquals(1, (int)list.get(0));
        Assert.assertEquals(2, (int)list.get(1));
        Assert.assertEquals(3, (int)list.get(2));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void setThrowsUnsupported() {
        ArrayList<Integer> list = new ArrayList<>();
        Integer __ = list.set(0, 0);
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
        Assert.assertEquals(0, list.indexOf(1));
        Assert.assertEquals(1, list.indexOf(2));
        Assert.assertEquals(2, list.indexOf(3));
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
}
