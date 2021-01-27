package sortalgorithms;

import algorithmen.BubbleSort;
import algorithmen.InsertionSort;
import algorithmen.SelectionSort;
import algorithmen.WorseBogoSort;

public class Start {
	public static void main(String[] args) {
		setup();
		new GUI_Sort();
	}

	private static void setup() {
		new BubbleSort();
		new InsertionSort();
		new SelectionSort();
		new WorseBogoSort();
	}
}
