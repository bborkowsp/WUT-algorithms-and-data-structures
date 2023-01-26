package pl.edu.pw.ee;

import java.io.File;
import org.junit.Before;
import org.junit.Test;

public class HuffmanTest {

    private Huffman huffman;

    @Before
    public void setUp() {
        this.huffman = new Huffman();
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_ThrowException_WhenPathToRootDirIsNull() {
        //given
        String nullPathToRootDir = null;

        //when
        huffman.huffman(nullPathToRootDir, true);

        //then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_ThrowException_WhenWrongCompressedFileName() {
        //given
        String pathToTestRootDirWithWrongCompressedFileName = new File("src\\test\\IllegalTestFiles\\testFilesWithWrongNames\\WrongCompressedFileName").getAbsolutePath();

        //when
        huffman.huffman(pathToTestRootDirWithWrongCompressedFileName, false);

        //then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_ThrowException_WhenWrongTreeFileName() {
        //given
        String pathToTestRootDirWithWrongTreeFileName = new File("src\\test\\IllegalTestFiles\\testFilesWithWrongNames\\WrongTreeName").getAbsolutePath();

        //when
        huffman.huffman(pathToTestRootDirWithWrongTreeFileName, false);

        //then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_ThrowException_WhenWrongToCompressFileName() {
        //given
        String pathToTestRootDirWithWrongToCompressFileName = new File("src\\test\\IllegalTestFiles\\testFilesWithWrongNames\\WrongToCompressFileName").getAbsolutePath();

        //when
        huffman.huffman(pathToTestRootDirWithWrongToCompressFileName, true);

        //then
        assert false;
    }

}
