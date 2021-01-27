package algorithmen;

import sortalgorithms.Algorithmus;

import java.util.Random;

public class BogoSort extends Algorithmus {
    private final Random rnd = new Random();

    public BogoSort() {
        super("BogoSort");
    }

    protected void internalSort() {
        while (!checkSort()) {
            swap(rnd.nextInt(daten.length), rnd.nextInt(daten.length));
        }
    }
}
