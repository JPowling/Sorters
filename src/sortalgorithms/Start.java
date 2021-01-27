package sortalgorithms;

import algorithmen.BubbleSort;
import algorithmen.InsertionSort;
import algorithmen.SelectionSort;
import algorithmen.WorseBogoSort;

public class Start {

	public static void main(String[] args) {
		new WorseBogoSort();
		new BubbleSort();
		new InsertionSort();
		new SelectionSort();
		new GUI_Sort();
	}

}
