package pl.edu.pw.ee;

import java.util.Arrays;
import java.util.Random;
import org.junit.Assert;

public class HeapSortTest extends CommonSortTest {

    private static final double EPS = 0;
    private static final int SEED = 0;
    private static final int SIZE = 10_000_000;

    public HeapSortTest() {
        this.sorter = new HeapSort();
    }

    @Override
    public void shouldCorrectlySortWhenLargeSetOfRandomValues() {
        //given
        double[] nums = new double[SIZE];
        Random random = new Random(SEED);
        for (int i = 0; i < SIZE; i++) {
            nums[i] = random.nextDouble();
        }

        //when
        sorter.sort(nums);

        //then
        double[] expected = Arrays.copyOf(nums, SIZE);
        Arrays.sort(expected);
        Assert.assertArrayEquals(expected, nums, EPS);
    }
}
