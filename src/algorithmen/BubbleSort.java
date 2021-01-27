package algorithmen;

import sortalgorithms.Algorithmus;

public class BubbleSort extends Algorithmus {

    public void internalSort() {
        while (!checkSort()) {
            for (int i = 0; i < daten.length - 1; i++) {
                if (compare(i, i + 1)) {
                    swap(i, (i + 1));
                }
            }
        }
    }
}
