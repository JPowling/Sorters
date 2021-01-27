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

    public static Thread sortThread;
    private static boolean running;

    public Algorithmus() {
        name = this.getClass().getSimpleName();
        addAlgorithmus(this);
    }

    public static void fillDaten(int max) {
        daten = new int[max];
        boolean[] used = new boolean[max];

        for (int i = 0; i < max; i++) {
            used[i] = false;
        }
        for (int i = 0; i < max; i++) {
            while (true) {
                int a = rnd.nextInt(max);

                if (!used[a]) {
                    used[a] = true;
                    daten[i] = a;
                    break;
                }
            }
        }
    }

    public static void fillEmpty() {
        daten = new int[GUI_Sort.DEFAULT_ARRAY_SIZE];

        for (int i = 0; i < GUI_Sort.DEFAULT_ARRAY_SIZE; i++) {
            daten[i] = 0;
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

    public static boolean isRunning() {
        return Algorithmus.running;
    }


    public void swap(int i1, int i2) {
        swappedElements.clear();
        swappedElements.add(i1);
        swappedElements.add(i2);

        int zS = daten[i1];
        daten[i1] = daten[i2];
        daten[i2] = zS;

        numTausch++;
    }
    
  public static void stopSortThread() {
        clearHighlights();


        System.out.println("stopped sortThread");

        running = false;
        GUI_Sort.setBtnStartStopLabel("Start");
        sortThread.stop();
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
    public static void startSortThread() {
        running = true;
        GUI_Sort.setBtnStartStopLabel("Stop");
        sortThread.start();

    }

    public static boolean checkSort() {
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


    public static void stopSort() {
        clearHighlights();

        running = false;
        sortThread.stop();
        System.out.println("stopped sortThread");
    }
   
    private static void clearHighlights() {
        swappedElements.clear();
        comparedElements.clear();
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

    public void sort() {
        clearHighlights();

        System.out.println("Sorting with " + name + "...");

        sortThread = new Thread(() -> {
            internalSort();
            Algorithmus.stopSortThread();
            System.out.println("Sorted");
        });
        Algorithmus.startSortThread();
    }

    private static void clearHighlights() {
        swappedElements.clear();
        comparedElements.clear();
    }
}
