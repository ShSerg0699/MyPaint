package nsu.shserg.dialogs;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class LineSetting extends JPanel {
    private static final int MAX_THICKNESS = 50;
    private static final int MIN_THICKNESS = 1;
    private static final int DEFAULT_THICKNESS = 1;

    private final JSpinner spinnerThickness = new JSpinner(
            new SpinnerNumberModel(DEFAULT_THICKNESS, MIN_THICKNESS, MAX_THICKNESS, 1));

    public LineSetting() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(Box.createVerticalStrut(10));
        spinnerThickness.setToolTipText("1 to 50");
        JPanel thickness = new JPanel();
        thickness.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(EtchedBorder.RAISED, Color.BLACK, Color.WHITE),
                "Thickness", TitledBorder.LEADING, TitledBorder.TOP));
        thickness.add(spinnerThickness);
        this.add(thickness);
        this.add(Box.createVerticalStrut(15));
    }

    public int showDialog() {
        return JOptionPane.showConfirmDialog(this, this, "Line Settings",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
    }

    public int getThickness() {
        return (int) spinnerThickness.getValue();
    }
}
