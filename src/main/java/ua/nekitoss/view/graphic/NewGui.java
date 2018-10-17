package ua.nekitoss.view.graphic;

import javax.swing.*;
import java.awt.*;

public class NewGui {
    private  static final int BTN_CRT_WIDTH = 100;
    private  static final int BTN_CRT_HEIGHT = 50;

    private JFrame jframeNewHero;
    private JPanel jpanelNew;
    private JButton createBtn;
    private JComboBox newBox;
    private JTextField heroNameTextField;
    private JLabel nameLbl;
    private JLabel typeLbl;

    private JPanel statsJPanel;

    private JLabel hpLbl;
    private JLabel attackLbl;
    private JLabel defenceLbl;


    public NewGui() {
        jframeNewHero = new JFrame("Create hero");
        Toolkit tool = Toolkit.getDefaultToolkit();
        Dimension dimensionWindow = tool.getScreenSize();
        jframeNewHero.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jframeNewHero.setResizable(false);
        jpanelNew = new JPanel();
        jpanelNew.setLayout(new GridLayout(3, 3));
        jframeNewHero.add(jpanelNew);

        nameLbl= new JLabel("Name:");
        jpanelNew.add(nameLbl);

        heroNameTextField = new JTextField();
        jpanelNew.add(heroNameTextField);

        typeLbl = new JLabel("Type:");
        jpanelNew.add(typeLbl);

        newBox = new JComboBox();
        jpanelNew.add(newBox);


        statsJPanel = new JPanel();
        statsJPanel.setLayout(new GridLayout(3,2));
        statsJPanel.add(new JLabel("Health:"));
        hpLbl = new JLabel();
        statsJPanel.add(hpLbl);

        statsJPanel.add(new JLabel("Attack:"));
        attackLbl = new JLabel();
        statsJPanel.add(attackLbl);

        statsJPanel.add(new JLabel("Defence:"));
        defenceLbl = new JLabel();
        statsJPanel.add(defenceLbl);

        jpanelNew.add(statsJPanel);


        createBtn = createButton("Confirm", 10, 10);
        jpanelNew.add(createBtn);




        jframeNewHero.pack();
//        jframeNewHero.setBounds(dimensionWindow.width / 2 - jframeNewHero.getWidth() / 2, dimensionWindow.height / 2 - jframeNewHero.getWidth() / 2, jframeNewHero.getWidth(), jframeNewHero.getHeight());
        jframeNewHero.setBounds(dimensionWindow.width / 2 - jframeNewHero.getWidth() / 2, dimensionWindow.height / 2 - jframeNewHero.getWidth() / 2, 300, 300);


    }

    private JButton createButton(String title, int x, int y){
        JButton button = new JButton(title);
        button.setBounds(x, y, BTN_CRT_WIDTH, BTN_CRT_HEIGHT);
        return button;
    }

    public static void main(String[] args) {
        NewGui app = new NewGui();
        app.jframeNewHero.setVisible(true);
    }

    public JFrame getJframeNewHero() {
        return jframeNewHero;
    }

    public JPanel getJpanelNew() {
        return jpanelNew;
    }

    public JButton getCreateBtn() {
        return createBtn;
    }

    public JComboBox getNewBox() {
        return newBox;
    }

    public JTextField getHeroNameTextField() {
        return heroNameTextField;
    }

    public JLabel getNameLbl() {
        return nameLbl;
    }

    public JLabel getTypeLbl() {
        return typeLbl;
    }

    public JPanel getStatsJPanel() {
        return statsJPanel;
    }

    public JLabel getHpLbl() {
        return hpLbl;
    }

    public JLabel getAttackLbl() {
        return attackLbl;
    }

    public JLabel getDefenceLbl() {
        return defenceLbl;
    }
}
