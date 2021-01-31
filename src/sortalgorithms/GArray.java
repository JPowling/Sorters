package sortalgorithms;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class GArray {

	private final int ABSTAND_RAND = 20;
	private int CURRENT_WIDTH;
	private int CURRENT_HEIGHT;

	public void render(Graphics g) throws InterruptedException {
		CURRENT_WIDTH = GUI_Sort.getViualiserWidth();
		CURRENT_HEIGHT = GUI_Sort.getViualiserHight();

		g.clearRect(0, 0, CURRENT_WIDTH, CURRENT_HEIGHT);

		g.setColor(Color.WHITE);

		float num1 = (float) CURRENT_HEIGHT - 10;
		float num2 = (float) Algorithmus.daten.length;
		double scale = num1 / num2;

		@SuppressWarnings("IntegerDivisionInFloatingPointContext")
		float width = (CURRENT_WIDTH - 2 * ABSTAND_RAND) / ((Algorithmus.daten.length));

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

			g.fillRect(ABSTAND_RAND + Math.round(i * width), CURRENT_HEIGHT, Math.round(width), (int) Math.round((-Algorithmus.daten[i] * scale) - 10));
		}
	}
}
