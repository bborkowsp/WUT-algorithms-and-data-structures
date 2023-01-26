package pl.edu.pw.ee;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import static pl.edu.pw.ee.DecompressFile.writeTextToDecompressedFile;

public class CompressedFileUtils {

    public static int readCompressedFile(Node root, File compressedFile, String pathToRootDir) throws FileNotFoundException, IOException {
        List<CodedChar> codedChars = new ArrayList<>();

        root.encode(codedChars);
        String decompressedString = "";
        DataInputStream dataInputStream = null;
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(compressedFile);
            dataInputStream = new DataInputStream(fileInputStream);
            Path path = Paths.get(compressedFile.getAbsolutePath());
            int numberOfEncodedBits = dataInputStream.readInt();
            byte[] allBytes = Files.readAllBytes(path);
            String allCodes = "";
            int startOfEncodedCharByte = 4;/* 4 is the number of bytes spent to store the number of encoded chars in bit form in a compressed file */

            for (int i = startOfEncodedCharByte; i < allBytes.length; i++) {
                int bits = allBytes[i];
                String s = String.format("%8s", Integer.toBinaryString(bits & 0xFF)).replace(' ', '0');
                allCodes += s;
            }

            String characterCode = "";
            for (int i = 0; i < numberOfEncodedBits; i++) {
                char singleBit = allCodes.charAt(i);
                characterCode += singleBit;
                for (int j = 0; j < codedChars.size(); j++) {
                    if (characterCode.equals(codedChars.get(j).getCode())) {
                        decompressedString += codedChars.get(j).getCharacter();
                        characterCode = "";
                        break;
                    }
                }

            }
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("Error, the file that you want to decompress was not found!");
        } catch (IOException e) {
            throw new IOException("Error, connot read from compressed file!");
        } finally {
            try {
                if (dataInputStream != null) {
                    dataInputStream.close();
                }
            } catch (IOException e) {
                throw new IOException("Error, cannot close the object reading from compressed file!");
            }
            try {
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
            } catch (IOException e) {
                throw new IOException("Error, cannot close the input stream of compressed file!");
            }

        }

        writeTextToDecompressedFile(decompressedString, pathToRootDir);

        return decompressedString.length();
    }

    public static int writeCodesToCompressedFile(List<CodedChar> codedChars, String pathToRootDir, ToCompressFileUtils readToCompressFile) throws IOException {
        File compressedFile = new File(pathToRootDir + "\\CompressedFile.txt");
        int numberOfEncodedBits = 0;
        try {
            compressedFile.createNewFile();
        } catch (IOException e) {
            throw new IOException("Error, cannot create the compressed file!");
        }

        DataOutputStream dataOutputStream = null;
        FileOutputStream fileOutputStream = null;
        int numberOfBitsInCompressedFile = 0;
        try {
            fileOutputStream = new FileOutputStream(compressedFile);
            dataOutputStream = new DataOutputStream(fileOutputStream);
            String allCodes = readToCompressFile.getAllCodes();
            numberOfEncodedBits = allCodes.length();
            dataOutputStream.writeInt(numberOfEncodedBits);
            int numberOfBitsInByte = 8;

            for (int i = 0; i < allCodes.length(); i += numberOfBitsInByte) {
                if (i + numberOfBitsInByte < allCodes.length()) {
                    String eightBitSubString = allCodes.substring(i, i + numberOfBitsInByte);
                    byte bits = (byte) Integer.parseInt(eightBitSubString, 2);
                    dataOutputStream.write(bits);
                } else {
                    String remainingPart = allCodes.substring(i, i + (allCodes.length() - i));
                    int missingBitNumber = numberOfBitsInByte - remainingPart.length();
                    for (int j = 0; j < missingBitNumber; j++) {
                        remainingPart += "0";
                    }
                    byte bits = (byte) Integer.parseInt(remainingPart, 2);
                    dataOutputStream.write(bits);
                }

            }
            numberOfBitsInCompressedFile = dataOutputStream.size() * 8;
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("Error, compressed file not found!");
        } catch (IOException e) {
            throw new IOException("Error, connot write to compressed file!");
        } finally {
            try {
                if (dataOutputStream != null) {
                    dataOutputStream.close();
                }
            } catch (IOException e) {
                throw new IOException("Error, cannot close the object writing to compressed file!");
            }
            try {
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
            } catch (IOException e) {
                throw new IOException("Error, cannot close the output stream of compressed file!");
            }

        }
        return numberOfBitsInCompressedFile;
    }
}
