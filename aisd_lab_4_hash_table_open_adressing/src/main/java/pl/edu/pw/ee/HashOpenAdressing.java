package pl.edu.pw.ee;

import pl.edu.pw.ee.services.HashTable;

public abstract class HashOpenAdressing<T extends Comparable<T>> implements HashTable<T> {

    private final T nil = null;
    private int size;
    private int nElems;
    private T[] hashElems;
    private final double correctLoadFactor;
    private final Deleted deleted;

    HashOpenAdressing() {
        this(2039); // initial size as random prime number
    }

    HashOpenAdressing(int size) {
        validateHashInitSize(size);

        this.size = size;
        this.hashElems = (T[]) new Comparable[this.size];
        this.correctLoadFactor = 0.75;
        this.deleted = new Deleted();
    }

    private class Deleted implements Comparable<T> {

        public Deleted() {
        }

        @Override
        public int compareTo(T o) {
            return 0;
        }
    }

    @Override
    public void put(T newElem) {
        validateInputElem(newElem);
        resizeIfNeeded();

        int key = newElem.hashCode();
        int i = 0;
        int hashId = hashFunc(key, i);

        while (hashElems[hashId] != nil && hashElems[hashId] != deleted) {
            if (hashElems[hashId].equals(newElem)) {
                hashElems[hashId] = newElem;
                return;
            }
            if (i == size - 1) {
                doubleResize();
            }
            i = (i + 1) % size;
            hashId = hashFunc(key, i);
        }
        if (hashElems[hashId] == deleted && newElem.equals(this.get(newElem))) {
            return;
        }
        hashElems[hashId] = newElem;
        nElems++;
    }

    @Override
    public T get(T elem) {
        validateInputElem(elem);

        for (T hashElem : hashElems) {
            if (elem.equals(hashElem)) {
                return hashElem;
            }
        }

        return null;
    }

    @Override
    public void delete(T elem) {
        validateInputElem(elem);

        for (int i = 0; i < hashElems.length; i++) {
            if (hashElems[i] != nil) {
                if (hashElems[i].equals(elem)) {
                    hashElems[i] = (T) deleted;
                    nElems--;
                }
            }
        }
    }

    private void validateHashInitSize(int initialSize) {
        if (initialSize < 1) {
            throw new IllegalArgumentException("Initial size of hash table cannot be lower than 1!");
        }
    }

    private void validateInputElem(T newElem) {
        if (newElem == null) {
            throw new IllegalArgumentException("Input elem cannot be null!");
        }
    }

    abstract int hashFunc(int key, int i);

    int getSize() {
        return size;
    }

    private void resizeIfNeeded() {
        double loadFactor = countLoadFactor();

        if (loadFactor >= correctLoadFactor) {
            doubleResize();
        }
    }

    private double countLoadFactor() {
        return (double) nElems / size;
    }

    private void doubleResize() {
        int oldSize = this.size;
        this.size *= 2;
        T[] newHashElems = (T[]) new Comparable[this.size];

        for (int i = 0; i < oldSize; i++) {
            if (i < hashElems.length) {
                if (hashElems[i] != nil) {
                    T elemToTransfer = hashElems[i];
                    int key = elemToTransfer.hashCode();
                    int j = 0;
                    int hashId = hashFunc(key, j);

                    while (newHashElems[hashId] != nil) {
                        if (j == size - 1) {
                            doubleResize();
                            return;
                        }
                        j = (j + 1) % size;
                        hashId = hashFunc(key, j);
                    }
                    newHashElems[hashId] = elemToTransfer;
                }
            }
        }
        hashElems = newHashElems;
    }
}
