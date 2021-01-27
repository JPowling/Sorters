package sortalgorithms;

import java.awt.*;

public class GArray {
	private GSort gSort;

	public GArray() {
	}

	public void render(Graphics g) throws InterruptedException {
		g.clearRect(0, 0, GSort.width, GSort.height);
		g.setColor(Color.blue);
		float num1 = (float) GSort.height - 300;
		float num2 = (float) Algorithmus.daten.length * 10;
		double scale = num1 / num2;
		int width = GSort.width / ((Algorithmus.daten.length) + 1);
		System.out.println(GSort.width + ", " + GSort.height);
		System.out.println(scale);
		for (int i = 0; i < Algorithmus.daten.length; i++) {
			g.fillRect(20 + i * width, GSort.height - 100, width, (int) Math.round((-Algorithmus.daten[i] * scale) - 10));
		}
	}
}
