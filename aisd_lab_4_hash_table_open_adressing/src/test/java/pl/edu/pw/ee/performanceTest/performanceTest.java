package pl.edu.pw.ee.performanceTest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import org.junit.Test;
import pl.edu.pw.ee.HashLinearProbing;
import pl.edu.pw.ee.services.HashTable;

public class performanceTest {

    @Test
    public void performanceTest() {

        int numberOfMeasurements = 30;
        int size = 512;
        Long[] sortTimes = new Long[numberOfMeasurements];

        String[] words = readFromFile();
        if (words == null) {
            throw new IllegalArgumentException("Error, cannot read from file");
        }

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < numberOfMeasurements; j++) {
                HashTable<String> hash = new HashLinearProbing<>(size);
                long startTime = System.nanoTime();
                for (String word : words) {
                    hash.put(word);
                }
                long endTime = System.nanoTime();
                sortTimes[j] = endTime - startTime;
            }
            long sumOfSortTimes = 0;

            for (int k = numberOfMeasurements / 3; k < numberOfMeasurements * 2 / 3; k++) {
                sumOfSortTimes += sortTimes[k];
            }
            System.out.println("Hash size = " + size + ", average put time = " + sumOfSortTimes / 10 + " [ns]");
            size *= 2;
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
