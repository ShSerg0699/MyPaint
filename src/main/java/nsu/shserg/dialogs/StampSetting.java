package nsu.shserg.dialogs;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class StampSetting extends JPanel {
    private static final int MAX_RADIUS = 500;
    private static final int MIN_RADIUS = 0;
    private static final int DEFAULT_RADIUS = 50;
    private static final int MAX_ROTATE = 180;
    private static final int MIN_ROTATE = 0;
    private static final int DEFAULT_ROTATE = 0;
    private static final int MAX_N = 16;
    private static final int MIN_N = 3;
    private static final int DEFAULT_N = 5;
    private boolean isStar = true;

    private final JRadioButton star = new JRadioButton("Star");
    private final JRadioButton polygon = new JRadioButton("Polygon");
    private final JSlider sliderRadius = new JSlider(MIN_RADIUS, MAX_RADIUS, DEFAULT_RADIUS);
    private final JSlider sliderRotate = new JSlider(MIN_ROTATE, MAX_ROTATE, DEFAULT_ROTATE);
    private final JSpinner spinnerRadius = new JSpinner(new SpinnerNumberModel(DEFAULT_RADIUS, MIN_RADIUS, MAX_RADIUS, 5));
    private final JSpinner spinnerRotate = new JSpinner(new SpinnerNumberModel(DEFAULT_ROTATE, MIN_ROTATE, MAX_ROTATE, 10));
    private final JSpinner spinnerN = new JSpinner(new SpinnerNumberModel(DEFAULT_N, MIN_N, MAX_N, 1));

    public StampSetting() {
        star.setSelected(true);
        star.addActionListener(e ->{
            if(star.isSelected()){
                polygon.setSelected(false);
                isStar = true;
            }
        });
        polygon.addActionListener(e -> {
            if(polygon.isSelected()){
                star.setSelected(false);
                isStar = false;
            }
        });

        sliderRadius.setPaintTicks(true);
        sliderRadius.setPaintLabels(true);
        sliderRadius.setMajorTickSpacing(100);
        sliderRadius.addChangeListener(e -> spinnerRadius.setValue(sliderRadius.getValue()));
        spinnerRadius.addChangeListener(e -> sliderRadius.setValue((int) spinnerRadius.getValue()));
        spinnerRadius.setToolTipText("0 to 500");

        sliderRotate.setPaintTicks(true);
        sliderRotate.setPaintLabels(true);
        sliderRotate.setMajorTickSpacing(30);
        sliderRotate.addChangeListener(e -> spinnerRotate.setValue(sliderRotate.getValue()));
        spinnerRotate.addChangeListener(e -> sliderRotate.setValue((int) spinnerRotate.getValue()));
        spinnerRotate.setToolTipText("0 to 180");
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(Box.createVerticalStrut(10));

        JPanel shapeType = createShapeTypePanel();
        this.add(shapeType);
        this.add(Box.createVerticalStrut(15));

        JPanel radius = this.createSliderSpinnerPanel(spinnerRadius, sliderRadius);
        radius.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(EtchedBorder.RAISED, Color.BLACK, Color.WHITE),
                "Radius", TitledBorder.LEADING, TitledBorder.TOP));
        this.add(radius);
        this.add(Box.createVerticalStrut(15));

        JPanel rotation = this.createSliderSpinnerPanel(spinnerRotate, sliderRotate);
        rotation.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(EtchedBorder.RAISED, Color.BLACK, Color.WHITE),
                "Rotation", TitledBorder.LEADING, TitledBorder.TOP));
        this.add(rotation);
        this.add(Box.createVerticalStrut(15));
    }

    private JPanel createShapeTypePanel(){
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel.add(star);
        panel.add(Box.createHorizontalStrut(10));
        panel.add(polygon);
        panel.add(Box.createHorizontalStrut(10));
        spinnerN.setToolTipText("3 to 16");
        spinnerN.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(EtchedBorder.RAISED, Color.BLACK, Color.WHITE),
                "N", TitledBorder.LEADING, TitledBorder.TOP));
        panel.add(spinnerN);
        return panel;
    }
    private JPanel createSliderSpinnerPanel(JSpinner spinner, JSlider slider) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel.add(spinner);
        panel.add(Box.createHorizontalStrut(10));
        panel.add(slider);
        return panel;
    }

    public int showDialog() {
        return JOptionPane.showConfirmDialog(this, this, "Stamp Settings",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
    }

    public boolean getIsStar(){
        return isStar;
    }

    public int getRotation() {
        return sliderRotate.getValue();
    }

    public int getRadius() {
        return sliderRadius.getValue();
    }

    public int getNumNode() {
        return (int) spinnerN.getValue();
    }
}