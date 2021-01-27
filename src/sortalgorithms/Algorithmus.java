package sortalgorithms;

import java.util.*;

public abstract class Algorithmus {

    private static final Random rnd = new Random();
    private static final ArrayList<Algorithmus> algoList = new ArrayList<>();
    private static int delay = 5;
    public static int[] daten = new int[1];
    protected static final List<Integer> comparedElements = new ArrayList<>();
    protected static final List<Integer> swappedElements = new ArrayList<>();

    private static int numTausch;
    private static int numVergl;
    private String name;


    public Algorithmus() {
        name = this.getClass().getSimpleName();
        addAlgorithmus(this);
    }

    public static void fillDaten(int max) {
        int a;
        daten = new int[max];
        boolean[] used = new boolean[max];
        for (int i = 0; i < max; i++) {
            used[i] = false;
        }
        for (int i = 0; i < max; i++) {
            while (true) {
                a = rnd.nextInt(max);
                if (!used[a]) {
                    used[a] = true;
                    daten[i] = a;
                    break;
                }
            }
        }
    }

    public static Algorithmus getAlgorithmus(int a) {
        return algoList.get(a);
    }

    public static int getAlgoSize() {
        return algoList.size();
    }

    public static List<Integer> getComparedElements() {
        return comparedElements;
    }

    public static List<Integer> getSwappedElements() {
        return swappedElements;
    }

    protected abstract void internalSort();

    public void sort() {
        System.out.println("Sorting with " + name + "...");
        internalSort();
        System.out.println("Sorted");
    }

    public void swap(int i1, int i2) {
        swappedElements.clear();
        swappedElements.add(i1);
        swappedElements.add(i2);

        int zS = daten[i1];
        daten[i1] = daten[i2];
        daten[i2] = zS;
        numTausch++;

        if (delay == 0)
            return;

        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param i1 first Index
     * @param i2 second Index
     * @return true wenn i1 größer als i2
     */
    public boolean compare(int i1, int i2) {
        comparedElements.clear();
        comparedElements.add(i1);
        comparedElements.add(i2);

        numVergl++;
        return daten[i1] > daten[i2];
    }

    public boolean checkSort() {
        for (int i = 0; i < daten.length - 1; i++) {
            if (daten[i] > daten[i + 1]) {
                return false;
            }
        }
        return true;
    }

    public void addAlgorithmus(Algorithmus algo) {
        algoList.add(algo);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void resetNumTausch() {
        numTausch = 0;
    }

    public int getNumTausch() {
        return numTausch;
    }

    public void incTausch() { //useless?
        numTausch++;
    }

    public void resetNumVergl() {
        numVergl = 0;
    }

    public void incVergl() { //useless?
        numVergl++;
    }

    public int getNumVergl() {
        return numVergl;
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        Algorithmus.delay = delay;
    }
}
