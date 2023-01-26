package pl.edu.pw.ee;

import static UtilsForTests.UtilsForTests.MultiLineString;
import static UtilsForTests.UtilsForTests.generateRandomString;
import static UtilsForTests.UtilsForTests.toCompressFilePreparation;
import java.io.File;
import java.io.IOException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static UtilsForTests.UtilsForTests.expectedNumberOfEncodedBits;
import static UtilsForTests.UtilsForTests.LoremIpsumString;

public class CompressFileTest {

    private Huffman huffman;
    private final String pathToRootDir = new File("src\\test\\testFiles").getAbsolutePath();

    private File fileToCompress;

    @Before
    public void setUp() {
        this.huffman = new Huffman();
        this.fileToCompress = new File(pathToRootDir + "\\ToCompress.txt");
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_ThrowException_WhenCompressingNonAsciiCharacters() throws IOException {
        //given
        String text = "Pchnąć w tę łódź jeża lub ośm skrzyń fig";
        toCompressFilePreparation(text, fileToCompress);

        //when
        huffman.huffman(pathToRootDir, true);

        //then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_ThrowException_WhenCompressingEmptyFile() throws IOException {
        //given
        String emptyText = "";
        toCompressFilePreparation(emptyText, fileToCompress);

        //when
        huffman.huffman(pathToRootDir, true);

        //then
        assert false;
    }

    @Test
    public void should_ReturnCorrectNumberOfEncodedBits_WhenCompressingOneChar() throws IOException {
        //given
        String text = "A";
        toCompressFilePreparation(text, fileToCompress);

        //when
        int actual = huffman.huffman(pathToRootDir, true);

        //then
        int expected = expectedNumberOfEncodedBits(fileToCompress);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void should_ReturnCorrectNumberOfEncodedBits_WhenCompressingTwoChars() throws IOException {
        //given
        String text = ".0";
        toCompressFilePreparation(text, fileToCompress);

        //when
        int actual = huffman.huffman(pathToRootDir, true);

        //then
        int expected = expectedNumberOfEncodedBits(fileToCompress);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void should_ReturnCorrectNumberOfEncodedBits_WhenCompressingThreeChars() throws IOException {
        //given
        String text = "-A0";
        toCompressFilePreparation(text, fileToCompress);

        //when
        int actual = huffman.huffman(pathToRootDir, true);

        //then
        int expected = expectedNumberOfEncodedBits(fileToCompress);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void should_ReturnCorrectNumberOfEncodedBits_WhenCompressingSomeChars() throws IOException {
        //given
        String text = "NIEMANIE";
        toCompressFilePreparation(text, fileToCompress);

        //when
        int actual = huffman.huffman(pathToRootDir, true);

        //then
        int expected = expectedNumberOfEncodedBits(fileToCompress);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void should_ReturnCorrectNumberOfEncodedBits_WhenCompressingSameCharManyTimes() throws IOException {
        //given
        String text = "";
        for (int i = 0; i < 20_000; i++) {
            text += '\t';
        }
        toCompressFilePreparation(text, fileToCompress);

        //when
        int actual = huffman.huffman(pathToRootDir, true);

        //then
        int expected = expectedNumberOfEncodedBits(fileToCompress);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void should_ReturnCorrectNumberOfEncodedBits_WhenCompressingBigRandomString() throws IOException {
        //given
        String text = generateRandomString();
        toCompressFilePreparation(text, fileToCompress);

        //when
        int actual = huffman.huffman(pathToRootDir, true);

        //then
        int expected = expectedNumberOfEncodedBits(fileToCompress);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void should_ReturnCorrectNumberOfEncodedBits_WhenCompressingMultiLineString() throws IOException {
        //given
        String text = MultiLineString();
        toCompressFilePreparation(text, fileToCompress);

        //when
        int actual = huffman.huffman(pathToRootDir, true);

        //then
        int expected = expectedNumberOfEncodedBits(fileToCompress);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void should_ReturnCorrectNumberOfEncodedBits_WhenCompressingLoremIpsumString() throws IOException {
        //given
        String text = LoremIpsumString();
        toCompressFilePreparation(text, fileToCompress);

        //when
        int actual = huffman.huffman(pathToRootDir, true);

        //then
        int expected = expectedNumberOfEncodedBits(fileToCompress);
        Assert.assertEquals(expected, actual);
    }

}
