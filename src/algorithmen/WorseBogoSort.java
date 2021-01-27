package algorithmen;

import sortalgorithms.Algorithmus;

import java.util.Random;

public class WorseBogoSort extends Algorithmus {

    private final Random rnd = new Random();

    protected void internalSort() {
        while (!checkSort()) {
            swap(rnd.nextInt(daten.length), rnd.nextInt(daten.length));
        }
    }
}
