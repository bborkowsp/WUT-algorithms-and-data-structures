package pl.edu.pw.ee;

public class HashDoubleHashing<T extends Comparable<T>> extends HashOpenAdressing<T> {

    public HashDoubleHashing() {
        super();
    }

    public HashDoubleHashing(int size) {
        super(size);
    }

    @Override
    int hashFunc(int key, int i) {
        int m = getSize();
        if (m == 3) {
            throw new IllegalArgumentException("Improper hash size (undefined operation)");
        }
        int f = key % m;
        int g = (1 + Math.abs(key % (m - 3))); // If key <= 0 , then g is mostly <=0

        int hash = (f + i * g) % m;
        hash = validateHashValue(hash);
        hash = hash < 0 ? -hash : hash;

        return hash;
    }

    private static int validateHashValue(int hash) {
        if (hash == Integer.MIN_VALUE) {
            return ++hash;
        } else {
            return hash;
        }
    }

}
