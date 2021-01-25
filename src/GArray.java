import java.awt.*;

public class GArray {


	private final Datenverwaltung dvw;
	private GSort gSort;

	public GArray(Datenverwaltung dvw) {
		this.dvw = dvw;
	}

	public void render(Graphics g) throws InterruptedException {
		g.clearRect(0, 0, GSort.WIDTH, GSort.HIGHT);
		g.setColor(Color.blue);
		float num1 = (float) GSort.HIGHT - 150;
		float num2 = (float) dvw.daten.length * 10;
		double scale = num1 / num2;
		int width = GSort.WIDTH / ((dvw.daten.length) + 1);
		System.out.println(scale);
		for (int i = 0; i < dvw.daten.length; i++) {
			g.fillRect(20 + i * width, GSort.HIGHT - 100, width, (int) Math.round((-dvw.daten[i] * scale) - 10));
			System.out.print(dvw.daten[i] + " ");
		}
		System.out.println();

	}
}
