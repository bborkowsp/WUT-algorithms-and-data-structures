package pl.edu.pw.ee;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import static pl.edu.pw.ee.CompressedFileUtils.readCompressedFile;
import static pl.edu.pw.ee.TreeUtils.buildHuffmanTree;
import static pl.edu.pw.ee.TreeUtils.readFrequencyOfCharacters;

public class DecompressFile {

    public static int decompressFile(File treeFile, File compressedFile, String pathToRootDir) throws IOException {
        int[] frequencyOfCharacters = readFrequencyOfCharacters(treeFile);
        Node root = buildHuffmanTree(frequencyOfCharacters);
        int numberOfCharsInDecompressedFile = readCompressedFile(root, compressedFile, pathToRootDir);
        return numberOfCharsInDecompressedFile;
    }

    public static void writeTextToDecompressedFile(String decompressedString, String pathToRootDir) throws IOException {
        File decompressedFile = new File(pathToRootDir + "\\Decompressed.txt");
        try {
            decompressedFile.createNewFile();
        } catch (IOException e) {
            throw new IOException("Error, cannot create compressed File!");
        }
        BufferedOutputStream bufferedOutputStream = null;
        FileOutputStream fileOutputStream = null;

        try {
            fileOutputStream = new FileOutputStream(decompressedFile);
            bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
            byte[] bytes = decompressedString.getBytes();
            bufferedOutputStream.write(bytes);
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("Error, the file that you want to write decompressed file was not found!");
        } catch (IOException e) {
            throw new IOException("Error, cannot write decompressed String to file!");
        } finally {
            try {
                if (bufferedOutputStream != null) {
                    bufferedOutputStream.close();
                }
            } catch (IOException e) {
                throw new IOException("Error, cannot close the object writing to decompressed file!");
            }
            try {
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
            } catch (IOException e) {
                throw new IOException("Error, cannot close the output stream of decompressed file!");
            }

        }

    }
}
