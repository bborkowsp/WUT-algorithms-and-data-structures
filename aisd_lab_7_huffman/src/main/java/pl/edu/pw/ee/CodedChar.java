package pl.edu.pw.ee;

public class CodedChar {

    private final char character;
    private final String code;

    public CodedChar(char character, String code) {
        this.character = character;
        this.code = code;
    }

    public char getCharacter() {
        return character;
    }

    public String getCode() {
        return code;
    }

}
