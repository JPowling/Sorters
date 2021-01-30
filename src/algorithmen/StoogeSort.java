package algorithmen;

import sortalgorithms.Algorithmus;

public class StoogeSort extends Algorithmus {
    protected void internalSort() {
        stoogeSort(0, daten.length - 1);
    }

    private void stoogeSort(int firstIndex, int lastIndex) {
        System.out.println("firstIndex:\t" + firstIndex);
        System.out.println("lastIndex:\t" + lastIndex);

        if (firstIndex >= lastIndex)
            return;

        if (compare(firstIndex, lastIndex))
            swap(firstIndex, lastIndex);

        if ((lastIndex - firstIndex + 1) > 2) {
            int t = (lastIndex - firstIndex + 1) / 3;
            stoogeSort(firstIndex, lastIndex - t);
            stoogeSort(firstIndex + t, lastIndex);
            stoogeSort(firstIndex, lastIndex - t);
        }
    }
}
