import java.util.Random; //by Jens

/**
 * @author Matthias Zimmer und Uwe Seckinger (verändert Hanna Wüst)
 * @version 2018.03.05
 * Oberfläche zur Wiedergabe von Sortierergebnissen 
 *
 */



public class Datenverwaltung {
	
	//edit Jens
	Random rnd = new Random();
	private GSort graphics;
	
	public Datenverwaltung() {
		graphics = new GSort(this);
	}
	public Datenverwaltung(boolean a) {
		
	}
	
	
	//end edit Jens
	
	
    /**
     * Array, das die zu sortierenden Daten enthält
     */
    public int[] daten;

    /**
     * Variable, in der die Anzahl der Vergleiche gespeichert werden kann.
     * Inhalt wird automatisch von Oberfläche übernommen.
     */
    private long anzahlVergleiche = 0;

    /**
     * Variable, in die die Laufzeit des Algorithmus in Millisekunden
     * gespeichert werden kann Wert wird automatisch in die Oberfläche
     * übernommen.
     */
    private long laufzeit = 0;

    /**
     * Methode, die die Elemente an den beiden vorgegebenen Stellen vertauscht 
     * 
     * @param ersterIndex - Index des ersten Elements
     * @param zweiterIndex - Indes des zweiten Elements
     */
    private void tauscheElementeAnPositionen(int ersterIndex, int zweiterIndex) {
        // Element an erster Position in Zwischenspeicher merken
        int zwischenspeicher = daten[ersterIndex]; 
        // Element an zweiter Position an erste Position kopieren 
        daten[ersterIndex] = daten[zweiterIndex];
        // Element aus Zwischenspeicher in zweite Position kopieren
        daten[zweiterIndex] = zwischenspeicher; 
        graphics.render();
    }

    /**
     * Sortiert die Daten im Array int[] daten mit Hilfe von SelectionSort
     */
    public void selectionSort() {
        //hier an der im Unterricht vorgesehenen Stelle Quelltext für Selectionsort einfügen
    	anzahlVergleiche = 0;
    	long timeStart = System.currentTimeMillis(); 
    	int b; //zeiger Bestes
    	for(int a = 0; a < daten.length; a++) {
			b = a;
			for(int s=a+1; s < daten.length; s++) {	
				if(daten[b] > daten[s]) {
					b = s;
				}
				anzahlVergleiche++;
			}
			tauscheElementeAnPositionen(a, b);
		}	
		long timeEnd = System.currentTimeMillis();
		laufzeit = timeEnd - timeStart;

    }

    /**
     * sortiert die Daten im Array int[] daten mit Hilfe von BubbleSort
     */
    public void bubbleSort() {
    	anzahlVergleiche = 0;
    	long timeStart = System.currentTimeMillis(); 
    	boolean getuascht = false;
    	while(!checkSort(daten)) {
    		for(int i = 0; i<daten.length-1; i++) {
    			anzahlVergleiche++;
    			if(daten[i]>daten[i+1]) {
    				tauscheElementeAnPositionen(i, i+1);
    			}
    		}
    	}
    	
    	long timeEnd = System.currentTimeMillis();
		laufzeit = timeEnd - timeStart; 

        // hier an der im Unterricht vorgesehenen Stelle Quelltext für Bubblesort einfügen
    }

    /**
     * sortiert die Daten im Array int[] daten mit Hilfe von Insertionsort
     */
    public void insertionSort() {

        // hier an der im Unterricht vorgesehenen Stelle Quelltext für Insertionsort einfügen

    }
    
    /**
     * Sortiert die Daten im Array int[] daten mit Hilfe von Quicksort
     */
    public void quickSort(int links, int rechts) {
        //hier an der im Unterricht vorgesehenen Stelle Quelltext für Quicksort einfügen
    }
    
    /**
     * Sortiert die Daten im Array int[] daten mit Hilfe von Mergesort
     */
    public void mergeSort(int links, int rechts) {
        //hier an der im Unterricht vorgesehenen Stelle Quelltext für Mergesort einfügen
    }
    
    
    // --- by Jens
    public void bogosort() {
    	anzahlVergleiche = 0;
    	long timeStart = System.currentTimeMillis(); 
    	while(!checkSort(daten)) {
    		tauscheElementeAnPositionen(rnd.nextInt(daten.length), rnd.nextInt(daten.length));
			anzahlVergleiche++;
    	}
    	long timeEnd = System.currentTimeMillis();
		laufzeit = timeEnd - timeStart;    
    }
    
    private boolean checkSort(int[] a) {
    	boolean retVal = false;
    	int z = 0;
    	for(int i=1; i< daten.length; i++) {
    		z = a[i-1];
    		if(z<=a[i]) {
    			retVal = true;
    		}else {
    			retVal = false;
    			break;
    		}
    	}
    	return retVal;
    }
    // --- end by Jens

    /**********************************************************
     * 
     * AB HIER NICHTS MEHR ÄNDERN!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
     * 
     ********************************************************** 
     */

    /**
     * stellt das Datensatzarray für die Oberfläche bereit
     * 
     * @return Array mit Datensätzen
     */
    public int[] getDaten() {
        return daten;
    }

    /**
     * stellt die Anzahl der Vergleiche für die Oberfläche bereit
     * 
     * @return Bei Algorithmus ermittelte Anzahl der Vergleiche
     */
    public long getAnzahlVergleiche() {
        return anzahlVergleiche;
    }

    /**
     * stellt die Laufzeit des Algorithmus für Oberfläche bereit
     * 
     * @return Bei Algorithmus ermittelte Laufzeit
     */
    public int getLaufzeit() {
        return (int) laufzeit;
    }

    /**
     * Füllt das Array daten mit Zufallszahlen zwischen 0 und der Zehnfachen
     * Anzahl
     * 
     * @param Anzahl
     *            der zu erzeugende Datensätze
     */
    public void zufaelligeDatensaetzeErzeugen(int anzahl) {
        daten = new int[anzahl];
        for (int i = 0; i < anzahl; i++) {
            daten[i] = (int) (Math.random() * anzahl * 10);
        }
    }

    /**
     * Füllt das Array daten mit Zufallszahlen zwischen 0 und der Hälfte der
     * Anzahl -> häufige Wiederholungen
     * 
     * @param Anzahl
     *            der zu erzeugende Datensätze
     */
    public void datensaetzeMitWiederholungErzeugen(int anzahl) {
        daten = new int[anzahl];
        for (int i = 0; i < anzahl; i++) {
            daten[i] = (int) (Math.random() * anzahl / 2);
        }
    }
}
