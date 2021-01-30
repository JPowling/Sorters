package sortalgorithms;

import algorithmen.*;

public class Start {

	public static void main(String[] args) {
		setup();
		new GUI_Sort();
	}

	private static void setup() {
		//Konstruktoren der Algorithmen in irgendeiner Reihenfolge aufrufen:
		new MiracleSort();
		new SelectionSort();
		new InsertionSort();
		new WorseBogoSort();
		new StoogeSort();
		new BubbleSort();
		new HeapSort();
	}
}
