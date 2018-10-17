package ua.nekitoss.view.graphic;

import javax.swing.*;
import java.awt.*;

/**
 * Created by mpochuka on 10/17/18.
 */
public class LoadGui {
    private  static final int BTN_CRT_WIDTH = 100;
    private  static final int BTN_CRT_HEIGHT = 50;

    private JFrame jframeLoadHero;
    private JPanel jpanelLoad;
    private JButton loadFinishBtn;

//    private JPanel statsJPanel;
    private JComboBox heroListBox;
    private JLabel nameLbl;
    private JLabel experienceLbl;
    private JLabel levelLbl;
    private JLabel hpLbl;
    private JLabel attackLbl;
    private JLabel defenceLbl;
    private JLabel helmetLbl;
    private JLabel armorLbl;
    private JLabel weaponLbl;
    private JLabel savedLbl;


    public LoadGui() {
        jframeLoadHero = new JFrame("Create hero");
        Toolkit tool = Toolkit.getDefaultToolkit();
        Dimension dimensionWindow = tool.getScreenSize();
        jframeLoadHero.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jframeLoadHero.setResizable(false);

        jpanelLoad = new JPanel();
        jpanelLoad.setLayout(new GridLayout(11, 2));
        jframeLoadHero.add(jpanelLoad);

        heroListBox = new JComboBox();
        jpanelLoad.add(heroListBox);

        loadFinishBtn = createButton("Confirm", 10, 10);
        jpanelLoad.add(loadFinishBtn);

        jpanelLoad.add(new JLabel("Name:"));
        nameLbl= new JLabel();
        jpanelLoad.add(nameLbl);

        jpanelLoad.add(new JLabel("Exp:"));
        experienceLbl= new JLabel();
        jpanelLoad.add(experienceLbl);

        jpanelLoad.add(new JLabel("Level:"));
        levelLbl= new JLabel();
        jpanelLoad.add(levelLbl);

        jpanelLoad.add(new JLabel("Health:"));
        hpLbl = new JLabel();
        jpanelLoad.add(hpLbl);

        jpanelLoad.add(new JLabel("Attack:"));
        attackLbl = new JLabel();
        jpanelLoad.add(attackLbl);

        jpanelLoad.add(new JLabel("Defence:"));
        defenceLbl = new JLabel();
        jpanelLoad.add(defenceLbl);

        jpanelLoad.add(new JLabel("Helmet:"));
        helmetLbl = new JLabel();
        jpanelLoad.add(helmetLbl);

        jpanelLoad.add(new JLabel("Defence:"));
        armorLbl = new JLabel();
        jpanelLoad.add(armorLbl);

        jpanelLoad.add(new JLabel("Weapon:"));
        weaponLbl = new JLabel();
        jpanelLoad.add(weaponLbl);

        jpanelLoad.add(new JLabel("Saved:"));
        savedLbl = new JLabel();
        jpanelLoad.add(savedLbl);



        jframeLoadHero.pack();
        jframeLoadHero.setBounds(dimensionWindow.width / 2 - jframeLoadHero.getWidth() / 2, dimensionWindow.height / 2 - jframeLoadHero.getWidth() / 2, jframeLoadHero.getWidth(), jframeLoadHero.getHeight());



    }

    private JButton createButton(String title, int x, int y){
        JButton button = new JButton(title);
        button.setBounds(x, y, BTN_CRT_WIDTH, BTN_CRT_HEIGHT);
        return button;
    }

    public static void main(String[] args) {
        LoadGui app = new LoadGui();
        app.jframeLoadHero.setVisible(true);
    }


    public JFrame getJframeLoadHero() {
        return jframeLoadHero;
    }

    public JPanel getJpanelLoad() {
        return jpanelLoad;
    }

    public JButton getLoadFinishBtn() {
        return loadFinishBtn;
    }

    public JComboBox getHeroListBox() {
        return heroListBox;
    }

    public JLabel getNameLbl() {
        return nameLbl;
    }

    public JLabel getExperienceLbl() {
        return experienceLbl;
    }

    public JLabel getLevelLbl() {
        return levelLbl;
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

    public JLabel getHelmetLbl() {
        return helmetLbl;
    }

    public JLabel getArmorLbl() {
        return armorLbl;
    }

    public JLabel getWeaponLbl() {
        return weaponLbl;
    }

    public JLabel getSavedLbl() {
        return savedLbl;
    }
}
