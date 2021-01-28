package sortalgorithms;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class GSort extends Canvas {

    public static int width;
    public static int height;

    private final GArray gArray;

    public GSort() {
        GSort.width = GUI_Sort.getViualiserWidth();
        GSort.height = GUI_Sort.getViualiserHight();

        this.gArray = new GArray();

        super.setPreferredSize(new Dimension(width, height));
    }

    public void render() {
        GUI_Sort.setAnzSwapLabel(Long.toString(Algorithmus.getNumTausch()));
        GUI_Sort.setAnzCompareLabel(Long.toString(Algorithmus.getNumVergl()));

        renderGraphics();
    }

    private void renderGraphics() {
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
