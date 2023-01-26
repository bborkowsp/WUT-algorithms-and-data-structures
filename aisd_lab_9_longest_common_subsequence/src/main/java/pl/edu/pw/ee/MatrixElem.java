package pl.edu.pw.ee;

public class MatrixElem {

    private final int value;
    private final Direction direction;

    public MatrixElem(int value, Direction direction) {
        this.value = value;
        this.direction = direction;
    }

    public int getValue() {
        return value;
    }

    public Direction getDirection() {
        return direction;
    }

}
