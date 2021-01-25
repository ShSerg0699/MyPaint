package nsu.shserg;


import nsu.shserg.dialogs.AboutDialog;
import nsu.shserg.dialogs.LineSetting;
import nsu.shserg.dialogs.StampSetting;
import nsu.shserg.toolbar.ToolBar;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MainFrame extends JFrame {

    public MainFrame() {
        super("My Paint");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(800, 600));

        MainPanel panel = new MainPanel(800, 600);
        StampSetting stampSetting = new StampSetting();
        LineSetting lineSetting = new LineSetting();

        JPanel statusBar = new JPanel();
        statusBar.setBorder(new BevelBorder(BevelBorder.LOWERED));
        this.add(statusBar, BorderLayout.SOUTH);
        statusBar.setPreferredSize(new Dimension(this.getWidth(), 16));
        statusBar.setLayout(new BoxLayout(statusBar, BoxLayout.X_AXIS));
        JLabel statusLabel = new JLabel("MyPaint");
        statusLabel.setHorizontalAlignment(SwingConstants.LEFT);
        statusBar.add(statusLabel);

        ToolBar toolBar = new ToolBar(panel, lineSetting, stampSetting, statusLabel);

        JMenuBar menu = new JMenuBar();
        JMenu menuFile = new JMenu("File");
        JMenu menuView = new JMenu("View");
        JMenu menuHelp = new JMenu("Help");
        JMenuItem itemOpen = new JMenuItem("Open...");
        JMenuItem itemSave = new JMenuItem("Save");
        JMenuItem itemExit = new JMenuItem("Exit");
        JMenuItem itemClear = new JMenuItem("Clear");
        JMenuItem itemLine = new JMenuItem("Line");
        JMenuItem itemStamp = new JMenuItem("Stamp");
        JMenuItem itemFill = new JMenuItem("Fill");
        JMenuItem itemColor = new JMenuItem("Color");
        JMenuItem itemAbout = new JMenuItem("About");

        itemExit.addActionListener(e -> System.exit(0));
        itemOpen.addActionListener(e-> {
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
        itemSave.addActionListener(e -> {
            File file = new File("image.png");
            try {
                ImageIO.write(panel.getImage(), "png", file);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        itemLine.addActionListener(e -> {
                statusLabel.setText("Line");
                lineSetting.showDialog();
                panel.setLineModeOn(lineSetting.getThickness());
        });
        itemFill.addActionListener(e -> {
            statusLabel.setText("Fill");
            panel.setFillModeOn();
        });
        itemStamp.addActionListener(e -> {
            statusLabel.setText("Stamp");
            stampSetting.showDialog();
            panel.setStampModeOn(true, stampSetting.getNumNode(), stampSetting.getRadius(), stampSetting.getRotation());
        });
        itemClear.addActionListener(e -> panel.clearPanel());
        itemColor.addActionListener(e -> panel.setColor());
        itemAbout.addActionListener(event -> {
            if (aboutDialog == null)
                aboutDialog = new AboutDialog(MainFrame.this);
            aboutDialog.setVisible(true);
        });

        menu.add(menuFile);
        menu.add(menuView);
        menu.add(menuHelp);
        menuFile.add(itemOpen);
        menuFile.add(itemSave);
        menuFile.add(itemExit);
        menuView.add(itemClear);
        menuView.add(itemLine);
        menuView.add(itemStamp);
        menuView.add(itemColor);
        menuView.add(itemFill);
        menuHelp.add(itemAbout);

        final JScrollPane scrollPane = new JScrollPane(panel);
        this.add(scrollPane);

        this.setJMenuBar(menu);
        this.add(panel, BorderLayout.CENTER);
        this.add(toolBar, BorderLayout.NORTH);

        this.pack();
        this.setVisible(true);
    }

    AboutDialog aboutDialog;
}
