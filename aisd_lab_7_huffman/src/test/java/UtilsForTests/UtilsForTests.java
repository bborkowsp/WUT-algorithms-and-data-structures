package UtilsForTests;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import pl.edu.pw.ee.CodedChar;
import pl.edu.pw.ee.Node;
import pl.edu.pw.ee.ToCompressFileUtils;
import static pl.edu.pw.ee.TreeUtils.buildHuffmanTree;

public class UtilsForTests {

    public static boolean testIfContentOfTwoFilesIsEqual(File fileToCompress, File decompressedFile) throws IOException {
        BufferedReader bufferedReaderDecompressed = null;
        FileReader fileReaderDecompressed = null;
        BufferedReader bufferedReaderFileToCompress = null;
        FileReader fileReaderFileToCompress = null;
        try {
            fileReaderDecompressed = new FileReader(decompressedFile);
            fileReaderFileToCompress = new FileReader(fileToCompress);
            bufferedReaderDecompressed = new BufferedReader(fileReaderDecompressed);
            bufferedReaderFileToCompress = new BufferedReader(fileReaderFileToCompress);
            int cDecompressed;
            int cFileToCompress;
            while ((cDecompressed = bufferedReaderDecompressed.read()) != -1) {
                char characterDecompressed = (char) cDecompressed;
                cFileToCompress = bufferedReaderFileToCompress.read();
                if (cFileToCompress == -1) {
                    return false;
                }
                char characterFileToCompress = (char) cFileToCompress;
                if (characterDecompressed != characterFileToCompress) {
                    return false;
                }
            }
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("Error, the file not found!");
        } catch (IOException e) {
            throw new IOException("Error, cannot read from tests file");
        } finally {
            try {
                if (fileReaderDecompressed != null && fileReaderFileToCompress != null) {
                    fileReaderDecompressed.close();
                    fileReaderFileToCompress.close();
                }
            } catch (IOException e) {
                throw new IOException("Error, cannot close the input stream of file");
            }
            try {
                if (bufferedReaderDecompressed != null && bufferedReaderFileToCompress != null) {
                    bufferedReaderDecompressed.close();
                    bufferedReaderFileToCompress.close();
                }
            } catch (IOException e) {
                throw new IOException("Error, cannot close the object reading from the file");
            }
        }
        return true;
    }

    public static String generateRandomString() {
        int leftLimitAsciiCode = 32;
        int rightLimitAsciiCode = 127;
        int StringLength = 20_000;
        Random random = new Random();
        StringBuilder buf = new StringBuilder(StringLength);
        for (int i = 0; i < StringLength; i++) {
            int randomLimitedInt = leftLimitAsciiCode + (int) (random.nextFloat() * (rightLimitAsciiCode - leftLimitAsciiCode + 1));
            buf.append((char) randomLimitedInt);
        }

        String generatedString = buf.toString();
        return generatedString;
    }

    public static String MultiLineString() {
        String testString = new StringBuilder()
                .append("\nTo impr\nove is to chan\t\tge, so to be perfect is to change often.\n")
                .append("-Winston Churchill")
                .append("Tell me an\nd I forget. Teach me and I remember. Involve me and I learn.\n")
                .append("-Benjamin Franklin")
                .append("\t \t The \n  \t \n End")
                .toString();
        return testString;

    }

    public static String LoremIpsumString() {
        String loremIpsum = "<p>Lorem ipsum dolor sit amet. Et dolor asperiores est dolore laudantium aut placeat praesentium.\n "
                + "Ad sint explicabo non voluptas autem sed facere magni ab tenetur numquam. Ex reprehenderit rerum in velit reprehenderit sit optio impedit qui atque culpa rem consequuntur quis qui odio odit.\n"
                + " </p><p>Et illum tempore in quidem repudiandae vel sunt quia et quia autem est commodi accusantium ut nulla enim!\n "
                + "Sed rerum galisum quo nostrum minima nam quia rerum qui laborum aliquid eos fugit adipisci in sapiente delectus.\n"
                + " Qui minus unde sed dignissimos soluta vel saepe iste! Quo rerum veniam vel voluptatibus perspiciatis ea quae expedita non doloribus eveniet?\n"
                + " </p><p>Eum voluptate quae eum illum culpa quo ratione incidunt et deleniti incidunt aut consequatur omnis et molestiae ipsa non expedita praesentium.\n"
                + " 33 sunt sunt et mollitia omnis et animi aperiam non quis modi. Non nihil modi a eligendi quisquam et ducimus suscipit. \n";
        return loremIpsum;
    }

    public static void toCompressFilePreparation(String text, File fileToCompress) throws IOException {
        BufferedWriter bufferedWriter = null;
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(fileToCompress));
            bufferedWriter.write(text);
        } catch (IOException e) {
            throw new IOException("Cannot write to test 'fileToCompress' file");
        } finally {
            if (bufferedWriter != null) {
                try {
                    bufferedWriter.close();
                } catch (IOException e) {
                    throw new IOException("Error, cannot close the object writing to test 'fileToCompress' file");
                }
            }
        }
    }

    public static int expectedNumberOfEncodedBits(File fileToCompress) throws IOException {
        ToCompressFileUtils toCompressFileUtils = new ToCompressFileUtils(128, "");
        toCompressFileUtils.readCharactersFromFileToCompress(fileToCompress);
        Node root = buildHuffmanTree(toCompressFileUtils.getFrequencyOfCharacters());

        List<CodedChar> codedChars = new ArrayList<>();
        root.encode(codedChars);
        toCompressFileUtils.characterEncoding(codedChars, fileToCompress);
        String allCodes = toCompressFileUtils.getAllCodes();
        int expected = 32 + ((int) Math.ceil((double) allCodes.length() / 8)) * 8;
        return expected;
    }

    public static List<CodedChar> preparationForAllCodesTesting(File fileToCompress, ToCompressFileUtils toCompressFileUtils) throws IOException {
        toCompressFileUtils.readCharactersFromFileToCompress(fileToCompress);
        Node root = buildHuffmanTree(toCompressFileUtils.getFrequencyOfCharacters());

        List<CodedChar> codedChars = new ArrayList<>();
        root.encode(codedChars);
        return codedChars;
    }

}
