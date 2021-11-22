package collections;

import collections.arrayList.ArrayList;
import collections.linkedList.LinkedList;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Random;
import java.util.Vector;
import java.util.Collections;
import java.util.Arrays;
import java.util.function.Consumer;

public class SortingTest {

    public void testListSorting(List<Integer> list, Consumer<List<Integer>> sort) {
        Random randomNumbers = new Random(System.currentTimeMillis());
        List<Integer> expected = new Vector<>();
        for (int i = 0; i < 1000; i++) {
            int rand = randomNumbers.nextInt(1000000);
            list.add(rand);
            expected.add(rand);
        }

        Collections.sort(expected);
        sort.accept(list);
        Assert.assertArrayEquals(expected.toArray(), list.toArray());
    }

    @Test
    public void testLinkedListMergeSort() {
        testListSorting(
            new LinkedList<>(),
            list -> Sorting.mergeSort(list, Integer::compare));
    }

    @Test
    public void testArrayListMergeSort() {
        testListSorting(
            new ArrayList<>(),
            list -> Sorting.mergeSort(list, Integer::compare));
    }

    @Test
    public void testLinkedListBubbleSort() {
        testListSorting(
            new LinkedList<>(),
            list -> Sorting.bubbleSort(list, Integer::compare));
    }

    @Test
    public void testArrayListBubbleSort() {
        testListSorting(
            new ArrayList<>(),
            list -> Sorting.bubbleSort(list, Integer::compare));
    }

    @Test
    public void bubbleSortSortsToIdentity() {
        List<Integer> list = new Vector<>();
        Sorting.bubbleSort(list, Integer::compare);
        Assert.assertArrayEquals(new Integer[]{}, list.toArray());

        list.add(1);
        Sorting.bubbleSort(list, Integer::compare);
        Assert.assertArrayEquals(new Integer[]{1}, list.toArray());

        list.add(2);
        Sorting.bubbleSort(list, Integer::compare);
        Assert.assertArrayEquals(new Integer[]{1, 2}, list.toArray());

        list.add(3);
        Sorting.bubbleSort(list, Integer::compare);
        Assert.assertArrayEquals(new Integer[]{1, 2, 3}, list.toArray());
    }

    @Test
    public void bubbleSortSwapsManyValues() {
        Vector<Integer> list = new Vector<>();
        list.add(2);
        list.add(1);
        Sorting.bubbleSort(list, Integer::compare);
        Assert.assertArrayEquals(new Integer[]{1, 2}, list.toArray());

        list.clear();
        list.addAll(Arrays.asList(2, 3, 1));
        Sorting.bubbleSort(list, Integer::compare);
        Assert.assertArrayEquals(new Integer[]{1, 2, 3}, list.toArray());
    }

    @Test
    public void bubbleSortSortsOneThousandRandomNumbers() {
        Vector<Integer> list = new Vector<>();
        Random randomNumbers = new Random();
        for (int i = 0; i < 1000; i++)
            list.add(randomNumbers.nextInt(100));
        Sorting.bubbleSort(list, Integer::compare);

        Integer previousValue = Integer.MIN_VALUE;
        for (Integer value : list)
            if (value < previousValue)
                Assert.fail();
            else
                previousValue = value;
    }

    @Test
    public void mergeSortSortsToIdentity() {
        Vector<Integer> list = new Vector<>();
        Sorting.mergeSort(list, Integer::compare);
        Assert.assertArrayEquals(new Integer[0], list.toArray());

        list.add(1);
        Sorting.mergeSort(list, Integer::compare);
        Assert.assertArrayEquals(new Integer[]{1}, list.toArray());

        list.add(2);
        Sorting.mergeSort(list, Integer::compare);
        Assert.assertArrayEquals(new Integer[]{1, 2}, list.toArray());

        list.add(3);
        Sorting.mergeSort(list, Integer::compare);
        Assert.assertArrayEquals(new Integer[]{1, 2, 3}, list.toArray());

        list.add(4);
        Sorting.mergeSort(list, Integer::compare);
        Assert.assertArrayEquals(new Integer[]{1, 2, 3, 4}, list.toArray());
    }

    @Test
    public void mergeSortsManyValues() {
        Vector<Integer> list = new Vector<>();

        list.addAll(Arrays.asList(9, 8, 7, 6, 5, 4, 3, 2, 1));
        Sorting.mergeSort(list, Integer::compare);
        Assert.assertArrayEquals(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9}, list.toArray());
        list.clear();

        list.addAll(Arrays.asList(2, 1));
        Sorting.mergeSort(list, Integer::compare);
        Assert.assertArrayEquals(new Integer[]{1, 2}, list.toArray());
        list.addAll(Arrays.asList(4, 5, 3));
        Sorting.mergeSort(list, Integer::compare);
        Assert.assertArrayEquals(new Integer[]{1, 2, 3, 4, 5}, list.toArray());
        list.addAll(Arrays.asList(9, 8, 7, 6, 5, 4, 3, 2, 1));
        Sorting.mergeSort(list, Integer::compare);
        Assert.assertArrayEquals(new Integer[]{1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 7, 8, 9}, list.toArray());
    }

    @Test
    public void mergeSortsOneThousandRandomNumbers() {
        Vector<Integer> list = new Vector<>();
        Random randomNumbers = new Random();
        for (int i = 0; i < 1000; i++)
            list.add(randomNumbers.nextInt(100));
        Sorting.mergeSort(list, Integer::compare);

        Integer previousValue = Integer.MIN_VALUE;
        for (Integer value : list)
            if (value < previousValue)
                Assert.fail();
            else
                previousValue = value;
    }
}
