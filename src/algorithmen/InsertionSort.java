package algorithmen;

import sortalgorithms.Algorithmus;

public class InsertionSort extends Algorithmus {
	protected void internalSort() {
		for (int first = 0; first < daten.length - 1; first++) {
			int next = first + 1;
			if (compare(first, next)) {
				swap(first, next);
				int current = first;
				while (current > 0 && compare(current - 1, current)) {
					swap(current, current - 1);
					current--;
				}
			}
		}
	}
}
