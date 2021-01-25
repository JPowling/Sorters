import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class GSort extends Canvas {
	
	private static final long serialVersionUID = -1442798787354930462L;

	public static final int WIDTH = 1200;
	public static final int HIGHT = WIDTH / 12 * 9;
	private Thread thread;
	private boolean running = false;

	Random rand;
	private GArray gArray;
	
	public GSort(Datenverwaltung dvw) {
		rand = new Random();
		new GFenster(WIDTH, HIGHT, "Game 01", this);
		gArray = new GArray(dvw);
		

		
		
	}

	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}

		Graphics g = bs.getDrawGraphics();


		try {
			gArray.render(g);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		g.dispose();
		bs.show();
	}
	
	
}
