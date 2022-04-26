package utils;

import javax.swing.JPanel;

public abstract class OriginalView extends JPanel {
    public final String path;

    public OriginalView(String path) {
        super();
        this.path = path;
    }
}