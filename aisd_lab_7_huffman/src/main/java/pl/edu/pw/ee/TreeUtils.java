package pl.edu.pw.ee;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TreeUtils {

    public static void writeTreeStructureToFile(int[] frequencyOfCharacters, String pathToRootDir) throws IOException {
        File treeFile = new File(pathToRootDir + "\\Tree.txt");
        try {
            treeFile.createNewFile();
        } catch (IOException e) {
            throw new IOException("Error, cannot create tree file!");
        }
        FileOutputStream treeFileOutputStream = null;
        ObjectOutputStream treeFileObjectOutputStream = null;
        try {
            treeFileOutputStream = new FileOutputStream(treeFile);
            treeFileObjectOutputStream = new ObjectOutputStream(treeFileOutputStream);
            treeFileObjectOutputStream.writeObject(frequencyOfCharacters);

        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("Error, the file to save the tree to was not found!");
        } catch (IOException e) {
            throw new IOException("Error, cannot write tree to file!");
        } finally {
            try {
                if (treeFileObjectOutputStream != null) {
                    treeFileObjectOutputStream.close();
                }
            } catch (IOException e) {
                throw new IOException("Error, cannot close the object writing to output stream of tree file!");
            }
            try {
                if (treeFileOutputStream != null) {
                    treeFileOutputStream.close();
                }
            } catch (IOException e) {
                throw new IOException("Error, cannot close the output stream of tree file!");
            }
        }
    }

    public static Node buildHuffmanTree(int[] frequencyOfCharacters) {
        List<Node> huffmanTree = new ArrayList<>();
        for (int i = 0; i < frequencyOfCharacters.length; i++) {
            if (frequencyOfCharacters[i] > 0) {
                int frequency = frequencyOfCharacters[i];
                char character = (char) i;
                huffmanTree.add(new Node(frequency, character));
            }
        }
        if (huffmanTree.isEmpty()) {
            throw new IllegalArgumentException("Error, file to compress is empty!");
        }
        if (huffmanTree.size() == 1) {
            huffmanTree.get(0).setCode("0");
        }
        while (huffmanTree.size() > 1) {
            combine(huffmanTree);
        }

        Node root = huffmanTree.get(0);
        return root;
    }

    public static int[] readFrequencyOfCharacters(File treeFile) throws IOException {
        int[] frequencyOfCharacters = null;
        FileInputStream treefileInputStream = null;
        ObjectInputStream treeFileobjectInputStream = null;

        try {
            treefileInputStream = new FileInputStream(treeFile);
            treeFileobjectInputStream = new ObjectInputStream(treefileInputStream);

            frequencyOfCharacters = (int[]) treeFileobjectInputStream.readObject();

        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("Error, tree file not found!");
        } catch (IOException e) {
            throw new IOException("Error, cannot read from tree file!");
        } catch (ClassNotFoundException e) {
            throw new IOException("Error, class not found in tree file!");
        } finally {
            try {
                if (treeFileobjectInputStream != null) {
                    treeFileobjectInputStream.close();
                }
            } catch (IOException e) {
                throw new IOException("Error, cannon close the object reading from input stream from tree file!");
            }
            try {
                if (treefileInputStream != null) {
                    treefileInputStream.close();
                }
            } catch (IOException e) {
                throw new IOException("Error, cannot close the Input stream from tree file!");
            }
        }
        return frequencyOfCharacters;
    }

    private static void combine(List<Node> huffmanTree) {
        /* If it is not true, it means that there is only one node in tree */
        if (huffmanTree.size() < 2) {
            return;
        }
        Collections.sort(huffmanTree);
        Node leftNode = huffmanTree.remove(0);
        Node rightNode = huffmanTree.remove(0);

        Node parentNode = new Node(leftNode.getFrequency() + rightNode.getFrequency(), leftNode, rightNode);
        huffmanTree.add(0, parentNode);
    }
}
