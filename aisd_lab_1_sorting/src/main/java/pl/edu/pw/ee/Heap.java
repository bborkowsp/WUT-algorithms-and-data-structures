package pl.edu.pw.ee;

import java.util.List;
import pl.edu.pw.ee.services.HeapExtension;
import pl.edu.pw.ee.services.HeapInterface;

public class Heap<T extends Comparable<T>> implements HeapInterface<T>, HeapExtension {

    private List<T> data;
    private boolean isBuild;

    public Heap(List<T> data) {
        this.data = data;
        this.isBuild = false;
    }

    @Override
    public void put(T item) {
        if (item == null) {
            throw new IllegalArgumentException("Cannot put null item!");
        }
        if (isBuild) {
            data.add(item);
            heapUp();
        } else {
            throw new IllegalArgumentException("Heap is not built");
        }
    }

    @Override
    public T pop() {
        if (data.isEmpty()) {
            throw new ArrayIndexOutOfBoundsException("Heap is empty!");
        } else if (isBuild) {
            T elementToPop = data.get(0);
            data.set(0, data.get(data.size() - 1));
            data.remove(data.size() - 1);
            this.heapify(0, data.size());
            return elementToPop;
        } else {
            throw new IllegalArgumentException("Heap is not built!");
        }

    }

    @Override
    public void build() {
        isBuild = true;
        int n = data.size();

        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(i, n);
        }
    }

    @Override
    public void heapify(int startId, int endId) {
        int largestId = startId;
        int leftChild = 2 * largestId + 1;
        int rightChild = 2 * largestId + 2;
        if (leftChild < endId && data.get(leftChild).compareTo(data.get(largestId)) > 0) {
            largestId = leftChild;
        }
        if (rightChild < endId && data.get(rightChild).compareTo(data.get(largestId)) > 0) {
            largestId = rightChild;
        }

        if (largestId != startId) {
            swap(largestId, startId);
            heapify(largestId, endId);
        }
    }

    private void swap(int largest, int startId) {
        T temporary = data.get(startId);
        data.set(startId, data.get(largest));
        data.set(largest, temporary);
    }

    private void heapUp() {

        int childIndex = data.size() - 1;
        while (childIndex > 0) {

            int parentIndex = (childIndex - 1) / 2;
            if (data.get(parentIndex).compareTo(data.get(childIndex)) >= 0) {
                return;
            } else {
                swap(parentIndex, childIndex);
                childIndex = parentIndex;
            }

        }
    }
}
