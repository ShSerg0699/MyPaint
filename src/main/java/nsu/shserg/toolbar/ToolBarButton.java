package nsu.shserg.toolbar;

import javax.swing.*;

public class ToolBarButton extends JButton {
    public ToolBarButton(String hint, String iconName){
        Icon icon = new ImageIcon(iconName);
        setIcon(icon);
        setToolTipText(hint);
    }
}