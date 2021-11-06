package collections.arrayList;

import org.junit.Assert;
import org.junit.Test;

import java.util.Iterator;

public class ArrayListTest {

    @Test
    public void newArrayList() {
        ArrayList<Integer> list = new ArrayList<>(new Integer[0]);
        Iterator<Integer> iterator = list.iterator();
        Assert.assertFalse(iterator.hasNext());

        list = new ArrayList<>(new Integer[]{1, 2, 3});
        iterator = list.iterator();
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals(1, (int)iterator.next());
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals(2, (int)iterator.next());
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals(3, (int)iterator.next());
        Assert.assertFalse(iterator.hasNext());
    }
}
