import java.awt.Color;
import java.awt.Graphics;

public class GArray {
	
	
	
	private Datenverwaltung dvw;
	private GSort gSort;
	
	public GArray(Datenverwaltung dvw) {
		this.dvw = dvw;
	}
	
	public void render(Graphics g) throws InterruptedException {
	 	g.clearRect(0, 0, gSort.WIDTH, gSort.HIGHT);
		g.setColor(Color.blue);
		for(int i = 0; i<dvw.daten.length; i++) {
			g.fillRect(20+i*10, gSort.HIGHT-100, gSort.WIDTH/(dvw.daten.length*10), -dvw.daten[i]-10);
			System.out.print(dvw.daten[i]+" ");
	 	}
	 	System.out.println();
	 	//Thread.sleep(0);
	}
}
