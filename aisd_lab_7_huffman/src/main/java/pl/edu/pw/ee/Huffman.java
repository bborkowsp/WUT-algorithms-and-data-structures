package pl.edu.pw.ee;

import java.io.File;
import java.io.IOException;
import static pl.edu.pw.ee.CompressFile.compressFile;
import static pl.edu.pw.ee.DecompressFile.decompressFile;

public class Huffman {

    public int huffman(String pathToRootDir, boolean compress) {
        if (pathToRootDir == null) {
            throw new IllegalArgumentException("Error, path to directory is null!");
        }
        if (compress) {
            File fileToCompress = new File(pathToRootDir + "\\ToCompress.txt");
            validatefileToCompress(fileToCompress);
            try {
                int numberOfBitsInCompressedFile = compressFile(fileToCompress, pathToRootDir);
                return numberOfBitsInCompressedFile;
            } catch (IOException e) {
                System.err.println("Error, cannot compress file!");
                System.exit(-1);
            }
        } else {
            File treeFile = new File(pathToRootDir + "\\Tree.txt");
            validateTreeFile(treeFile);
            File compressedFile = new File(pathToRootDir + "\\CompressedFile.txt");
            validateCompressedFile(compressedFile);
            try {
                int numberOfCharsInDecompressedFile = decompressFile(treeFile, compressedFile, pathToRootDir);
                return numberOfCharsInDecompressedFile;
            } catch (IOException e) {
                System.err.println("Error, cannot decompress file!");
                System.exit(-1);
            }

        }
        return 0;
    }

    private void validatefileToCompress(File fileToCompress) {
        if (!fileToCompress.exists()) {
            throw new IllegalArgumentException("Error, the file to be compressed does not exist!");
        }
    }

    private void validateTreeFile(File treeFile) {
        if (!treeFile.exists()) {
            throw new IllegalArgumentException("Error, file with tree does not exist!");
        }
    }

    private void validateCompressedFile(File CompressedFile) {
        if (!CompressedFile.exists()) {
            throw new IllegalArgumentException("Error, file that you want to decompress does not exist!");
        }
    }
}
