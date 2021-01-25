package nsu.shserg.mode;

import nsu.shserg.MainPanel;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class LineMode extends MouseAdapter {
    private MainPanel jFrame;
    private Graphics graphics;
    private Point point = null;
    private int thickness = 1;
    private BufferedImage image;

    public LineMode(MainPanel mainPanel, Graphics graphics) {
        jFrame = mainPanel;
        image = jFrame.getImage();
        this.graphics = graphics;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        point = e.getPoint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(thickness == 1) {
            drawBresenhamLine(point.x, point.y, e.getX(), e.getY(), graphics);
        } else{
            Graphics2D g2 = (Graphics2D) graphics;
            g2.setStroke(new BasicStroke(thickness));
            g2.drawLine(point.x, point.y, e.getX(), e.getY());
            g2.setStroke(new BasicStroke(1));
        }
        jFrame.repaint();
    }

    /*public void drawBresenhamLine(int x1, int y1, int x2, int y2, Graphics g) {
        if(x2 < 0 || x2 > image.getWidth() || y2 < 0 || y2 > image.getHeight()){
            return;
        }
        int x = x1;
        int y = y1;
        int dx = x2 - x1;
        int dy = y2 - y1;
        int incrementX = sign(dx);
        int incrementY = sign(dy);
        dx = Math.abs(dx);
        dy = Math.abs(dy);

        int pdx, pdy, shortSide, longSide;
        if (dx > dy) {
            pdx = incrementX;
            pdy = 0;
            shortSide = dy;
            longSide = dx;
        } else {
            pdx = 0;
            pdy = incrementY;
            shortSide = dx;
            longSide = dy;
        }

        int error = longSide / 2;
        image.setRGB(x,y, 23);
        for (int i = 0; i < longSide; i++) {
            error -= shortSide;
            if (error < 0) {
                error += longSide;
                x += incrementX;
                y += incrementY;
            } else {
                x += pdx;
                y += pdy;
            }
            image.setRGB(x,y, 23);
        }
    }*/
    public void drawBresenhamLine(int x1, int y1, int x2, int y2, Graphics g) {
        if(x2 < 0 || x2 >= image.getWidth() || y2 < 0 || y2 >= image.getHeight()){
            return;
        }
        g.drawLine(x1, y1, x2, y2);
        int x = x1;
        int y = y1;
        int dx = x2 - x1;
        int dy = y2 - y1;
        int incrementX = sign(dx);
        int incrementY = sign(dy);
        dx = Math.abs(dx);
        dy = Math.abs(dy);

        int pdx, pdy, shortSide, longSide;
        if (dx > dy) {
            pdx = incrementX;
            pdy = 0;
            shortSide = dy;
            longSide = dx;
        } else {
            pdx = 0;
            pdy = incrementY;
            shortSide = dx;
            longSide = dy;
        }

        int error = longSide;
        image.setRGB(x,y, g.getColor().getRGB());
        for (int i = 0; i < longSide; i++) {
            error -= shortSide*2;
            if (error < 0) {
                error += longSide*2;
                x += incrementX;
                y += incrementY;
            } else {
                x += pdx;
                y += pdy;
            }
            image.setRGB(x,y, g.getColor().getRGB());
        }
    }

    private int sign(int x) {
        return (x > 0) ? 1 : (x < 0) ? -1 : 0;
    }

    public void setThickness(int x){
        thickness = x;
    }
}
