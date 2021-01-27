package sortalgorithms;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class GSort extends Canvas {

    public static int width;
    public static int height;
    private static final long serialVersionUID = -1442798787354930462L;
    private final boolean running = false;
    private final GArray gArray;
    Random rand;
    private Thread thread;

    public GSort(int width, int height) {
        GSort.width = width;
        GSort.height = height;
        rand = new Random();
        gArray = new GArray();
        super.setPreferredSize(new Dimension(width, height));
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
