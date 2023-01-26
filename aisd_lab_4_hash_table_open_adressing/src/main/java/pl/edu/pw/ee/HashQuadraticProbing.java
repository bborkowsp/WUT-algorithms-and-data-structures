package pl.edu.pw.ee;

public class HashQuadraticProbing<T extends Comparable<T>> extends HashOpenAdressing<T> {

    private final double a;
    private final double b;

    public HashQuadraticProbing() {
        super();
        this.a = 0.5;
        this.b = 0.5;
    }

    public HashQuadraticProbing(int size, double a, double b) {
        super(size);
        validateConstants(a, b);
        this.a = a;
        this.b = b;
    }

    @Override
    int hashFunc(int key, int i) {
        int m = getSize();

        int hash = (int) ((key % m + a * i + b * i * i) % m);
        hash = validateHashValue(hash);
        hash = hash < 0 ? -hash : hash;

        return hash;
    }

    private void validateConstants(double a, double b) {
        if (Double.isInfinite(a) || Double.isNaN(a)
                || Double.isInfinite(b) || Double.isNaN(b) || b == 0.0) {
            throw new IllegalArgumentException("Invalid contants value");
        }
    }

    private static int validateHashValue(int hash) {
        if (hash == Integer.MIN_VALUE) {
            return ++hash;
        } else {
            return hash;
        }
    }
}
