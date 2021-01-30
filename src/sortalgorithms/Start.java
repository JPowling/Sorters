package sortalgorithms;

import algorithmen.*;

public class Start {

	public static void main(String[] args) {
		setup();
		new GUI_Sort();
	}

	private static void setup() {
		new BubbleSort();
		new InsertionSort();
		new HeapSort();
		new MiracleSort();
		new SelectionSort();
		new WorseBogoSort();
	}
}
