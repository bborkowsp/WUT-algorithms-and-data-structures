package pl.edu.pw.ee;

import pl.edu.pw.ee.services.Sorting;

public class InsertionSort implements Sorting {

    @Override
    public void sort(double[] nums) {
        if (nums == null) {
            throw new IllegalArgumentException("Nums array cannot be null");
        }
        int numsLength = nums.length;
        int i, j;

        for (i = 1; i < numsLength; i++) {
            double temporaryValue = nums[i];
            j = i;
            while (j > 0 && nums[j - 1] >= temporaryValue) {
                nums[j] = nums[j - 1];
                j--;
            }
            nums[j] = temporaryValue;
        }
    }
}
