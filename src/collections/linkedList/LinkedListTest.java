package collections.linkedList;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class LinkedListTest {
    @Test
    public void newLinkedList() {
        LinkedList<Integer> list = new LinkedList<>();
        Assert.assertEquals(0, list.size());
        Assert.assertTrue(list.isEmpty());
    }

    @Test
    public void addsToList() {
        LinkedList<String> list = new LinkedList<>();
        list.add("a");
        assert(list.get(0)).equals("a");
        list.add("b");
        assert(list.get(0)).equals("a");
        assert(list.get(1)).equals("b");
        list.add("c");
        assert(list.get(2)).equals("c");
        list.add("Not an int");
        assert(list.get(3)).equals("Not an int");
    }

    @Test
    public void addResultsInTrue() {
        Assert.assertTrue(new LinkedList<>().add("A"));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void throwsWhenRetrievingElementOutOfBounds() {
        LinkedList<String> list = new LinkedList<>();
        list.get(0);
    }

    @Test
    public void returnsElementAtIndex() {
        LinkedList<String> list = new LinkedList<>();
        list.add("a");
        assert(list.get(0)).equals("a");
        list.add("b");
        assert(list.get(1)).equals("b");
    }

    @Test
    public void countsAllElements() {
        LinkedList<String> list = new LinkedList<>();
        Assert.assertEquals(0, list.size());
        list.add("a");
        Assert.assertEquals(1, list.size());
        list.add("b");
        Assert.assertEquals(2, list.size());
    }

    @Test
    public void checksForEmptyList() {
        LinkedList<String> list = new LinkedList<>();
        Assert.assertTrue(list.isEmpty());
        list.add("a");
        Assert.assertFalse(list.isEmpty());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void throwsWhenRemovingElementOutOfBounds_empty() {
        LinkedList<String> list = new LinkedList<>();
        list.remove(0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void throwsWhenRemovingElementOutOfBounds_equalToCount() {
        LinkedList<String> list = new LinkedList<>();
        list.add("a");
        list.add("b");
        list.remove(2);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void throwsWhenRemovingElementOutOfBounds_greaterThanCount() {
        LinkedList<String> list = new LinkedList<>();
        list.add("a");
        list.add("b");
        list.remove(5);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void throwsWhenRemovingNegativeIndex() {
        LinkedList<String> list = new LinkedList<>();
        list.add("a");
        list.add("b");
        list.remove(-1);
    }

    @Test
    public void removesItemsAtIndex() {
        LinkedList<String> list = new LinkedList<>();
        list.add("a");
        assert(list.remove(0)).equals("a");
        Assert.assertTrue(list.isEmpty());
        list.add("a");
        list.add("b");
        assert(list.remove(1)).equals("b");
        assert(list.get(0)).equals("a");
        Assert.assertEquals(1, list.size());
        list.add("b");
        list.add("c");
        assert(list.remove(2)).equals("c");
        assert(list.get(0)).equals("a");
        assert(list.get(1)).equals("b");
        Assert.assertEquals(2, list.size());
        list.add("c");
        list.add("d");
        assert(list.remove(2)).equals("c");
        assert(list.get(0)).equals("a");
        assert(list.get(1)).equals("b");
        assert(list.get(2)).equals("d");
        Assert.assertEquals(3, list.size());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void popThrowsWhenEmpty() {
        LinkedList<String> list = new LinkedList<>();
        list.pop();
    }

    @Test
    public void popReturnsFirstElement() {
        LinkedList<String> list = new LinkedList<>();
        list.add("a");
        assert(list.pop()).equals("a");
        Assert.assertEquals(0, list.size());
        list.add("a");
        list.add("b");
        list.add("c");
        assert(list.pop()).equals("a");
        Assert.assertEquals(2, list.size());
        assert(list.pop()).equals("b");
        Assert.assertEquals(1, list.size());
        assert(list.pop()).equals("c");
        Assert.assertEquals(0, list.size());
    }

    @Test
    public void containsIsFalseForEmptyLists() {
        LinkedList<String> list = new LinkedList<>();
        Assert.assertFalse(list.contains(1));
    }

    @Test
    public void containsIsFalseWhenItemIsNotInList() {
        LinkedList<String> list = new LinkedList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        Assert.assertFalse(list.contains("d"));
    }

    @Test
    public void containsIsTrueForListOfSizeOne() {
        LinkedList<String> list = new LinkedList<>();
        list.add("a");
        Assert.assertTrue(list.contains("a"));
    }

    @Test
    public void containsIsTrueForAnyIndexInList() {
        LinkedList<String> list = new LinkedList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        Assert.assertTrue(list.contains("a"));
        Assert.assertTrue(list.contains("b"));
        Assert.assertTrue(list.contains("c"));
        Assert.assertTrue(list.contains("d"));
    }

    @Test
    public void containsIsFalseForNullValues() {
        LinkedList<String> list = new LinkedList<>();
        Assert.assertFalse(list.contains(null));
    }

    @Test
    public void containsIsTrueWhenListContainsNull() {
        LinkedList<String> list = new LinkedList<>();
        list.add(null);
        Assert.assertTrue(list.contains(null));
    }

    @Test
    public void toArrayWorksWithEmptyList() {
        LinkedList<String> list = new LinkedList<>();
        Assert.assertArrayEquals(new Object[0], list.toArray());
    }

    @Test
    public void toArrayWorksWithSingleElementlist() {
        LinkedList<String> list = new LinkedList<>();
        list.add("a");
        Object[] expected = {"a"};
        Assert.assertArrayEquals(expected, list.toArray());
    }

    @Test
    public void toArrayWorksWithManyItems() {
        LinkedList<String> list = new LinkedList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        Object[] expected = {"a", "b", "c", "d"};
        Assert.assertArrayEquals(expected, list.toArray());
    }

    @Test
    public void toArrayReturnsAnEmptyListWhenGivenAnEmptyArray() {
        LinkedList<Number> list = new LinkedList<>();
        Number[] expected = {};
        Assert.assertArrayEquals(expected, list.toArray(new Number[0]));
    }

    @Test
    public void toArraySetsNextElementToNull() {
        LinkedList<String> list = new LinkedList<>();
        String[] testArray = {"a", "b", "c"};
        String[] expected = {null, "b", "c"};
        Assert.assertArrayEquals(expected, list.toArray(testArray));
        list.add("z");
        expected = new String[] {"z", null, "c"};
        Assert.assertArrayEquals(expected, list.toArray(testArray));
    }

    @Test
    public void toArrayResizesArrayIfTooSmall() {
        LinkedList<String> list = new LinkedList<>();
        String[] expected = {"a", "b", "c", "d"};
        for (String item : expected)
            list.add(item);
        Assert.assertArrayEquals(expected, list.toArray(new String[2]));
    }

    @Test
    public void toArrayCastsToTypeParameter() {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);
        Number[] expected = new Number[] {1, 2};
        Assert.assertArrayEquals(expected, list.toArray(new Number[0]));
    }

    @Test
    public void removingFromEmptyListResultsInFalse() {
        Number value = 1;
        Assert.assertFalse(new LinkedList<>().remove(value));
    }

    @Test
    public void removingItemNotInListResultsInFalse() {
        LinkedList<String> list = new LinkedList<>();
        list.add("a");
        Assert.assertFalse(list.remove("b"));
        Assert.assertArrayEquals(new String[] {"a"}, list.toArray());
    }

    @Test
    public void removesFirstItemInList() {
        LinkedList<String> list = new LinkedList<>();
        for (String i : new String[] {"a", "b", "c"})
            list.add(i);
        Assert.assertTrue(list.remove("a"));
        Assert.assertArrayEquals(new String[] {"b", "c"}, list.toArray());
    }

    @Test
    public void removesNullItemFromList() {
        LinkedList<String> list = new LinkedList<>();
        list.add(null);
        Assert.assertTrue(list.remove(null));
        Assert.assertArrayEquals(new Object[0], list.toArray());
    }

    @Test
    public void removesLastItemFromList() {
        LinkedList<String> list = new LinkedList<>();
        for (String i : new String[] {"a", "b", "c"})
            list.add(i);
        Assert.assertTrue(list.remove("c"));
        Assert.assertArrayEquals(new String[] {"a", "b"}, list.toArray());
    }

    @Test
    public void removesOnlyTheFirstOccurrenceOfAnItem() {
        LinkedList<String> list = new LinkedList<>();
        for (String i : new String[] {"b", "a", "b", "a", "b"})
            list.add(i);
        Assert.assertTrue(list.remove("a"));
        Assert.assertArrayEquals(new String[] {"b", "b", "a", "b"}, list.toArray());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void throwsWhenAttemptingToSetValuesInEmptyLists() {
        LinkedList<String> list = new LinkedList<>();
        list.set(0, "a");
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void throwsWhenAttemptingToSetValuesOutOfBounds() {
        LinkedList<String> list = new LinkedList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.set(3, "a");
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void throwsWhenAttemptingToSetNegativeIndices() {
        LinkedList<String> list = new LinkedList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.set(-1, "a");
    }

    @Test
    public void setsElementsToNewValues() {
        LinkedList<String> list = new LinkedList<>();
        list.add("a");
        Assert.assertEquals("a", list.set(0, "b"));
        Assert.assertEquals("b", list.get(0));
        list.add("c");
        list.add("d");
        Assert.assertEquals("c", list.set(1, "z"));
        Assert.assertEquals("z", list.get(1));
        Assert.assertEquals("d", list.set(2, "e"));
        Assert.assertEquals("e", list.get(2));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void insertThrowsOnEmptyList() {
        LinkedList<String> list = new LinkedList<>();
        list.add(0, "a");
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void insertThrowsOnNegativeIndex() {
        LinkedList<String> list = new LinkedList<>();
        list.add("a");
        list.add(-1, "a");
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void insertThrowsOnOutOfRangeIndex() {
        LinkedList<String> list = new LinkedList<>();
        list.add("a");
        list.add(1, "b");
    }

    @Test
    public void insertsItemAtZeroIndex() {
        LinkedList<String> list = new LinkedList<>();
        list.add("a");
        list.add(0, "b");
        Assert.assertEquals("b", list.get(0));
        Assert.assertEquals("a", list.get(1));
    }

    @Test
    public void insertsItemAtAnyIndex() {
        LinkedList<String> list = new LinkedList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add(1, "d");
        Assert.assertArrayEquals(new Object[] {"a", "d", "b", "c"}, list.toArray());
        list.add(3, "e");
        Assert.assertArrayEquals(new Object[] {"a", "d", "b", "e", "c"}, list.toArray());
    }

    @Test
    public void clearsAllItemsFromList() {
        LinkedList<String> list = new LinkedList<>();
        Assert.assertEquals(0, list.size());
        list.clear();
        Assert.assertEquals(0, list.size());
        list.add("a");
        list.clear();
        Assert.assertEquals(0, list.size());
        Assert.assertArrayEquals(new Object[0], list.toArray());
    }

    @Test
    public void indexResultsInNegativeOne() {
        LinkedList<String> list = new LinkedList<>();
        Assert.assertEquals(-1, list.indexOf("a"));
        list.add("a");
        Assert.assertEquals(-1, list.indexOf("b"));
        list.add("b");
        Assert.assertEquals(-1, list.indexOf("c"));
    }

    @Test
    public void returnsIndexOfFirstOccurrenceForValue() {
        LinkedList<String> list = new LinkedList<>();
        list.add("a");
        Assert.assertEquals(0, list.indexOf("a"));
        list.add("b");
        Assert.assertEquals(1, list.indexOf("b"));
        list.add("a");
        Assert.assertEquals(0, list.indexOf("a"));
        list.add("c");
        Assert.assertEquals(3, list.indexOf("c"));
    }

    @Test
    public void lastIndexResultsInNegativeOne() {
        LinkedList<String> list = new LinkedList<>();
        Assert.assertEquals(-1, list.lastIndexOf("a"));
        list.add("a");
        Assert.assertEquals(-1, list.lastIndexOf("b"));
        list.add("b");
        Assert.assertEquals(-1, list.lastIndexOf("c"));
        list.add("c");
        Assert.assertEquals(-1, list.lastIndexOf("d"));
    }

    @Test
    public void lastIndexReturnsLastInstanceOfValue() {
        LinkedList<String> list = new LinkedList<>();
        list.add("a");
        Assert.assertEquals(0, list.lastIndexOf("a"));
        list.add("b");
        Assert.assertEquals(1, list.lastIndexOf("b"));
        list.add("a");
        Assert.assertEquals(2, list.lastIndexOf("a"));
    }

    @Test
    public void listCalculatesHashCode() {
        // Increases by powers of 31, per the interface definition of the hashCode
        LinkedList<String> list = new LinkedList<>();
        Assert.assertEquals(1, list.hashCode());
        list.add("a");
        Assert.assertTrue(31 < list.hashCode());
        list.add("b");
        Assert.assertTrue(31 * 31 < list.hashCode());
        list.add("c");
        Assert.assertTrue(31 * 31 * 31 < list.hashCode());
    }

    @Test
    public void equalsTrueForListsWithEqualMembers() {
        LinkedList<String> linked = new LinkedList<>();
        List<String> vector = new Vector<>();
        Assert.assertTrue(linked.equals(vector));
        linked.add("a");
        vector.add("a");
        Assert.assertTrue(linked.equals(vector));
        linked.add("b");
        vector.add("b");
        Assert.assertTrue(linked.equals(vector));
    }

    @Test
    public void equalsFalseForListsWithUnequalMembers() {
        LinkedList<String> linked = new LinkedList<>();
        List<String> vector = new Vector<>();
        linked.add("a");
        Assert.assertFalse(linked.equals(vector));
        vector.add("a");
        Assert.assertFalse(new LinkedList<String>().equals(vector));
        linked.add("b");
        linked.add("c");
        vector.add("c");
        vector.add("b");
        Assert.assertFalse(linked.equals(vector));
        linked.clear();
        vector.clear();
        linked.add("a");
        vector.add("b");
        Assert.assertFalse(linked.equals(vector));
    }

    @Test
    public void equalsFalseIfObjectIsNotList() {
        LinkedList<String> list = new LinkedList<>();
        String[] array = new String[0];
        Assert.assertNotEquals(list, array);
    }

    @Test
    public void createsIterator() {
        LinkedList<String> list = new LinkedList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        Iterator<String> iterator = list.iterator();
        Assert.assertEquals(iterator.getClass(), LinkedIterator.class);
        Assert.assertEquals("a", iterator.next());
        Assert.assertEquals("b", iterator.next());
        Assert.assertEquals("c", iterator.next());
    }

    @Test
    public void createsListIterator() {
        LinkedList<String> list = new LinkedList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        ListIterator<String> iterator = list.listIterator();
        Assert.assertEquals(iterator.getClass(), LinkedListIterator.class);
        Assert.assertEquals("a", iterator.next());
        Assert.assertEquals("b", iterator.next());
        Assert.assertEquals("c", iterator.next());
    }

    @Test
    public void checksForMultipleElementExistence() {
        LinkedList<String> list = new LinkedList<>();
        Collection<String> items = new Vector<>();
        Assert.assertTrue(list.containsAll(items));
        items.add("a");
        Assert.assertFalse(list.containsAll(items));
        list.add("b");
        Assert.assertFalse(list.containsAll(items));
        items.add("c");
        list.add("a");
        Assert.assertFalse(list.containsAll(items));
    }
}
