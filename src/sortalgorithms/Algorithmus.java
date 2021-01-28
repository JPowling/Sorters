package sortalgorithms;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public abstract class Algorithmus {

    private static final Random rnd = new Random();

    private static final ArrayList<Algorithmus> algoList = new ArrayList<>();

    public static int delay = 20;
    public static int[] daten = new int[1];
    private static int maxDatenVal;

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
        maxDatenVal = max;
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

    public static boolean checkSort() {
        for (int i = 0; i < daten.length - 1; i++) {
            if (daten[i] > daten[i + 1]) {
                return false;
            }
        }
        return true;
    }

	public static boolean isRunning() {
		return Algorithmus.running;
	}

	public static void startSortThread() {
		running = true;
		GUI_Sort.setBtnStartStopLabel("Stop");
		GUI_Sort.resetAnzLabels();
		sortThread.start();
	}

	public static void stopSortThread() {
		GUI_Sort.setAnzSwapLabel(Integer.toString(numTausch));
		GUI_Sort.setAnzCompareLabel(Integer.toString(numVergl));

		System.out.println("stopped sortThread");

		running = false;
		GUI_Sort.setBtnStartStopLabel("Start");
		sortThread.stop();

		clearHighlights();
	}

	private static void clearHighlights() {
		swappedElements.clear();
		comparedElements.clear();
	}

	public void addAlgorithmus(Algorithmus algo) {
		algoList.add(algo);
	}

	protected abstract void internalSort();

	public void sort() {
		clearHighlights();

		System.out.println("Sorting with " + name + "...");

		sortThread = new Thread(() -> {
			internalSort();
			Algorithmus.stopSortThread();
			clearHighlights();
			System.out.println("Sorted");
		});
		Algorithmus.startSortThread();
	}

	public void swap(int i1, int i2) {
		swappedElements.clear();
		swappedElements.add(i1);
		swappedElements.add(i2);

		int zS = daten[i1];
		daten[i1] = daten[i2];
		daten[i2] = zS;

		numTausch++;

		sleep();
	}

	@SuppressWarnings("StatementWithEmptyBody")
	private void sleep() {
		if(delay < 0) {
			return;
		}

		if (delay > 301) {
            long now = System.currentTimeMillis();
            while (System.currentTimeMillis() - now < delay / 2 - 50) {
                // do nothing
            }
        } else {
            long now = System.nanoTime();
            long calc = delay * 320_000 + 5000000;
            System.out.println(calc);

            while (System.nanoTime() - now < calc) {
                // do nothing
            }
        }
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

	public long getDelay() {
		return delay;
	}

	public void setDelay(int delay) {
		Algorithmus.delay = delay;
	}
}
