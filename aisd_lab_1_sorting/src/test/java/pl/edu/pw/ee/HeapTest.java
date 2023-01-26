package pl.edu.pw.ee;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Arrays;

public class HeapTest {

    private Heap<Double> heap;
    List<Double> data = new ArrayList<>();
    private static final int SEED = 0;
    private static final int SIZE = 10_000_000;

    @Before
    public void setUp() {
        heap = new Heap(data);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void shouldThrowExceptionWhenHeapIsEmpty() {
        //when
        heap.pop();

        //then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenPuttingNullItem() {
        //given
        Double item = null;

        //when
        heap.put(item);

        //then
        assert false;
    }

    @Test
    public void shouldCorrectlyBuildWhenDataHasOneElem() {
        //given
        data.add(0.0);

        //when
        heap.build();

        //then
        Double[] expected = {0.0};
        Double[] actuals = data.toArray(new Double[0]);
        Assert.assertArrayEquals(expected, actuals);

    }

    @Test
    public void shouldCorrectlyBuildWhenDataHasTwoElem() {
        //given
        Double[] nums = {0.0, 1.0};
        Collections.addAll(data, nums);

        //when
        heap.build();
        //then
        Double[] expected = {1.0, 0.0};
        Double[] actuals = data.toArray(new Double[0]);
        Assert.assertArrayEquals(expected, actuals);

    }

    @Test
    public void shouldPutAndPopCorretly() {
        //given
        Random random = new Random(SEED);
        heap.build();
        Double[] expected = new Double[SIZE];
        Double[] actuals = new Double[SIZE];

        for (int i = 0; i < SIZE; i++) {
            Double item = random.nextDouble();
            expected[i] = item;

            //when
            heap.put(item);
        }
        Arrays.sort(expected, Collections.reverseOrder());

        for (int i = 0; i < SIZE; i++) {
            actuals[i] = heap.pop();
        }

        //then
        Assert.assertArrayEquals(expected, actuals);
    }

    @Test
    public void shoudlPopCorrectValues() {
        //given
        Random random = new Random(SEED);
        Double[] expected = new Double[SIZE / 2];
        Double[] actuals = new Double[SIZE / 2];

        for (int i = 0; i < SIZE / 2; i++) {
            Double item = random.nextDouble();
            data.add(item);
            expected[i] = item;
        }

        Arrays.sort(expected, Collections.reverseOrder());
        heap.build();
        //when
        for (int i = 0; i < SIZE / 2; i++) {
            actuals[i] = heap.pop();
        }

        //then
        Assert.assertArrayEquals(expected, actuals);

    }

    @Test
    public void shouldMaintainCorrectHeapStructureAfterPoppingFewElem() {
        //given
        Double[] nums = {1.0, 2.0, 3.0, 4.0, 5.0};
        Collections.addAll(data, nums);
        heap.build();
        Double[] actuals = new Double[3];

        for (int i = 0; i < 3; i++) {
            //when
            actuals[i] = heap.pop();
        }

        //then
        Double[] expected = {5.0, 4.0, 3.0};
        Assert.assertArrayEquals(expected, actuals);

    }

    @Test
    public void shoudlBuildWithStrings() {
        //given
        Heap<String> heapStrings;
        List<String> dataStrings = new ArrayList<>();
        String[] strings = {"cc", "aa", "bb", "ee"};
        Collections.addAll(dataStrings, strings);
        heapStrings = new Heap(dataStrings);

        //then
        heapStrings.build();

        //then
        String[] expected = {"ee", "cc", "bb", "aa"};
        String[] actuals = dataStrings.toArray(new String[0]);

        Assert.assertArrayEquals(expected, actuals);
    }

}
