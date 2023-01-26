package pl.edu.pw.ee.performanceTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import org.junit.Test;
import pl.edu.pw.ee.services.Sorting;

public abstract class CommonSortPerformanceTest {

    protected Sorting commonSorter;
    private static final int SEED = 0;
    private static final int NUMBER_OF_TESTS = 40;
    private static final int NUMBER_OF_SORT_REPS_PER_ARRAY = 10;
    private static final int ARRAY_LENGHT_INCREASE = 10;

    public CommonSortPerformanceTest(Sorting sorter) {
        this.commonSorter = sorter;
    }

    @Test
    public void performanceTestForRandomValues() {

        List<Long> sortTimes = new ArrayList<>();
        List<Integer> numberOfElements = new ArrayList<>();
        Random random = new Random(SEED);

        int numOfElemInArrPerIter = 0;
        for (int i = 1; i < NUMBER_OF_TESTS; i++) {

            numOfElemInArrPerIter += ARRAY_LENGHT_INCREASE;
            numberOfElements.add(numOfElemInArrPerIter);
            double[] nums = new double[numOfElemInArrPerIter];

            for (int j = 0; j < numOfElemInArrPerIter; j++) {
                nums[j] = random.nextDouble();
            }

            double[] notSortedNums = Arrays.copyOf(nums, numOfElemInArrPerIter);
            long sumOfSortTimes = 0;

            for (int k = 0; k < NUMBER_OF_SORT_REPS_PER_ARRAY; k++) {

                nums = Arrays.copyOf(notSortedNums, numOfElemInArrPerIter);
                long startTime = System.nanoTime();
                commonSorter.sort(nums);
                long endTime = System.nanoTime();
                sumOfSortTimes += (endTime - startTime);

            }
            long arithmeticAverage = sumOfSortTimes / NUMBER_OF_SORT_REPS_PER_ARRAY;
            sortTimes.add(arithmeticAverage);
        }
        
        System.out.println("\nNumber of array elements for each array" + numberOfElements);
        System.out.println("\nSort time(Random) = " + sortTimes);
        System.out.println("\n");
    }

}
