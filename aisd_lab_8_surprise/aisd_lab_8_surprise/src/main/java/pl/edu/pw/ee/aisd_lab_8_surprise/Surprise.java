package pl.edu.pw.ee.aisd_lab_8_surprise;

public class Surprise {

    private static final int maxValue = 99_999;
    private static final int minValue = -99_999;

    public static int countMaxSum(int[] vector) {
        if (vector.length < 2 || vector.length > maxValue) {
            throw new IllegalArgumentException("Illegal vector lenght!");
        }
        int sum = vector[0];
        int numberOfSixElementsVectorsInVector = vector.length / 6;
        int rest = vector.length - numberOfSixElementsVectorsInVector * 6;
        int start = 1;
        for (int i = 0; i < numberOfSixElementsVectorsInVector; i++) {

            sum += findMaxInSix(vector, start);
            start += 6;
        }
        int tries = 0;
        int sum2=0;

        return sum + sum2;
    }
    
    private static int findMax2(int[] vector, int start, int tries) {
        int maxid = start;
        int sum2 = 0;
        for (int i = start; i < vector.length- start; i++) {
            if (vector[maxid] < vector[start+i]) {
                maxid = start+i;
                sum2 = vector[maxid];
            }
        }
        if(maxid!=start && tries !=6){
            tries++;
            findMax2(vector,start,tries);
        }
        return sum2;
    }

    private static int findMaxInSix(int[] vector, int start) {
        int max;
        int vectorIndexForMaxValue = 0;
        for (int i = 0; i < 5; i++) {
            if (vector[vectorIndexForMaxValue] <= vector[start+i]) {
                vectorIndexForMaxValue = start+i;
            }
        }
        max = vector[vectorIndexForMaxValue];
        return max;
    }

    public static void main(String[] args) {
        //   System.out.println(7 / 6);
        int vector[] = {-3, 0, 4,2};
        int r =countMaxSum(vector);
        System.out.println(countMaxSum(vector));
    }
}
