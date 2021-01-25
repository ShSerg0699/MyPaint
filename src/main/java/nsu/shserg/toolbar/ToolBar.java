package nsu.shserg.toolbar;

import nsu.shserg.MainPanel;
import nsu.shserg.dialogs.LineSetting;
import nsu.shserg.dialogs.StampSetting;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ToolBar extends JToolBar {
    StampSetting stampSetting;
    LineSetting lineSetting;

    public ToolBar(final MainPanel panel, LineSetting lineSet, StampSetting stampSet, JLabel statusLabel) {
        super("ToolBar");
        setRollover(true);
        lineSetting = lineSet;
        stampSetting = stampSet;

        ToolBarButton open = new ToolBarButton("Open...", "src\\main\\resources\\Open.png");
        ToolBarButton save = new ToolBarButton("Save", "src\\main\\resources\\Save.png");
        ToolBarButton clear = new ToolBarButton("Clear", "src\\main\\resources\\Clear.png");
        ToolBarButton line = new ToolBarButton("Line", "src\\main\\resources\\Line.png");
        ToolBarButton stamp = new ToolBarButton("Stamp", "src\\main\\resources\\Stamp.png");
        ToolBarButton fill = new ToolBarButton("Fill", "src\\main\\resources\\Fill.png");
        ToolBarButton color = new ToolBarButton("Color", "src\\main\\resources\\Color.png");


        open.addActionListener(e -> {
            JFileChooser fileOpen = new JFileChooser();
            int ret = fileOpen.showDialog(panel, "Open...");
            if ((ret == JFileChooser.APPROVE_OPTION) && fileOpen.getSelectedFile() != null) {
                File file = fileOpen.getSelectedFile();
                statusLabel.setText(file.getName());
                try {
                    BufferedImage image = ImageIO.read(file);
                    panel.getImage().getGraphics().drawImage(image, 0, 0, null);
                    panel.repaint();
                } catch (IOException ex){
                    ex.printStackTrace();
                }
            }
        });
        save.addActionListener(e -> {
            File file = new File("image.png");
            try {
                ImageIO.write(panel.getImage(), "png", file);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        line.addActionListener(e -> {
            statusLabel.setText("Line");
            lineSetting.showDialog();
            panel.setLineModeOn(lineSetting.getThickness());
        });
        fill.addActionListener(e -> {
            statusLabel.setText("Fill");
            panel.setFillModeOn();
        });
        stamp.addActionListener(e -> {
            statusLabel.setText("Stamp");
            stampSetting.showDialog();
            panel.setStampModeOn(stampSetting.getIsStar(), stampSetting.getNumNode(), stampSetting.getRadius(), stampSetting.getRotation());
        });
        clear.addActionListener(e -> panel.clearPanel());
        color.addActionListener(e -> panel.setColor());

        add(open);
        add(save);
        addSeparator();
        add(clear);
        add(line);
        add(stamp);
        add(fill);
        add(color);
    }


}
