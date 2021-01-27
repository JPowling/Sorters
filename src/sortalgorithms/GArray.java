package sortalgorithms;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class GArray {
	public void render(Graphics g) throws InterruptedException {
		g.clearRect(0, 0, GSort.width, GSort.height);
		float num1 = (float) GSort.height - 300;
		float num2 = (float) Algorithmus.daten.length / 3;
		double scale = num1 / num2;
		int width = GSort.width / ((Algorithmus.daten.length) + 1);
//		System.out.println(GSort.width + ", " + GSort.height);
//		System.out.println(scale);

//		System.out.println(activeElements);

		Map<Integer, Color> colorMap = new HashMap<>();

		for (int i = 0; i < Algorithmus.getSwappedElements().size(); i++) {
			try {
				colorMap.put(Algorithmus.getSwappedElements().get(i), Color.GREEN);
			} catch (IndexOutOfBoundsException ignored) {
				// for threadsafety
			}
		}
		for (int i = 0; i < Algorithmus.getComparedElements().size(); i++) {
			try {
				colorMap.put(Algorithmus.getComparedElements().get(i), Color.RED);
			} catch (IndexOutOfBoundsException ignored) {
				// for threadsafety
			}
		}

		for (int i = 0; i < Algorithmus.daten.length; i++) {
			g.setColor(colorMap.getOrDefault(i, Color.blue));

			g.fillRect(20 + i * width, GSort.height - 100, width, (int) Math.round((-Algorithmus.daten[i] * scale) - 10));
		}
	}
}
