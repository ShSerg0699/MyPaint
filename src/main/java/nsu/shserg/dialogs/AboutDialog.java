package nsu.shserg.dialogs;

import javax.swing.*;
import java.awt.*;

public class AboutDialog extends JDialog
{
    public AboutDialog(JFrame owner)
    {
        super(owner, "About My Paint", true);
        add(new JLabel("<html><h1><i>  My Paint</i></h1>"
            + "My Paint is a simple raster graphics editor."
            + "The program has three drawing tools - \"line\", \"stamp\" and \"fill\". "
            + "When you click the left mouse button in the workspace of the application, the current tool with the center at the selected point is applied. "
            + "For the line tool, the last 2 click points are connected by a line segment.<br>"
            + "The line tool is characterized by two parameters: line thickness and current color. "
            + "Lines are drawn on top of an existing image.<br>"
            + "The stamp tool draws the outline of the selected shape. "
            + "A shape is regular convex polygon or regular star. "
            + "A stamp is drawn over the existing image with lines of thickness 1 of the current color.<br>"
            + "Parameters of the stamp tool:<br>"
            + "1) shape (type of polygon);<br>"
            + "2) size (radius of the polygon in pixels);<br>"
            + "3) rotation (rotation of the polygon around the center in degrees).<br>"
            + "The fill tool, starting from the specified point, executes the Span-fill algorithm. "
            + "That is, all the pixels of the four-connected pixel region of the seed color are filled with the current color. "
            + "The fill tool has only one parameter - color.<br>"
            + "For all tools, the color is set in a separate dialog box.<br>"
            + "There is also a function to clear the drawing area. "
            + "When you call it, the entire area is painted over in white."
            + "<hr>By Shniakin Sergei, FIT NSU 17204<br>"
            + "Build on March 3, 2020</html>"),
                BorderLayout.CENTER);

        JButton ok = new JButton("ok");
        ok.addActionListener(e -> setVisible(false));

        JPanel panel = new JPanel();
        panel.add(ok);
        add(panel, BorderLayout.SOUTH);
        setSize(400, 500);
    }
}