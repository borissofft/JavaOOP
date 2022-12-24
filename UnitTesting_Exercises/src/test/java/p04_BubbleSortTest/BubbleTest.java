package p04_BubbleSortTest;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class BubbleTest {

    private int[] elements = {13, 42, 69, -1, 0, -2, -34};
    private int[] sorted = Arrays.stream(elements).sorted().toArray();

    @Test
    public void sortShouldTestTheCollection() {
        Bubble.sort(elements);
        Assert.assertArrayEquals(sorted, elements);
    }

    @Test
    public void sortShouldNotReorderElementsInSortedArray() {
        int[] before = sorted.clone();
        Bubble.sort(before);
        Assert.assertArrayEquals(sorted, before);
    }

}