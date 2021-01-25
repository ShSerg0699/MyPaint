package nsu.shserg;

import nsu.shserg.mode.FillMode;
import nsu.shserg.mode.LineMode;
import nsu.shserg.mode.StampMode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

public class MainPanel extends JPanel implements ActionListener {
    private FillMode fillMode;
    private LineMode lineMode;
    private StampMode stampMode;

    private boolean isShowed = false;
    private BufferedImage image;
    private Graphics graphics;
    private Color color = Color.RED;

    public MainPanel(int width, int height) {
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        graphics = image.getGraphics();
        graphics.fillRect(0, 0, image.getWidth(), image.getHeight());
        graphics.setColor(this.color);
        fillMode = new FillMode(this, graphics);
        lineMode = new LineMode(this, graphics);
        stampMode = new StampMode(this, graphics);
        this.addMouseListener(lineMode);
    }

    public void setFillModeOn(){
        for (MouseListener it : this.getMouseListeners()) {
            this.removeMouseListener(it);
        }
        this.addMouseListener(fillMode);
    }

    public void setLineModeOn(int thickness){
        for (MouseListener it : this.getMouseListeners()) {
            this.removeMouseListener(it);
        }
        this.addMouseListener(lineMode);
        lineMode.setThickness(thickness);
    }

    public void setStampModeOn(boolean isStar, int n, int radius, int rotate){
        for (MouseListener it : this.getMouseListeners()) {
            this.removeMouseListener(it);
        }
        this.addMouseListener(stampMode);
        stampMode.setIsStar(isStar);
        stampMode.setN(n);
        stampMode.setRadius(radius);
        stampMode.setRotate(rotate);
    }

    public void clearPanel(){
        Color color = graphics.getColor();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, image.getWidth(), image.getHeight());
        graphics.setColor(color);
        repaint();
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setColor() {
        color = JColorChooser.showDialog(this, "Choose color", color);
        graphics.setColor(color);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        this.showLine();
    }

    private void showLine() {
        this.isShowed = !this.isShowed;
        this.repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D painter = (Graphics2D) g;
        painter.drawImage(this.image, 0, 0, null);
    }
}