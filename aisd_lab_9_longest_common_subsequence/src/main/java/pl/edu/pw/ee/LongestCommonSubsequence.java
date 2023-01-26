package pl.edu.pw.ee;

import java.util.ArrayList;
import java.util.List;
import static pl.edu.pw.ee.Direction.LEFT;
import static pl.edu.pw.ee.Direction.NO_DIRECTION;
import static pl.edu.pw.ee.Direction.UPPER;
import static pl.edu.pw.ee.Direction.UPPERLEFT;

class LongestCommonSubsequence {

    private final List<PathElement> path = new ArrayList<>();
    private MatrixElem[][] matrix;
    private final String topStr;
    private final String leftStr;

    public LongestCommonSubsequence(String topStr, String leftStr) {
        validateStrings(topStr, leftStr);
        this.topStr = topStr;
        this.leftStr = leftStr;
    }

    private void validateStrings(String topStr, String leftStr) {
        if (topStr == null || leftStr == null || "".equals(topStr) || "".equals(leftStr)) {
            throw new IllegalArgumentException("Illegal string values!");
        }
    }

    public String findLCS() {
        buildMatrix();
        int startRows = leftStr.length();
        int startCol = topStr.length();
        String result = "";
        while (startRows > 0 && startCol > 0) {
            if (matrix[startRows][startCol].getDirection() == UPPER) {
                path.add(new PathElement(startRows, startCol, UPPER));
                startRows--;
                continue;
            }
            if (matrix[startRows][startCol].getDirection() == LEFT) {
                path.add(new PathElement(startRows, startCol, LEFT));
                startCol--;
                continue;
            }
            if (matrix[startRows][startCol].getDirection() == UPPERLEFT) {
                path.add(new PathElement(startRows, startCol, UPPERLEFT));
                result += leftStr.charAt(startRows - 1);
                startCol--;
                startRows--;

            }
        }
        result = reverseResult(result);
        return result;
    }

    private void buildMatrix() {
        int numberOfColumns = topStr.length() + 1;
        int numberOfRows = leftStr.length() + 1;
        matrix = new MatrixElem[numberOfRows][numberOfColumns];
        for (int i = 0; i < numberOfColumns; i++) {
            matrix[0][i] = new MatrixElem(0, NO_DIRECTION);
        }
        for (int i = 0; i < numberOfRows; i++) {
            matrix[i][0] = new MatrixElem(0, NO_DIRECTION);
        }

        for (int col = 1; col < topStr.length() + 1; col++) {
            for (int row = 1; row < leftStr.length() + 1; row++) {
                if (leftStr.charAt(row - 1) == topStr.charAt(col - 1)) {
                    matrix[row][col] = new MatrixElem(matrix[row - 1][col - 1].getValue() + 1, UPPERLEFT);
                }
                if (leftStr.charAt(row - 1) != topStr.charAt(col - 1)) {
                    if (matrix[row - 1][col].getValue() >= matrix[row][col - 1].getValue()) {
                        matrix[row][col] = new MatrixElem(matrix[row - 1][col].getValue(), UPPER);
                    } else {
                        matrix[row][col] = new MatrixElem(matrix[row][col - 1].getValue(), LEFT);
                    }
                }
            }
        }
    }

    private String reverseResult(String result) {
        StringBuilder stringBuilder = new StringBuilder(result);
        stringBuilder.reverse();
        return stringBuilder.toString();
    }

    public void display() {
        validateDisplayConditions();
        printHorizontalSeparator();
        printVerticalSeparator();
        printTopStr();
        printVerticalSeparator();
        printHorizontalSeparator();
        printVerticalSeparator();
        printVerticalZeros();
        printVerticalSeparator();
        printHorizontalSeparator();
        for (int i = 1; i < leftStr.length() + 1; i++) {
            printVerticalSeparatorWithDirection(i);
            printCharAndMatrixElements(i);
            printVerticalSeparator();
            printHorizontalSeparator();
        }
    }

    private void validateDisplayConditions() {
        if (matrix == null) {
            buildMatrix();
        }
        if (path.isEmpty()) {
            findLCS();
        }
    }

    private void printHorizontalSeparator() {
        System.out.print("+-------");
        for (int i = 0; i < topStr.length() + 1; i++) {
            System.out.print("+-----");
        }
        System.out.print("+\n");
    }

    private void printVerticalSeparator() {
        System.out.print("|       ");
        for (int i = 0; i < topStr.length() + 1; i++) {
            System.out.print("|     ");
        }
        System.out.print("|\n");
    }

    private void printTopStr() {
        System.out.print("|       |     ");
        for (int i = 0; i < topStr.length(); i++) {
            char c = topStr.charAt(i);
            System.out.print(checkIfSpecialCharacterIsInTopStr(c));
        }
        System.out.print("|\n");
    }

    private void printVerticalZeros() {
        System.out.print("|       ");
        for (int i = 0; i < topStr.length() + 1; i++) {
            System.out.print("|  " + matrix[0][i].getValue() + "  ");
        }
        System.out.print("|\n");
    }

    private void printVerticalSeparatorWithDirection(int charIndex) {
        System.out.print("|       |     ");
        int row = charIndex;
        boolean flag = false;
        for (int i = 1; i < topStr.length() + 1; i++) {
            for (PathElement p : path) {
                if (row == p.getRowNumber() && i == p.getColumnNumber()) {
                    if (p.getDirection() == UPPER) {
                        System.out.print("|  ^  ");
                        flag = true;
                    }
                    if (p.getDirection() == UPPERLEFT) {
                        System.out.print("|\\    ");
                        flag = true;
                    }
                }
            }
            if (!flag) {
                System.out.print("|     ");
            }
            flag = false;
        }
        System.out.print("|\n");
    }

    private void printCharAndMatrixElements(int charIndex) {
        char c = leftStr.charAt(charIndex - 1);
        int row = charIndex;
        boolean flag = false;
        System.out.print(checkIfSpecialCharacterInLeftStr(c));
        for (int i = 1; i < topStr.length() + 1; i++) {
            for (PathElement p : path) {
                if (row == p.getRowNumber() && i == p.getColumnNumber()) {
                    if (p.getDirection() == LEFT) {
                        int value = matrix[row][i].getValue();
                        printValueWithDirection(value);
                        flag = true;
                    }
                }
            }
            if (!flag) {
                int value = matrix[row][i].getValue();
                printValueWithoutDirection(value);
            }
            flag = false;
        }
        System.out.print("|\n");
    }

    private void printValueWithDirection(int value) {
        if (value <= 9) {
            System.out.print("|< " + value + "  ");
        }
        if (value > 9 && value <= 99) {
            System.out.print("|< " + value + " ");
        }
        if (value > 99 && value <= 999) {
            System.out.print("|< " + value + "");
        }
        if (value > 999 && value <= 9999) {
            System.out.print("|<" + value + "");
        } else if (value > 9999) {
            throw new IllegalArgumentException("Value is to big to display!");
        }
    }

    private void printValueWithoutDirection(int value) {
        if (value <= 9) {
            System.out.print("|  " + value + "  ");
        }
        if (value > 9 && value <= 99) {
            System.out.print("|  " + value + " ");
        }
        if (value > 99 && value <= 999) {
            System.out.print("|  " + value + "");
        }
        if (value > 999 && value <= 9999) {
            System.out.print("| " + value + "");
        }
        if (value > 9999 && value <= 99999) {
            System.out.print("|" + value + "");
        } else if (value > 99999) {
            throw new IllegalArgumentException("Value is to big to display!");
        }
    }

    private String checkIfSpecialCharacterIsInTopStr(char c) {
        if (c == '\n') {
            return "| " + "\\n" + "  ";
        }
        if (c == '\t') {
            return "| " + "\\t" + "  ";
        }
        if (c == '\r') {
            return "| " + "\\r" + "  ";
        }
        if (c == '\b') {
            return "| " + "\\b" + "  ";
        }
        if (c == '\f') {
            return "| " + "\\f" + "  ";
        } else {
            return "|  " + c + "  ";
        }
    }

    private String checkIfSpecialCharacterInLeftStr(char c) {
        if (c == '\n') {
            return "|  " + "\\n" + "   |  0  ";
        }
        if (c == '\t') {
            return "|  " + "\\t" + "   |  0  ";
        }
        if (c == '\r') {
            return "|  " + "\\r" + "   |  0  ";
        }
        if (c == '\b') {
            return "|  " + "\\b" + "   |  0  ";
        }
        if (c == '\f') {
            return "|  " + "\\f" + "   |  0  ";
        } else {
            return "|   " + c + "   |  0  ";
        }
    }
    
}
