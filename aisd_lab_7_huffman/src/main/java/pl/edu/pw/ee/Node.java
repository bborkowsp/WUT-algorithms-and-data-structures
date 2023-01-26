package pl.edu.pw.ee;

import java.io.Serializable;
import java.util.List;

public class Node implements Comparable<Node>, Serializable {

    private final int frequency;
    private char character;
    private Node leftNode;
    private Node rightNode;
    private String code = "";

    public Node(int frequency, char character) {
        validateFrequency(frequency);
        validateCharacter(character);
        this.frequency = frequency;
        this.character = character;
    }

    public Node(int frequency, Node leftNode, Node rightNode) {
        validateFrequency(frequency);
        this.frequency = frequency;
        this.leftNode = leftNode;
        this.rightNode = rightNode;
    }

    public int getFrequency() {
        return frequency;
    }

    public Node getLeftNode() {
        return leftNode;
    }

    public Node getRightNode() {
        return rightNode;
    }

    public char getCharacter() {
        return character;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public int compareTo(Node o) {
        return Integer.compare(this.frequency, o.frequency);
    }

    private void validateFrequency(int frequency) {
        if (frequency < 1) {
            throw new IllegalArgumentException("Error, frequency cannot be lower than 1!");
        }
    }

    private void validateCharacter(char character) {
        if ((int) character > 127) {
            throw new IllegalArgumentException("Error, character is not in Ascii table!");
        }
    }

    public void encode(List<CodedChar> codedChars) {
        if (this.leftNode != null && this.rightNode != null) {
            this.leftNode.setCode(this.code + "0");
            this.leftNode.encode(codedChars);
            this.rightNode.setCode(this.code + "1");
            this.rightNode.encode(codedChars);
        } else {
            CodedChar codedChar = new CodedChar(this.character, this.code);
            codedChars.add(codedChar);
        }
    }
}
