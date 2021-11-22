package collections;

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Vector;
import java.util.function.BiFunction;
import java.util.function.Function;

public class Sorting {
    public static <T> void bubbleSort(List<T> list, BiFunction<T, T, Integer> comparer) {
        if (list.size() < 2) return;
        boolean hasSwapped;
        do {
            hasSwapped = false;
            ListIterator<T> iterator = list.listIterator();
            T prev = iterator.next();
            while (iterator.hasNext()) {
                T next = iterator.next();
                if (greaterThan(prev, next, comparer)) {
                    iterator.remove();
                    prev = iterator.previous();
                    iterator.add(next);
                    iterator.next();
                    hasSwapped = true;
                } else
                    prev = next;
            }
        } while (hasSwapped);
    }

    public static <T> void mergeSort(List<T> list, BiFunction<T, T, Integer> comparer) {
        if (list.size() < 2) return;
        List<T> left = new Vector<>();
        List<T> right = new Vector<>();

        Iterator<T> iterator = list.iterator();
        for (int i = 0; i < list.size() / 2; i++)
            left.add(iterator.next());
        while (iterator.hasNext())
            right.add(iterator.next());

        mergeSort(left, comparer);
        mergeSort(right, comparer);
        mergeIntoList(list, left, right, comparer);
    }

    private static <T> void mergeIntoList(List<T> destination, List<T> left, List<T> right, BiFunction<T, T, Integer> comparer) {
        destination.clear();
        ListIterator<T> leftIterator = left.listIterator();
        ListIterator<T> rightIterator = right.listIterator();

        leftIterator.next();
        rightIterator.next();
        while (leftIterator.hasNext() && rightIterator.hasNext())
            dumpRemainingIterator(destination, comparer, leftIterator, rightIterator);
        dumpRemainingIterator(destination, comparer, leftIterator, rightIterator);
        T leftItem = peekPrevious(leftIterator);
        T rightItem = peekPrevious(rightIterator);

        if (greaterThan(leftItem, rightItem, comparer)) {
            destination.add(rightItem);
            destination.add(leftItem);
        } else {
            destination.add(leftItem);
            destination.add(rightItem);
        }

        leftIterator.forEachRemaining(i -> destination.add(i));
        rightIterator.forEachRemaining(i -> destination.add(i));
    }

    private static <T> void dumpRemainingIterator(List<T> destination, BiFunction<T, T, Integer> comparer, ListIterator<T> leftIterator, ListIterator<T> rightIterator) {
        T leftItem = peekPrevious(leftIterator);
        dumpWhile(destination, rightIterator, right -> !lessThan(leftItem, right, comparer));
        T rightItem = peekPrevious(rightIterator);
        dumpWhile(destination, leftIterator, left -> !greaterThan(left, rightItem, comparer));
    }

    private static <T> void dumpWhile(List<T> destination, ListIterator<T> iterator, Function<T, Boolean> predicate) {
        T value = peekPrevious(iterator);
        while(iterator.hasNext() && predicate.apply(value)) {
            destination.add(value);
            value = iterator.next();
        }
    }

    private static <T> T peekPrevious(ListIterator<T> iterator) {
        iterator.previous();
        return iterator.next();
    }

    private static <T> boolean greaterThan(T leftItem, T rightItem, BiFunction<T,T, Integer> comparer) {
        return comparer.apply(leftItem, rightItem) > 0;
    }

    private static <T> boolean lessThan(T leftItem, T rightItem, BiFunction<T, T, Integer> comparer) {
        return comparer.apply(leftItem, rightItem) < 0;
    }
}
