package jp.waseda.asagi.kobayashi.utils;

import java.util.Map;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;

import jp.waseda.asagi.kobayashi.router.Router;
import jp.waseda.asagi.kobayashi.settings.Settings;

public abstract class OriginalView extends JPanel {
    public final String path;

    abstract public void onAppear(Map<String, String> param);

    abstract public void onDisapper();

    protected final JPanel mainPanel = new JPanel();

    private final JButton backButton = new JButton("戻る");

    public OriginalView(String path, String headerTitle, boolean needBackButton) {
        super();
        this.path = path;
        setLayout(null);

        // メインパネル
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBounds(5, 30, Settings.PANEL_WIDTH - 25, Settings.PANEL_HEIGHT - 75);
        add(mainPanel);

        final JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new GridBagLayout());

        if (needBackButton) {
            // 戻るボタンの作成
            backButton.setBounds(5, 5, 150, 20);

            ButtonActionAttacher.attach(backButton, () -> {
                Router.pop();
            });
            add(backButton);
        }

        // ヘッダーの作成
        JLabel headerLabel = new JLabel(headerTitle);
        headerPanel.add(headerLabel);
        headerPanel.setBounds(0, 0, Settings.PANEL_WIDTH - 25, 30);
        add(headerPanel);
    }

    public void changeBackButtonText(String text) {
        backButton.setText(text);
    }
}