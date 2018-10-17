package ua.nekitoss.controller;

import ua.nekitoss.model.heroes.Hero;
import ua.nekitoss.view.graphic.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

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



//  private HeroSelectFrame selectFrame;
  private MenuNewLoadGui menuNewLoadGui;
  private NewGui newGui;
  private LoadGui loadGui;

  private JComboBox loadBox;
  private JComboBox newBox;
  private JButton newBtn;
  private JButton createBtn;
  private JButton loadBtn;
  private JButton loadFinishBtn;
  private JTextField newNameTxtField;

    private JLabel newHpLbl;
    private JLabel newAttackLbl;
    private JLabel newDefenceLbl;

  private JLabel loadNameLbl;
  private JLabel loadExperienceLbl;
  private JLabel loadLevelLbl;
  private JLabel loadHpLbl;
  private JLabel loadAttackLbl;
  private JLabel loadDefenceLbl;
  private JLabel loadHelmetLbl;
  private JLabel loadArmorLbl;
  private JLabel loadWeaponLbl;
  private JLabel loadSavedLbl;

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
//    selectFrame.setVisible(false);

    mainFrame.setVisible(true);
  }

  public void showSelectFrameWindow() {
    mainFrame.setVisible(false);
//    selectFrame.setVisible(true);
      menuNewLoadGui.getJframeNewLoad().setVisible(true);
  }

  private void initComponents() {
    mainFrame = GameFrame.getInstance();
//    selectFrame = HeroSelectFrame.getInstance();
    menuNewLoadGui = new MenuNewLoadGui();
    newGui = new NewGui();
    loadGui = new LoadGui();

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

//    loadBox = selectFrame.getHeroListBox();
    loadBox = loadGui.getHeroListBox();
//    newBtn = selectFrame.getNewButton();
    newBtn = menuNewLoadGui.getNewBtn();
    createBtn = newGui.getCreateBtn();
//    loadBtn = selectFrame.getLoadButton();
    loadBtn = menuNewLoadGui.getLoadBtn();
//    newBox = selectFrame.getNewBox();
    newBox = newGui.getNewBox();
//    newNameTxtField = selectFrame.getHeroNameTextField();
    newNameTxtField = newGui.getHeroNameTextField();



      newHpLbl = newGui.getHpLbl();
      newAttackLbl = newGui.getAttackLbl();
      newDefenceLbl = newGui.getDefenceLbl();

    loadNameLbl = loadGui.getNameLbl();
    loadExperienceLbl = loadGui.getExperienceLbl();
    loadLevelLbl = loadGui.getLevelLbl();
    loadHpLbl = loadGui.getHpLbl();
    loadAttackLbl = loadGui.getAttackLbl();
    loadDefenceLbl = loadGui.getDefenceLbl();
      loadHelmetLbl = loadGui.getHelmetLbl();
      loadArmorLbl = loadGui.getArmorLbl();
      loadWeaponLbl = loadGui.getWeaponLbl();
    loadSavedLbl = loadGui.getSavedLbl();
    loadFinishBtn= loadGui.getLoadFinishBtn();
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
    createBtn.addActionListener(new CreateBtnListener());
    loadFinishBtn.addActionListener(new LoadFinishBtnListener());
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
      fillHeroInfoToLoad(herolist.get(0));
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

  public void fillHeroInfoToLoad(Hero hero){
    loadNameLbl.setText(hero.getName());
    loadExperienceLbl.setText(Integer.toString(hero.getExperience()));
    loadLevelLbl.setText(Integer.toString(hero.getLevel()));
    loadHpLbl.setText(Integer.toString(hero.getHp()));
    loadAttackLbl.setText(Integer.toString(hero.getAttack()));
    loadDefenceLbl.setText(Integer.toString(hero.getDefense()));
//    loadHelmetLbl.setText(hero.getHelm().toString());
//    loadArmorLbl.setText(hero.getArmor().toString());
//    loadWeaponLbl.setText(hero.getWeapon().toString());
    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss   dd.MM.yyyy");
    loadSavedLbl.setText(sdf.format(hero.getSaved()));
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

  private class CreateBtnListener implements ActionListener {
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

  private class NewBtnListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      newGui.getJframeNewHero().setVisible(true);
    }
  }

  private class LoadBtnListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        loadGui.getJframeLoadHero().setVisible(true);
    }
  }

    private class LoadFinishBtnListener implements ActionListener {
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
      fillHeroInfoToLoad((LoadHeroController.getInstance().getHeroList()).get(selectedCheckboxNum));
    }
  }
}
