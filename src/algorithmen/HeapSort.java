package algorithmen;

import sortalgorithms.Algorithmus;

public class HeapSort extends Algorithmus {

    protected void internalSort() {
        int n = daten.length;

        // Build heap (rearrange array)
        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(n, i);

        // One by one extract an element from heap
        for (int i = n - 1; i > 0; i--) {
            // Move current root to end
            swap(0, i);

            // call max heapify on the reduced heap
            heapify(i, 0);
        }
    }

    void heapify(int n, int i) {

        int largest = i; // Initialize largest as root
        int l = 2 * i + 1; // left = 2*i + 1
        int r = 2 * i + 2; // right = 2*i + 2

        // If left child is larger than root
        if (l < n && Algorithmus.compare(l, largest))
            largest = l;

        // If right child is larger than largest so far
        if (r < n && Algorithmus.compare(r, largest))
            largest = r;

        // If largest is not root
        if (largest != i) {
            swap(i, largest);

            // Recursively heapify the affected sub-tree
            heapify(n, largest);
        }
    }
}
