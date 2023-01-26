package pl.edu.pw.ee;

import java.util.Arrays;
import java.util.Random;
import org.junit.Assert;
import org.junit.Test;
import pl.edu.pw.ee.services.Sorting;

public abstract class CommonSortTest {

    private static final double EPS = 0;
    private static final int SEED = 0;
    private static final int SIZE = 100_000; /* Array size for "shouldCorrectlySortWhenLargeSetOfRandomValues" test */
    protected Sorting sorter;

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenArrayIsNull() {
        // given
        double[] nums = null;

        // when
        sorter.sort(nums);

        // then
        assert false;
    }

    @Test
    public void shouldPassWhenInputArrayIsEmpty() {
        // given
        double[] nums = {};

        // when
        sorter.sort(nums);

        // then
        int expectedSize = 0;
        Assert.assertEquals(expectedSize, nums.length);
    }

    @Test
    public void shouldPassWhenOnlyOneElemInArray() {
        // given
        double[] nums = {1};

        // when
        sorter.sort(nums);

        // then
        double[] expected = {1};
        Assert.assertArrayEquals(expected, nums, EPS);
    }

    @Test
    public void shouldCorrectlySortWhenTwoElemInArray() {
        // given
        double[] nums = {2, 1};

        // when
        sorter.sort(nums);

        // then
        double[] expected = {1, 2};
        Assert.assertArrayEquals(expected, nums, EPS);
    }

    @Test
    public void shouldCorrectlySortWhenThreeElemInArray() {
        // given
        double[] nums = {3, 1, 2};

        // when
        sorter.sort(nums);

        // then
        double[] expected = {1, 2, 3};
        Assert.assertArrayEquals(expected, nums, EPS);
    }

    @Test
    public void shouldCorrectlySortWhenValuesInArrayAreRepeated() {
        // given
        double[] nums = {3, 4, 1, 5, 1, 3};

        // when
        sorter.sort(nums);

        // then
        double[] expected = {1, 1, 3, 3, 4, 5};
        Assert.assertArrayEquals(expected, nums, EPS);
    }

    @Test
    public void shouldCorrectlySortWhenOnlyFirstElemIsInWrongPlaceAndArrayLenIsEven() {
        // given
        double[] nums = {6, 1, 2, 3, 4, 5};

        // when
        sorter.sort(nums);

        // then
        double[] expected = {1, 2, 3, 4, 5, 6};
        Assert.assertArrayEquals(expected, nums, EPS);
    }

    @Test
    public void shouldCorrectlySortWhenOnlyFirstElemIsInWrongPlaceAndArrayLenIsOdd() {
        // given
        double[] nums = {7, 1, 2, 3, 4, 5, 6};

        // when
        sorter.sort(nums);

        // then
        double[] expected = {1, 2, 3, 4, 5, 6, 7};
        Assert.assertArrayEquals(expected, nums, EPS);
    }

    @Test
    public void shouldCorrectlySortWhenOnlyLastElemIsInWrongPlaceAndArrayLenIsEven() {
        // given
        double[] nums = {2, 3, 4, 5, 6, 1};

        // when
        sorter.sort(nums);

        // then
        double[] expected = {1, 2, 3, 4, 5, 6};
        Assert.assertArrayEquals(expected, nums, EPS);
    }

    @Test
    public void shouldCorrectlySortWhenOnlyLastElemIsInWrongPlaceAndArrayLenIsOdd() {
        // given
        double[] nums = {2, 3, 4, 5, 6, 7, 1};

        // when
        sorter.sort(nums);

        // then
        double[] expected = {1, 2, 3, 4, 5, 6, 7};
        Assert.assertArrayEquals(expected, nums, EPS);
    }

    @Test
    public void shouldCorrectlySortWhenInputValuesInOrder() {
        // given
        double[] nums = {1, 2, 3, 4, 5};

        // when
        sorter.sort(nums);

        // then
        double[] expected = {1, 2, 3, 4, 5};
        Assert.assertArrayEquals(expected, nums, EPS);
    }

    @Test
    public void shouldCorrectlySortWhenAllInputInRevertOrder() {
        // given
        double[] nums = {5, 4, 3, 2, 1};

        // when
        sorter.sort(nums);

        // then
        double[] expected = {1, 2, 3, 4, 5};
        Assert.assertArrayEquals(expected, nums, EPS);
    }

    @Test
    public void shouldCorrectlySortWhenLargeValuesInArray() {
        // given
        double[] nums = {Double.POSITIVE_INFINITY, Double.MIN_VALUE, Double.NEGATIVE_INFINITY, Double.MAX_VALUE};

        // when
        sorter.sort(nums);

        // then
        double[] expected = {Double.NEGATIVE_INFINITY, Double.MIN_VALUE, Double.MAX_VALUE, Double.POSITIVE_INFINITY};
        Assert.assertArrayEquals(expected, nums, EPS);
    }

    @Test
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
