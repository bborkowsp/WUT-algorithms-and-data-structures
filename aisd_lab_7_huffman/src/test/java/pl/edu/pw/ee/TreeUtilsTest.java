package pl.edu.pw.ee;

import static UtilsForTests.UtilsForTests.toCompressFilePreparation;
import java.io.File;
import java.io.IOException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static pl.edu.pw.ee.TreeUtils.readFrequencyOfCharacters;

public class TreeUtilsTest {

    private Huffman huffman;
    private final String pathToRootDir = new File("src\\test\\testFiles").getAbsolutePath();

    private File treeFile;
    private File fileToCompress;

    @Before
    public void setUp() {
        this.huffman = new Huffman();
        this.treeFile = new File(pathToRootDir + "\\Tree.txt");
        this.fileToCompress = new File(pathToRootDir + "\\ToCompress.txt");
    }

    @Test
    public void should_CorrectlyReadFrequencyOfCharactersFromFile() throws IOException {
        //given
        String text = "+/XY\n+Z=\n\n+";
        toCompressFilePreparation(text, fileToCompress);

        //when
        huffman.huffman(pathToRootDir, true);

        //then
        int[] expectedFrequencyOfCharacters = new int[128];
        expectedFrequencyOfCharacters[(int) '+'] = 3;
        expectedFrequencyOfCharacters[(int) 'X'] = 1;
        expectedFrequencyOfCharacters[(int) 'Y'] = 1;
        expectedFrequencyOfCharacters[(int) 'Z'] = 1;
        expectedFrequencyOfCharacters[(int) '='] = 1;
        expectedFrequencyOfCharacters[(int) '/'] = 1;
        expectedFrequencyOfCharacters[(int) '\n'] = 3;
        int[] actualFrequencyOfCharacters = readFrequencyOfCharacters(treeFile);
        Assert.assertArrayEquals(expectedFrequencyOfCharacters, actualFrequencyOfCharacters);

    }

}
