package pl.edu.pw.ee;

public class PathElement {

    private final int columnNumber;
    private final int rowNumber;
    private final Direction direction;

    public PathElement(int rowNumber, int columnNumber, Direction direction) {
        this.rowNumber = rowNumber;
        this.columnNumber = columnNumber;
        this.direction = direction;
    }

    public int getColumnNumber() {
        return columnNumber;
    }

    public int getRowNumber() {
        return rowNumber;
    }

    public Direction getDirection() {
        return direction;
    }

}
