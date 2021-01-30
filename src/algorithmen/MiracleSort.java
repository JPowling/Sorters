package algorithmen;

import sortalgorithms.Algorithmus;

public class MiracleSort extends Algorithmus {

    @Override
    protected void internalSort() {
        while (!Algorithmus.checkSort()) {
            // do nothing
        }
    }
}
