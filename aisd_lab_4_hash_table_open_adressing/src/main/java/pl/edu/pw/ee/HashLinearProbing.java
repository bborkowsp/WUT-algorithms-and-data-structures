package pl.edu.pw.ee;

public class HashLinearProbing<T extends Comparable<T>> extends HashOpenAdressing<T> {

    public HashLinearProbing() {
        super();
    }

    public HashLinearProbing(int size) {
        super(size);
    }

    @Override
    int hashFunc(int key, int i) {
        int m = getSize();

        int hash = (key % m + i) % m;
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
