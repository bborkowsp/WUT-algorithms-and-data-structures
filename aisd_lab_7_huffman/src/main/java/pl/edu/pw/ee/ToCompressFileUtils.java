package pl.edu.pw.ee;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class ToCompressFileUtils {

    private final int[] frequencyOfCharacters;
    private String allCodes;

    public ToCompressFileUtils(int numberOfAsciiCharacters, String initialString) {
        this.frequencyOfCharacters = new int[numberOfAsciiCharacters];
        this.allCodes = initialString;
    }

    public int[] getFrequencyOfCharacters() {
        return frequencyOfCharacters;
    }

    public String getAllCodes() {
        return allCodes;
    }

    public void readCharactersFromFileToCompress(File fileToCompress) throws IOException {
        int newLineAsciiCode = '\n';
        int numberOfLines = -1;
        try {
            Scanner scanner = new Scanner(fileToCompress);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                numberOfLines++;
                for (char character : line.toCharArray()) {
                    int characterAsciiCode = (int) character;
                    if (characterAsciiCode > 127) {
                        throw new IllegalArgumentException("Character is not in Ascii table, in line number = " + numberOfLines);
                    }
                    frequencyOfCharacters[characterAsciiCode]++;
                }
            }
        } catch (IOException e) {
            throw new IOException("Error, connot read from file!");
        }
        frequencyOfCharacters[newLineAsciiCode] = numberOfLines;
    }

    public void characterEncoding(List<CodedChar> codedChars, File fileToCompress) throws IOException {
        BufferedReader bufferedReader = null;
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(fileToCompress);
            bufferedReader = new BufferedReader(fileReader);
            int c;
            while ((c = bufferedReader.read()) != -1) {
                char character = (char) c;
                for (CodedChar codedChar : codedChars) {
                    if (character == codedChar.getCharacter()) {
                        allCodes += codedChar.getCode();
                    }
                }
            }
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("Error, the file you want to compress was not found!");
        } catch (IOException e) {
            throw new IOException("Error, cannot read from file that you want to compress!");
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (IOException e) {
                throw new IOException("Error, cannot close the object reading from the file you want to compress");
            }
            try {
                if (fileReader != null) {
                    fileReader.close();
                }
            } catch (IOException e) {
                throw new IOException("Error, cannot close the input stream of file that you want to compress ");
            }

        }
    }
}
