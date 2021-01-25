package nsu.shserg.mode;

import nsu.shserg.MainPanel;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class StampMode extends MouseAdapter {
    private MainPanel jFrame;
    private Graphics graphics;
    private boolean isStar = true;
    private int n = 5;
    private int radius = 100;
    private int rotate = 0;

    public StampMode(MainPanel mainPanel, Graphics graphics) {
        jFrame = mainPanel;
        this.graphics = graphics;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        drawPolygon(e.getX(), e.getY(), graphics);
        jFrame.repaint();
    }

    public void drawPolygon(int x, int y, Graphics g) {
        if (isStar) {
            int[] X = new int[n * 2];
            int[] Y = new int[n * 2];
            for (int i = 0; i < n * 2; i++) {
                if (i % 2 == 0) {
                    X[i] = (int) Math.round(x + (Math.cos(Math.toRadians(rotate)) * radius));
                    Y[i] = (int) Math.round(y + (Math.sin(Math.toRadians(rotate)) * radius));
                } else {
                    X[i] = (int) Math.round(x + (Math.cos(Math.toRadians(rotate)) * radius / 2));
                    Y[i] = (int) Math.round(y + (Math.sin(Math.toRadians(rotate)) * radius / 2));
                }
                rotate += 360.0 / (2 * n);
            }
            g.drawPolygon(X, Y, n * 2);
        } else {
            int[] X = new int[n];
            int[] Y = new int[n];
            for (int i = 0; i < n; i++) {
                X[i] = (int) Math.round(x + (Math.cos(Math.toRadians(rotate)) * radius));
                Y[i] = (int) Math.round(y + (Math.sin(Math.toRadians(rotate)) * radius));
                rotate += 360.0 / n;
            }
            g.drawPolygon(X, Y, n);
        }
    }

    public void setIsStar(boolean x) {
        isStar = x;
    }

    public void setN(int x) {
        n = x;
    }

    public void setRadius(int x) {
        radius = x;
    }

    public void setRotate(int x) {
        rotate = x;
    }

}
