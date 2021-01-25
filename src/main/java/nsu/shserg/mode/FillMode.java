package nsu.shserg.mode;

import nsu.shserg.MainPanel;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class FillMode extends MouseAdapter {
    private MainPanel jFrame;
    private BufferedImage image;
    private Graphics graphics;

    public FillMode(MainPanel mainPanel, Graphics graphics) {
        jFrame = mainPanel;
        image = mainPanel.getImage();
        this.graphics = graphics;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        spanFill(e.getX(), e.getY(), graphics);
        jFrame.repaint();
    }

    public void spanFill(int x, int y, Graphics g) {
        int oldColor = image.getRGB(x, y);
        if (oldColor == g.getColor().getRGB()){
            return;
        }
        
        int i;
        for (i = x; (i >= 0) && (oldColor == image.getRGB(i,y)); --i);
        int spanStart = i + 1;
        for (i = x; (i < image.getWidth()) && (oldColor == image.getRGB(i, y)); ++i);
        int spanEnd = i - 1;
        g.drawLine(spanStart, y, spanEnd, y);

        for(i = spanStart; i <= spanEnd; ++i){
            if((y < image.getHeight() - 1) && oldColor == image.getRGB(i, y + 1)){
                spanFill(i, y + 1, g);
            }
            if((y > 0) && oldColor == image.getRGB(i, y - 1)){
                spanFill(i, y - 1, g);
            }
        }

    }
}
