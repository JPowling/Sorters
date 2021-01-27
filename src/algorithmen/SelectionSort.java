package algorithmen;

import sortalgorithms.Algorithmus;

public class SelectionSort extends Algorithmus {
    public SelectionSort() {
        super("SelectionSort");
        super.addAlgorithmus(this);
    }

    public void internalSort() {
        int smallest;
        for (int first = 0; first < daten.length; first++) {
            smallest = first;
            for (int current = first + 1; current < daten.length; current++) {
                if (compare(smallest, current)) {
                    smallest = current;
                }
            }
            swap(first, smallest);
        }
    }
}
