package pl.edu.pw.ee.performanceTest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import org.junit.Test;
import pl.edu.pw.ee.HashListChaining;

public class performanceTest {

    @Test
    public void performanceTestForPrimes() {

        int[] hashSize = {4096, 8192, 16384, 32768, 65536, 131072, 262144,
            4093, 8191, 16381, 32771, 65537, 131071, 262147};
        int numberOfMeasurements = 30;
        Long[] sortTimes = new Long[numberOfMeasurements];
        String[] words = readFromFile();

        if (words == null) {
            throw new IllegalArgumentException("Error, cannot read from file");
        }

        for (int i = 0; i < hashSize.length; i++) {
            int size = hashSize[i];
            HashListChaining hashlist = new HashListChaining(size);

            for (String word : words) {
                hashlist.add(word);
            }

            for (int j = 0; j < numberOfMeasurements; j++) {
                long startTime = System.nanoTime();
                for (String word : words) {
                    hashlist.get(word);
                }
                long endTime = System.nanoTime();
                sortTimes[j] = endTime - startTime;
            }
            Arrays.sort(sortTimes);
            long sumOfSortTimes = 0;

            for (int k = numberOfMeasurements / 3; k < numberOfMeasurements * 2 / 3; k++) {
                sumOfSortTimes += sortTimes[k];
            }
            System.out.println("Hash size = " + size + ", average search time = " + sumOfSortTimes / 10 + " [ns]");
        }
    }

    private String[] readFromFile() {
        File file = new File("src\\files\\listOfWords.txt");
        int numberOfWords = 100_000;
        String[] words = new String[numberOfWords];
        int index = 0;

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                words[index] = line;
                index++;
            }
            return words;
        } catch (IOException exception) {
            return null;
        }

    }
}
