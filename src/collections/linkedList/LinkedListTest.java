package collections.linkedList;

import org.junit.Assert;
import org.junit.Test;

public class LinkedListTest {
    @Test
    public void newLinkedList() {
        LinkedList<Integer> list = new LinkedList();
        Assert.assertEquals(0, list.size());
        Assert.assertTrue(list.isEmpty());
    }

    @Test
    public void addsToList() {
        LinkedList list = new LinkedList();
        list.add(1);
        assert(list.elementAt(0)).equals(1);
        list.add(2);
        assert(list.elementAt(0)).equals(1);
        assert(list.elementAt(1)).equals(2);
        list.add(3);
        assert(list.elementAt(2)).equals(3);
        list.add("Not an int");
        assert(list.elementAt(3)).equals("Not an int");
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void throwsWhenRetrievingElementOutOfBounds() {
        LinkedList list = new LinkedList();
        list.elementAt(0);
    }

    @Test
    public void returnsElementAtIndex() {
        LinkedList list = new LinkedList();
        list.add(1);
        assert(list.elementAt(0)).equals(1);
        list.add(2);
        assert(list.elementAt(1)).equals(2);
    }

    @Test
    public void countsAllElements() {
        LinkedList list = new LinkedList();
        Assert.assertEquals(0, list.size());
        list.add(1);
        Assert.assertEquals(1, list.size());
        list.add(2);
        Assert.assertEquals(2, list.size());
    }

    @Test
    public void checksForEmptyList() {
        LinkedList list = new LinkedList();
        Assert.assertTrue(list.isEmpty());
        list.add(1);
        Assert.assertFalse(list.isEmpty());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void throwsWhenRemovingElementOutOfBounds_empty() {
        LinkedList list = new LinkedList();
        list.removeAt(0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void throwsWhenRemovingElementOutOfBounds_equalToCount() {
        LinkedList list = new LinkedList();
        list.add(1);
        list.add(2);
        list.removeAt(2);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void throwsWhenRemovingElementOutOfBounds_greaterThanCount() {
        LinkedList list = new LinkedList();
        list.add(1);
        list.add(2);
        list.removeAt(5);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void throwsWhenRemovingNegativeIndex() {
        LinkedList list = new LinkedList();
        list.add(1);
        list.add(2);
        list.removeAt(-1);
    }

    @Test
    public void removesItemsAtIndex() {
        LinkedList list = new LinkedList();
        list.add(1);
        assert(list.removeAt(0)).equals(1);
        Assert.assertTrue(list.isEmpty());
        list.add(1);
        list.add(2);
        assert(list.removeAt(1)).equals(2);
        assert(list.elementAt(0)).equals(1);
        Assert.assertEquals(1, list.size());
        list.add(2);
        list.add(3);
        assert(list.removeAt(2)).equals(3);
        assert(list.elementAt(0)).equals(1);
        assert(list.elementAt(1)).equals(2);
        Assert.assertEquals(2, list.size());
        list.add(3);
        list.add(4);
        assert(list.removeAt(2)).equals(3);
        assert(list.elementAt(0)).equals(1);
        assert(list.elementAt(1)).equals(2);
        assert(list.elementAt(2)).equals(4);
        Assert.assertEquals(3, list.size());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void popThrowsWhenEmpty() {
        LinkedList list = new LinkedList();
        list.pop();
    }

    @Test
    public void popReturnsFirstElement() {
        LinkedList list = new LinkedList();
        list.add(1);
        assert(list.pop()).equals(1);
        Assert.assertEquals(0, list.size());
        list.add(1);
        list.add(2);
        list.add(3);
        assert(list.pop()).equals(1);
        Assert.assertEquals(2, list.size());
        assert(list.pop()).equals(2);
        Assert.assertEquals(1, list.size());
        assert(list.pop()).equals(3);
        Assert.assertEquals(0, list.size());
    }

    @Test
    public void containsIsFalseForEmptyLists() {
        LinkedList list = new LinkedList();
        Assert.assertFalse(list.contains(1));
    }

    @Test
    public void containsIsFalseWhenItemIsNotInList() {
        LinkedList list = new LinkedList();
        list.add(1);
        list.add(2);
        list.add(3);
        Assert.assertFalse(list.contains(4));
    }

    @Test
    public void containsIsTrueForListOfSizeOne() {
        LinkedList list = new LinkedList();
        list.add(1);
        Assert.assertTrue(list.contains(1));
    }

    @Test
    public void containsIsTrueForAnyIndexInList() {
        LinkedList list = new LinkedList();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        Assert.assertTrue(list.contains(1));
        Assert.assertTrue(list.contains(2));
        Assert.assertTrue(list.contains(3));
        Assert.assertTrue(list.contains(4));
    }

    @Test
    public void containsIsFalseForNullValues() {
        LinkedList list = new LinkedList();
        Assert.assertFalse(list.contains(null));
    }

    @Test
    public void containsIsTrueWhenListContainsNull() {
        LinkedList list = new LinkedList();
        list.add(null);
        Assert.assertTrue(list.contains(null));
    }

    @Test
    public void toArrayWorksWithEmptyList() {
        LinkedList list = new LinkedList();
        Assert.assertArrayEquals(new Object[0], list.toArray());
    }

    @Test
    public void toArrayWorksWithSingleElementlist() {
        LinkedList list = new LinkedList();
        list.add(1);
        Object[] expected = {1};
        Assert.assertArrayEquals(expected, list.toArray());
    }

    @Test
    public void toArrayWorksWithManyItems() {
        LinkedList list = new LinkedList();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        Object[] expected = {1, 2, 3, 4};
        Assert.assertArrayEquals(expected, list.toArray());
    }

    @Test
    public void toArrayReturnsAnEmptyListWhenGivenAnEmptyArray() {
        LinkedList<Number> list = new LinkedList();
        Number[] expected = {};
        Assert.assertArrayEquals(expected, list.toArray(new Number[0]));
    }

    @Test
    public void toArrayReturnsNullValuesWhenLinkedListIsEmpty() {
        LinkedList list = new LinkedList();
        Object[] expected = {null, null, null};
        Assert.assertArrayEquals(expected, list.toArray(new Object[] {1, 2, 3}));
    }

    @Test
    public void toArrayOnlyTakesItemsForTheSizeOfTheArray() {
        LinkedList list = new LinkedList();
        for (int item : new int[] {1, 2, 3, 4})
            list.add(item);
        Object[] expected = {1, 2};
        Assert.assertArrayEquals(expected, list.toArray(new Object[2]));
    }
}
