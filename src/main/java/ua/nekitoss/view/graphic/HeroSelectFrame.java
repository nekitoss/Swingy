package ua.nekitoss.view.graphic;

import ua.nekitoss.view.AView;
import javax.swing.*;

public class HeroSelectFrame extends JFrame{//} implements AView {
  private JComboBox heroListBox;
  private JButton newButton;
  private JButton loadButton;
  private JPanel heroSelectPanel;
  private JComboBox newBox;
  private JTextField heroNameTextField;
  private JPanel labesHeroInfolPane;
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

  private static HeroSelectFrame instance;

  private static final int WIDTH = 500;
  private static final int HEIGHT = 400;

  private HeroSelectFrame() {
    setSize(WIDTH, HEIGHT);
    setContentPane(heroSelectPanel);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setTitle("Swingy by nekitoss");
    pack();
  }

  public static HeroSelectFrame getInstance(){
    if (instance == null){
      instance = new HeroSelectFrame();
    }
    return instance;
  }

  public JButton getNewButton() {
    return newButton;
  }

  public JButton getLoadButton() {
    return loadButton;
  }

  public JComboBox getHeroListBox() {
    return heroListBox;
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
