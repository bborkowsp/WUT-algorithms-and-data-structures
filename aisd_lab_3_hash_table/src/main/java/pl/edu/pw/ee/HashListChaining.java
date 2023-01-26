package pl.edu.pw.ee;

import java.util.ArrayList;
import java.util.List;
import pl.edu.pw.ee.services.HashTable;

public class HashListChaining<T extends Comparable<T>> implements HashTable<T> {

    private final Elem<T> nil = null;
    private List<Elem<T>> hashElems;
    private int nElem;

    private class Elem<T> {

        private T value;
        private Elem<T> next;

        Elem(T value, Elem<T> nextElem) {
            this.value = value;
            this.next = nextElem;
        }
    }

    public HashListChaining(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Size should be bigger than zero");
        }

        hashElems = new ArrayList<>();
        initializeHash(size);
    }

    @Override
    public void delete(T value) {
        if (value == null) {
            throw new IllegalArgumentException("Cannot delete null value!");
        }
        int hashCode = value.hashCode();
        int hashId = countHashId(hashCode);

        Elem<T> elem = hashElems.get(hashId);
        Elem<T> parentElem = hashElems.get(hashId);

        while (elem != nil && !(elem.value.compareTo(value) == 0)) {
            parentElem = elem;
            elem = elem.next;
        }
        if (elem != nil) {
            if (parentElem != elem) {
                if (elem.next != nil) {
                    elem = parentElem;
                    elem.next = elem.next.next;
                    nElem--;
                } else {
                    parentElem.next = nil;
                    nElem--;
                }
            } else {
                hashElems.set(hashId, elem.next);
                nElem--;
            }
        } else {
            throw new IllegalArgumentException("Element is not not on the list!");
        }
    }

    @Override
    public void add(T value) {
        if (value == null) {
            throw new IllegalArgumentException("Cannot add null value!");
        }
        int hashCode = value.hashCode();
        int hashId = countHashId(hashCode);

        Elem<T> oldElem = hashElems.get(hashId);

        while (oldElem != nil && !(oldElem.value.compareTo(value) == 0)) {
            oldElem = oldElem.next;
        }
        if (oldElem != nil) {
            oldElem.value = value;
        } else {
            hashElems.set(hashId, new Elem(value, hashElems.get(hashId)));
            nElem++;
        }
    }

    @Override
    public T get(T value) {
        if (value == null) {
            throw new IllegalArgumentException("Cannot get null value!");
        }
        int hashCode = value.hashCode();
        int hashId = countHashId(hashCode);

        Elem<T> elem = hashElems.get(hashId);

        while (elem != nil && !(elem.value.compareTo(value) == 0)) {
            elem = elem.next;
        }
        return elem != nil ? elem.value : null;
    }

    public double countLoadFactor() {
        double size = hashElems.size();
        return nElem / size;
    }

    public int getnElem() {
        return nElem;
    }

    private void initializeHash(int size) {

        for (int i = 0; i < size; i++) {
            hashElems.add(i, nil);
        }
    }

    private int countHashId(int hashCode) {
        int n = hashElems.size();

        if (hashCode != Integer.MIN_VALUE) {
            return Math.abs(hashCode) % n;
        } else {
            return (Integer.MIN_VALUE * (-1)) % n;
        }
    }

}
