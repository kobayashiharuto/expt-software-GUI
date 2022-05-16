package utils;

import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import router.Router;

public abstract class OriginalView extends JPanel {
    public final String path;

    abstract public void onAppear(Map<String, String> param);

    abstract public void onDisapper();

    public OriginalView(String path, boolean needBackButton) {
        super();
        this.path = path;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        if (needBackButton) {
            JButton backButton = new JButton("戻る");
            backButton.setAlignmentX(JButton.LEFT_ALIGNMENT);
            backButton.setHorizontalAlignment(JButton.LEFT);

            ButtonActionAttacher.attach(backButton, () -> {
                Router.pop();
            });
            add(backButton);
        }
    }
}