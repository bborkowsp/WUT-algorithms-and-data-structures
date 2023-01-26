package pl.edu.pw.ee.performanceTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import pl.edu.pw.ee.SelectionSort;
import pl.edu.pw.ee.services.Sorting;

public class SelectionSortPerformanceTest extends CommonSortPerformanceTest {

    private static final int NUMBER_OF_TESTS = 40;
    private static final int NUMBER_OF_SORT_REPS_PER_ARRAY = 10;
    private static final int ARRAY_LENGHT_INCREASE = 10;

    public SelectionSortPerformanceTest() {
        super(new SelectionSort());
    }

    @Test
    public void performanceTestForOptimisticData() {

        Sorting sorter = new SelectionSort();
        List<Long> sortTimes = new ArrayList<>();
        int numOfElemInArrPerIter = 0;

        for (int i = 1; i < NUMBER_OF_TESTS; i++) {

            numOfElemInArrPerIter += ARRAY_LENGHT_INCREASE;
            double[] nums = new double[numOfElemInArrPerIter];
            int temporaryValue = 0;

            for (int j = 0; j < numOfElemInArrPerIter; j++) {
                nums[j] = temporaryValue;
                temporaryValue++;
            }

            double[] notSortedNums = Arrays.copyOf(nums, numOfElemInArrPerIter);
            long sumOfSortTimes = 0;

            for (int k = 0; k < NUMBER_OF_SORT_REPS_PER_ARRAY; k++) {

                nums = Arrays.copyOf(notSortedNums, numOfElemInArrPerIter);
                long startTime = System.nanoTime();
                sorter.sort(nums);
                long endTime = System.nanoTime();
                sumOfSortTimes += (endTime - startTime);

            }
            long arithmeticAverage = sumOfSortTimes / NUMBER_OF_SORT_REPS_PER_ARRAY;
            sortTimes.add(arithmeticAverage);
        }

        System.out.println("\nSort time(Optimistic) = " + sortTimes);
        System.out.println("\n");
    }

    @Test
    public void performanceTestForPessimisticData() {

        Sorting sorter = new SelectionSort();
        List<Long> sortTimes = new ArrayList<>();
        int numOfElemInArrPerIter = 0;

        for (int i = 1; i < NUMBER_OF_TESTS; i++) {

            numOfElemInArrPerIter += ARRAY_LENGHT_INCREASE;
            double[] nums = new double[numOfElemInArrPerIter];
            int temporaryValue = numOfElemInArrPerIter;

            for (int j = 0; j < numOfElemInArrPerIter; j++) {
                nums[j] = temporaryValue;
                temporaryValue--;
            }

            double[] notSortedNums = Arrays.copyOf(nums, numOfElemInArrPerIter);
            long sumOfSortTimes = 0;

            for (int k = 0; k < NUMBER_OF_SORT_REPS_PER_ARRAY; k++) {

                nums = Arrays.copyOf(notSortedNums, numOfElemInArrPerIter);
                long startTime = System.nanoTime();
                sorter.sort(nums);
                long endTime = System.nanoTime();
                sumOfSortTimes += (endTime - startTime);

            }
            long arithmeticAverage = sumOfSortTimes / NUMBER_OF_SORT_REPS_PER_ARRAY;
            sortTimes.add(arithmeticAverage);
        }

        System.out.println("\nSort time(Pessimistic) = " + sortTimes);
        System.out.println("\n");
    }

}
