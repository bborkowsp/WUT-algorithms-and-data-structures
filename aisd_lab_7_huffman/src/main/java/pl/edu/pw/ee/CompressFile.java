package pl.edu.pw.ee;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static pl.edu.pw.ee.TreeUtils.buildHuffmanTree;
import static pl.edu.pw.ee.TreeUtils.writeTreeStructureToFile;

public class CompressFile {

    public static int compressFile(File fileToCompress, String pathToRootDir) throws IOException {
        String initialString = "";
        int numberOfAsciiCharacters = 128;
        ToCompressFileUtils toCompressFileUtils = new ToCompressFileUtils(numberOfAsciiCharacters, initialString);

        toCompressFileUtils.readCharactersFromFileToCompress(fileToCompress);

        writeTreeStructureToFile(toCompressFileUtils.getFrequencyOfCharacters(), pathToRootDir);
        Node root = buildHuffmanTree(toCompressFileUtils.getFrequencyOfCharacters());

        List<CodedChar> codedChars = new ArrayList<>();
        root.encode(codedChars);

        toCompressFileUtils.characterEncoding(codedChars, fileToCompress);
        int numberOfEncodedBits = CompressedFileUtils.writeCodesToCompressedFile(codedChars, pathToRootDir, toCompressFileUtils);
        return numberOfEncodedBits;
    }

}
