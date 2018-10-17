package ua.nekitoss.view.graphic;

import java.awt.*;
import javax.swing.*;

public class MenuNewLoadGui {
    private  static final int BTN_SEL_WIDTH = 100;
    private  static final int BTN_SEL_HEIGHT = 50;

    private JFrame jframeNewLoad;
    private JPanel jpanelNewLoad1;
    private JButton newBtn;
    private JButton loadBtn;

    public MenuNewLoadGui() {
        jframeNewLoad = new JFrame("Hero:");
        Toolkit tool = Toolkit.getDefaultToolkit();
        Dimension dimensionWindow = tool.getScreenSize();
        jframeNewLoad.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframeNewLoad.setResizable(false);
        jpanelNewLoad1 = new JPanel();
        jpanelNewLoad1.setLayout(new FlowLayout());
        newBtn = createButton("New...", 10, 10);
        loadBtn = createButton("Load...", 210, 10);
        jpanelNewLoad1.add(newBtn);
        jpanelNewLoad1.add(loadBtn);
        jframeNewLoad.add(jpanelNewLoad1);
        jframeNewLoad.pack();
        jframeNewLoad.setBounds(dimensionWindow.width / 2 - jframeNewLoad.getWidth() / 2, dimensionWindow.height / 2 - jframeNewLoad.getWidth() / 2, jframeNewLoad.getWidth(), jframeNewLoad.getHeight());

    }

    private JButton createButton(String title, int x, int y){
        JButton button = new JButton(title);
        button.setBounds(x, y, BTN_SEL_WIDTH, BTN_SEL_HEIGHT);
        return button;
    }

    public static void main(String[] args) {
        MenuNewLoadGui app = new MenuNewLoadGui();
        app.jframeNewLoad.setVisible(true);
    }

    public JFrame getJframeNewLoad() {
        return jframeNewLoad;
    }

    public JPanel getJpanelNewLoad1() {
        return jpanelNewLoad1;
    }

    public JButton getNewBtn() {
        return newBtn;
    }

    public JButton getLoadBtn() {
        return loadBtn;
    }
}
