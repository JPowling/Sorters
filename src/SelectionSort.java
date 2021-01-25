import java.util.Random;

public class SelectionSort {
	
	
	public static void main(String[] args) {
		
		Random rnd = new Random();
		int max = 10000;
		int[] daten = new int[max];
		
		for(int i=0; i<max; i++) {
			daten[i] = rnd.nextInt(max);
		}
		System.out.println("Sorting.....\t ");
		//print(array);
		long vergleiche = 0;
		long timeStart = System.currentTimeMillis(); 
		int b; //zeiger Bestes
		int zSp = 0; //zwischenspeicher
		//print(array);
		for(int a = 0; a < daten.length; a++) {
			b = a;
			for(int s=a+1; s < daten.length; s++) {	
				if(daten[b] > daten[s]) {
					b = s;
				}
				vergleiche++;
			}
			zSp = daten[a];
			daten[a] = daten[b];
			daten[b] = zSp;
			//print(array);
		}	
		long timeEnd = System.currentTimeMillis();
		System.out.println("Sorted ");
		//print(array);
		long timeDelta = timeEnd - timeStart;
		System.out.println("Vergleiche: " +vergleiche);
		System.out.println("Time passed: "+ timeDelta);
	}
	
	public static void print(int[] a) {
		for(int i = 0; i < a.length; i++) {
			System.out.print(a[i]+", ");
		}
		System.out.println();
	}
	
}

