package pl.edu.pw.ee;

import static UtilsForTests.UtilsForTests.MultiLineString;
import static UtilsForTests.UtilsForTests.generateRandomString;
import static UtilsForTests.UtilsForTests.testIfContentOfTwoFilesIsEqual;
import static UtilsForTests.UtilsForTests.toCompressFilePreparation;
import java.io.File;
import java.io.IOException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static UtilsForTests.UtilsForTests.LoremIpsumString;

public class DecompressFileTest {

    private Huffman huffman;
    private final String pathToRootDir = new File("src\\test\\testFiles").getAbsolutePath();

    private File fileToCompress;

    @Before
    public void setUp() {
        this.huffman = new Huffman();
        this.fileToCompress = new File(pathToRootDir + "\\ToCompress.txt");
    }

    @Test
    public void testIfNumberOfCharInDecompressedFileIsCorrect_WhenCompressingOneChar() throws IOException {
        //given
        String text = "A";
        toCompressFilePreparation(text, fileToCompress);
        huffman.huffman(pathToRootDir, true);

        //when
        int actual = huffman.huffman(pathToRootDir, false);

        //then
        Assert.assertEquals(text.length(), actual);
    }

    @Test
    public void testIfNumberOfCharInDecompressedFileIsCorrect_WhenCompressingTwoChars() throws IOException {
        //given
        String text = "A:";
        toCompressFilePreparation(text, fileToCompress);
        huffman.huffman(pathToRootDir, true);

        //when
        int actual = huffman.huffman(pathToRootDir, false);

        //then
        Assert.assertEquals(text.length(), actual);
    }

    @Test
    public void testIfNumberOfCharInDecompressedFileIsCorrect_WhenCompressingTreeChars() throws IOException {
        //given
        String text = "E    N";
        toCompressFilePreparation(text, fileToCompress);
        huffman.huffman(pathToRootDir, true);

        //when
        int actual = huffman.huffman(pathToRootDir, false);

        //then
        Assert.assertEquals(text.length(), actual);
    }

    @Test
    public void testIfNumberOfCharInDecompressedFileIsCorrect_WhenCompressingSomeChars() throws IOException {
        //given
        String text = "NIEMANIE";
        toCompressFilePreparation(text, fileToCompress);
        huffman.huffman(pathToRootDir, true);

        //when
        int actual = huffman.huffman(pathToRootDir, false);

        //then
        Assert.assertEquals(text.length(), actual);
    }

    @Test
    public void testIfNumberOfCharInDecompressedFileIsCorrect_WhenCompressingSameCharManyTimes() throws IOException {
        //given
        String text = "";
        for (int i = 0; i < 20_000; i++) {
            text += ' ';
        }
        toCompressFilePreparation(text, fileToCompress);
        huffman.huffman(pathToRootDir, true);

        //when
        int actual = huffman.huffman(pathToRootDir, false);

        //then
        Assert.assertEquals(text.length(), actual);
    }

    @Test
    public void testIfNumberOfCharInDecompressedFileIsCorrect_WhenCompressingBigRandomString() throws IOException {
        //given
        String text = generateRandomString();
        toCompressFilePreparation(text, fileToCompress);
        huffman.huffman(pathToRootDir, true);

        //when
        int actual = huffman.huffman(pathToRootDir, false);

        //then
        Assert.assertEquals(text.length(), actual);
    }

    @Test
    public void testIfNumberOfCharInDecompressedFileIsCorrect_WhenCompressingMultiLineString() throws IOException {
        //given
        String text = MultiLineString();
        toCompressFilePreparation(text, fileToCompress);
        huffman.huffman(pathToRootDir, true);

        //when
        int actual = huffman.huffman(pathToRootDir, false);

        //then
        Assert.assertEquals(text.length(), actual);
    }

    @Test
    public void testIfNumberOfCharInDecompressedFileIsCorrect_WhenCompressingLoremIpsumString() throws IOException {
        //given
        String text = LoremIpsumString();
        toCompressFilePreparation(text, fileToCompress);
        huffman.huffman(pathToRootDir, true);

        //when
        int actual = huffman.huffman(pathToRootDir, false);

        //then
        Assert.assertEquals(text.length(), actual);
    }

    @Test
    public void testIfToCompressAndDecompressedFilesAreEqual_WhenCompressingOneChar() throws IOException {
        //given
        String oneChar = "?";
        toCompressFilePreparation(oneChar, fileToCompress);

        //when
        huffman.huffman(pathToRootDir, true);
        huffman.huffman(pathToRootDir, false);
        File decompressedFile = new File(pathToRootDir + "\\Decompressed.txt");
        //then
        boolean actual = testIfContentOfTwoFilesIsEqual(fileToCompress, decompressedFile);
        Assert.assertEquals(true, actual);

    }

    @Test
    public void testIfToCompressAndDecompressedFilesAreEqual_WhenCompressingTwoChars() throws IOException {
        //given
        String twoChars = "KK";
        toCompressFilePreparation(twoChars, fileToCompress);

        //when
        huffman.huffman(pathToRootDir, true);
        huffman.huffman(pathToRootDir, false);
        File decompressedFile = new File(pathToRootDir + "\\Decompressed.txt");
        //then
        boolean actual = testIfContentOfTwoFilesIsEqual(fileToCompress, decompressedFile);
        Assert.assertEquals(true, actual);

    }

    @Test
    public void testIfToCompressAndDecompressedFilesAreEqual_WhenCompressingThreeChars() throws IOException {
        //given
        String threeChars = "-=w";
        toCompressFilePreparation(threeChars, fileToCompress);

        //when
        huffman.huffman(pathToRootDir, true);
        huffman.huffman(pathToRootDir, false);
        File decompressedFile = new File(pathToRootDir + "\\Decompressed.txt");
        //then
        boolean actual = testIfContentOfTwoFilesIsEqual(fileToCompress, decompressedFile);
        Assert.assertEquals(true, actual);

    }

    @Test
    public void testIfToCompressAndDecompressedFilesAreEqual_WhenCompressingSameCharManyTimes() throws IOException {
        //given
        String textWithSameChar = "";
        for (int i = 0; i < 20_000; i++) {
            textWithSameChar += 'a';
        }
        toCompressFilePreparation(textWithSameChar, fileToCompress);
        File decompressed = new File(pathToRootDir + "\\Decompressed.txt");

        //when
        huffman.huffman(pathToRootDir, true);
        huffman.huffman(pathToRootDir, false);

        //then
        boolean actual = testIfContentOfTwoFilesIsEqual(fileToCompress, decompressed);
        Assert.assertEquals(true, actual);
    }

    @Test
    public void testIfToCompressAndDecompressedFilesAreEqual_WhenCompressingBigRandomString() throws IOException {
        //given
        String randomText = generateRandomString();
        toCompressFilePreparation(randomText, fileToCompress);

        //when
        huffman.huffman(pathToRootDir, true);
        huffman.huffman(pathToRootDir, false);
        File decompressedFile = new File(pathToRootDir + "\\Decompressed.txt");

        //then
        boolean actual = testIfContentOfTwoFilesIsEqual(fileToCompress, decompressedFile);
        Assert.assertEquals(true, actual);

    }

    @Test
    public void testIfToCompressAndDecompressedFilesAreEqual_WhenCompressingMultiLineString() throws IOException {
        //given
        String multiLineText = MultiLineString();
        toCompressFilePreparation(multiLineText, fileToCompress);

        //when
        huffman.huffman(pathToRootDir, true);
        huffman.huffman(pathToRootDir, false);
        File decompressedFile = new File(pathToRootDir + "\\Decompressed.txt");

        //then
        boolean actual = testIfContentOfTwoFilesIsEqual(fileToCompress, decompressedFile);
        Assert.assertEquals(true, actual);

    }

    @Test
    public void compareSizeOfCompressedAndDecompressedFileSize() throws IOException {
        //given
        String loremIpsum = LoremIpsumString();
        toCompressFilePreparation(loremIpsum, fileToCompress);
        File decompressed = new File(pathToRootDir + "\\Decompressed.txt");

        //when
        huffman.huffman(pathToRootDir, true);
        huffman.huffman(pathToRootDir, false);

        //then
        Assert.assertEquals(fileToCompress.length(), decompressed.length());

    }

}
