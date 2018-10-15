package ua.nekitoss.controller;

import ua.nekitoss.model.heroes.Hero;
import ua.nekitoss.view.graphic.GameFrame;
import ua.nekitoss.view.graphic.HeroSelectFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FrameController {

  private static FrameController instance;

  private GameFrame mainFrame;
  private JTextArea gameTA;
  private JButton upBtn;
  private JButton downBtn;
  private JButton rightBtn;
  private JButton leftBtn;

  private JButton mapBtn;
  private JLabel heroInfoLBL;
  private JLabel heroInfoLBL2;

  private JCheckBox detailedFightChkBx;
  private JCheckBox mapEveryTurnChkBx;

  private HeroSelectFrame selectFrame;

  private JComboBox loadBox;
  private JComboBox newBox;
  private JButton newBtn;
  private JButton loadBtn;
  private JTextField newNameTxtField;

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

  private FrameController() {
    initComponents();
    initListeners();
    setFill();
  }

  public static FrameController getInstance(){
    if (instance == null){
      instance = new FrameController();
    }
    return instance;
  }

  public void showMainFrameWindow() {
//    mapBtn.setVisible(false);
    selectFrame.setVisible(false);
    mainFrame.setVisible(true);
  }

  public void showSelectFrameWindow() {
    mainFrame.setVisible(false);
    selectFrame.setVisible(true);
  }

  private void initComponents() {
    mainFrame = GameFrame.getInstance();
    selectFrame = HeroSelectFrame.getInstance();

    gameTA = mainFrame.getGameTA();
    upBtn = mainFrame.getUpBtn();
    downBtn = mainFrame.getDownBtn();
    leftBtn = mainFrame.getLeftBtn();
    rightBtn = mainFrame.getRightBtn();
    mapBtn = mainFrame.getMapBtn();
    heroInfoLBL = mainFrame.getHeroInfoLBL();
    heroInfoLBL2 = mainFrame.getHeroInfoLBL2();
    detailedFightChkBx = mainFrame.getDetailedFightCheckBox();
    mapEveryTurnChkBx = mainFrame.getPrintMapEveryTurnCheckBox();

    loadBox = selectFrame.getHeroListBox();
    newBtn = selectFrame.getNewButton();
    loadBtn = selectFrame.getLoadButton();
    newBox = selectFrame.getNewBox();
    newNameTxtField = selectFrame.getHeroNameTextField();

    nameLbl = selectFrame.getNameLbl();
    experienceLbl = selectFrame.getExperienceLbl();
    levelLbl = selectFrame.getLevelLbl();
    hpLbl = selectFrame.getHpLbl();
    attackLbl = selectFrame.getAttackLbl();
    defenceLbl = selectFrame.getDefenceLbl();
    helmetLbl = selectFrame.getHelmetLbl();
    armorLbl = selectFrame.getArmorLbl();
    weaponLbl = selectFrame.getWeaponLbl();
    savedLbl = selectFrame.getSavedLbl();
  }

  private void initListeners() {
    upBtn.addActionListener(new UpBtnListener());
    downBtn.addActionListener(new DownBtnListener());
    leftBtn.addActionListener(new LeftBtnListener());
    rightBtn.addActionListener(new RightBtnListener());
    mapBtn.addActionListener(new MapBtnListener());
    detailedFightChkBx.addActionListener(new DetailedFightListener());
    mapEveryTurnChkBx.addActionListener(new MapEveryTurnListener());

    newBtn.addActionListener(new NewBtnListener());
    loadBtn.addActionListener(new LoadBtnListener());
    loadBox.addActionListener(new HeroLoadComboBoxListener());
  }

  private void setFill(){
    newBox.setEditable(false);
    String[] items = Hero.HeroClass.names();
    newBox.setModel(new DefaultComboBoxModel(items));

    loadBox.setEditable(false);
    java.util.List<Hero> herolist = DatabaseController.getInstance().listHero();
    if (herolist.size() > 0) {
      String[] items2 = DatabaseController.getHeroNamesListFromHeroList(herolist);
      loadBox.setModel(new DefaultComboBoxModel(items2));
      fillHeroInfo(herolist.get(0));
    }
    else
    {
//      loadBox.setEnabled(false);
      loadBtn.setEnabled(false);
    }
//    loadBox.setModel(new DefaultComboBoxModel(herolist.toArray()));
  }

  public void printMsg(String msg){
    gameTA.append(msg);
  }

  public void printlnMsg(String msg){
    gameTA.append(msg + '\n');
  }

  public void fillHeroInfo(Hero hero){
    nameLbl.setText(hero.getName());
    experienceLbl.setText(Integer.toString(hero.getExperience()));
    levelLbl.setText(Integer.toString(hero.getLevel()));
    hpLbl.setText(Integer.toString(hero.getHp()));
    attackLbl.setText(Integer.toString(hero.getAttack()));
    defenceLbl.setText(Integer.toString(hero.getDefense()));
//    helmetLbl.setText(hero.getHelm());
//    armorLbl.setText(hero.getArmor());
//    weaponLbl.setText(hero.getWeapon());
    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss   dd.MM.yyyy");
    savedLbl.setText(sdf.format(hero.getSaved()));
  }

  private class UpBtnListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      gameTA.append("Up\n");
      Gameplay.moveHero(Gameplay.direction.UP);
    }
  }

  private class DownBtnListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      gameTA.append("Down\n");
      Gameplay.moveHero(Gameplay.direction.DOWN);
    }
  }

  private class LeftBtnListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      gameTA.append("Left\n");
      Gameplay.moveHero(Gameplay.direction.LEFT);
    }
  }

  private class RightBtnListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      gameTA.append("Right\n");
      Gameplay.moveHero(Gameplay.direction.RIGHT);
    }
  }

  private class DetailedFightListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      gameTA.append("You changed detailedFight setting to: " + (detailedFightChkBx.isSelected() ? "On" : "Off") + '\n');
      Gameplay.getInstance().setDetailedFight(detailedFightChkBx.isSelected());
    }
  }

  private class MapEveryTurnListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      gameTA.append("You change mapEveryTurn setting to: " + (mapEveryTurnChkBx.isSelected() ? "On" : "Off") + '\n');
      Gameplay.getInstance().setPrintMapEveryTurn(mapEveryTurnChkBx.isSelected());
    }
  }

  private class NewBtnListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      if (newNameTxtField.getText().isEmpty()){
        JOptionPane.showMessageDialog(null, "You must input Name for Hero!");
      }
      else {
        int selectedHeroType = newBox.getSelectedIndex();
        LoadHeroController.getInstance().createNewHero((Hero.HeroClass.values())[selectedHeroType], newNameTxtField.getText());
      }
    }
  }

  private class LoadBtnListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      int selectedCheckboxNum = loadBox.getSelectedIndex();
//      int selectedHeroId = (LoadHeroController.getInstance().getHeroList()).get(selectedCheckboxNum).getId();
      LoadHeroController.getInstance().loadHero(selectedCheckboxNum);
    }
  }

  private class MapBtnListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      gameTA.append("Map\n");
      Gameplay.printMap();
    }
  }

  private class HeroLoadComboBoxListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      int selectedCheckboxNum = loadBox.getSelectedIndex();
      fillHeroInfo((LoadHeroController.getInstance().getHeroList()).get(selectedCheckboxNum));
    }
  }
}