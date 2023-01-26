package pl.edu.pw.ee;

import static UtilsForTests.UtilsForTests.preparationForAllCodesTesting;
import static UtilsForTests.UtilsForTests.toCompressFilePreparation;
import java.io.File;
import java.io.IOException;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ToCompressFileUtilsTest {

    private final String pathToRootDir = new File("src\\test\\testFiles").getAbsolutePath();
    private ToCompressFileUtils toCompressFileUtils;
    private File fileToCompress;

    @Before
    public void setUp() {
        this.fileToCompress = new File(pathToRootDir + "\\ToCompress.txt");
        this.toCompressFileUtils = new ToCompressFileUtils(128, "");
    }

    @Test
    public void should_CorrectlyCreateToCompressFileUtilsObject() {
        //given
        String initialString = "";
        int numberOfAsciiCharacters = 128;

        //when
        //then
        String expectedAllCodes = initialString;
        int[] expectedFrequencyOfCharacters = new int[numberOfAsciiCharacters];

        String actualAllCodes = toCompressFileUtils.getAllCodes();
        int[] actualfrequencyOfCharacters = toCompressFileUtils.getFrequencyOfCharacters();

        Assert.assertEquals(expectedAllCodes, actualAllCodes);
        Assert.assertArrayEquals(expectedFrequencyOfCharacters, actualfrequencyOfCharacters);
    }

    @Test
    /* readCharactersFromFileToCompress test */
    public void should_CorrectlyMakeFrequencyOfCharactersAndAllCodes_WhenCompressingOneChar() throws IOException {
        //given
        String text = "-";
        toCompressFilePreparation(text, fileToCompress);

        //when
        toCompressFileUtils.readCharactersFromFileToCompress(fileToCompress);

        //then
        int[] expectedFrequencyOfCharacters = new int[128];
        expectedFrequencyOfCharacters[(int) '-'] = 1;
        int[] actualfrequencyOfCharacters = toCompressFileUtils.getFrequencyOfCharacters();
        Assert.assertArrayEquals(expectedFrequencyOfCharacters, actualfrequencyOfCharacters);
    }

    @Test
    /* readCharactersFromFileToCompress test */
    public void should_CorrectlyMakeFrequencyOfCharactersArray_WhenCompressingMultipleLineString() throws IOException {
        //given
        String text = "A B\n"
                + "CC\nA A"
                + "A A\n\n"
                + "A\nB\tC\t"
                + "\t";
        toCompressFilePreparation(text, fileToCompress);

        //when
        toCompressFileUtils.readCharactersFromFileToCompress(fileToCompress);

        //then
        int[] expectedFrequencyOfCharacters = new int[128];
        expectedFrequencyOfCharacters[(int) '\n'] = 5;
        expectedFrequencyOfCharacters[(int) '\t'] = 3;
        expectedFrequencyOfCharacters[(int) 'A'] = 6;
        expectedFrequencyOfCharacters[(int) 'B'] = 2;
        expectedFrequencyOfCharacters[(int) 'C'] = 3;
        expectedFrequencyOfCharacters[(int) ' '] = 3;
        int[] actualfrequencyOfCharacters = toCompressFileUtils.getFrequencyOfCharacters();
        Assert.assertArrayEquals(expectedFrequencyOfCharacters, actualfrequencyOfCharacters);
    }

    @Test
    /* readCharactersFromFileToCompress test */
    public void should_CorrectlyMakeFrequencyOfCharactersArray() throws IOException {
        //given
        String text = "NIEMANIE";
        toCompressFilePreparation(text, fileToCompress);

        //when
        toCompressFileUtils.readCharactersFromFileToCompress(fileToCompress);

        //then
        int[] expectedFrequencyOfCharacters = new int[128];
        expectedFrequencyOfCharacters[(int) 'N'] = 2;
        expectedFrequencyOfCharacters[(int) 'I'] = 2;
        expectedFrequencyOfCharacters[(int) 'E'] = 2;
        expectedFrequencyOfCharacters[(int) 'M'] = 1;
        expectedFrequencyOfCharacters[(int) 'A'] = 1;
        int[] actualfrequencyOfCharacters = toCompressFileUtils.getFrequencyOfCharacters();
        Assert.assertArrayEquals(expectedFrequencyOfCharacters, actualfrequencyOfCharacters);
    }

    @Test
    /* characterEncoding test */
    public void should_CorrectlyMakeAllCodes_WhenCompressingOneChar() throws IOException {
        //given
        String text = "A";
        toCompressFilePreparation(text, fileToCompress);

        //when
        List<CodedChar> codedChars = preparationForAllCodesTesting(fileToCompress, toCompressFileUtils);
        toCompressFileUtils.characterEncoding(codedChars, fileToCompress);

        //then
        String expectedAllCodes = "0";
        String actualAllCodes = toCompressFileUtils.getAllCodes();
        Assert.assertEquals(expectedAllCodes, actualAllCodes);
    }

    @Test
    /* characterEncoding test */
    public void should_CorrectlyMakeAllCodes_WhenCompressingTwoChars() throws IOException {
        //given
        String text = "8x";
        toCompressFilePreparation(text, fileToCompress);

        //when
        List<CodedChar> codedChars = preparationForAllCodesTesting(fileToCompress, toCompressFileUtils);
        toCompressFileUtils.characterEncoding(codedChars, fileToCompress);

        //then
        String expectedAllCodes = "01";
        String actualAllCodes = toCompressFileUtils.getAllCodes();
        Assert.assertEquals(expectedAllCodes, actualAllCodes);
    }

    @Test
    /* characterEncoding test */
    public void should_CorrectlyMakeAllCodes_WhenCompressingThreeChars() throws IOException {
        //given
        String text = "/8x";
        toCompressFilePreparation(text, fileToCompress);

        //when
        List<CodedChar> codedChars = preparationForAllCodesTesting(fileToCompress, toCompressFileUtils);
        toCompressFileUtils.characterEncoding(codedChars, fileToCompress);

        //then
        String expectedAllCodes = "10110";
        String actualAllCodes = toCompressFileUtils.getAllCodes();
        Assert.assertEquals(expectedAllCodes, actualAllCodes);
    }

    @Test
    /* characterEncoding test */
    public void should_CorrectlyMakeAllCodes_WhenCompressing() throws IOException {
        //given
        String text = "NIEMANIE";
        toCompressFilePreparation(text, fileToCompress);

        //when
        List<CodedChar> codedChars = preparationForAllCodesTesting(fileToCompress, toCompressFileUtils);
        toCompressFileUtils.characterEncoding(codedChars, fileToCompress);

        //then
        String expectedAllCodes = "010011101100010011";
        String actualAllCodes = toCompressFileUtils.getAllCodes();
        Assert.assertEquals(expectedAllCodes, actualAllCodes);
    }

}
