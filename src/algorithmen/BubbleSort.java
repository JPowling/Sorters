package algorithmen;

import sortalgorithms.Algorithmus;

public class BubbleSort extends Algorithmus {

    public void internalSort() {
        for (int end = daten.length - 1; end > 0; end--) {
            for (int i = 0; i < end; i++) {
                if (compare(i, i + 1)) {
                    swap(i, i + 1);
                }
            }
        }
    }
}
