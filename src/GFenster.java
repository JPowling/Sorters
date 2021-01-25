import java.awt.Dimension;
import javax.swing.JFrame;

public class GFenster {

	//private static final long serialVersionUID = -8255319694373975038L;
		
	public GFenster(int width, int height, String title, GSort gSort) {
		JFrame frame = new JFrame(title);
		
		frame.setPreferredSize(new Dimension(width, height));
		frame.setMaximumSize(new Dimension(width, height));
		frame.setMinimumSize(new Dimension(width, height));
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocation(300, 200);	//obere linke Ecke, in der das Fenster sich öffnet
		frame.add(gSort);
		frame.setVisible(true);
		//gSort.start();
	}
	
}
