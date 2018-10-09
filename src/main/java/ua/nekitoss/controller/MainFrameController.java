package ua.nekitoss.controller;

import ua.nekitoss.model.GameMap;
import ua.nekitoss.view.graphic.GameFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrameController {

  private static MainFrameController instance;

  private GameFrame mainFrame;
  private JTextArea gameTA;
  private JButton upBtn;
  private JButton downBtn;
  private JButton rightBtn;
  private JButton leftBtn;

  private JButton mapBtn;
  private JLabel heroInfoLBL;

  private JCheckBox detailedFightChkBx;
  private JCheckBox mapEveryTurnChkBx;

  private MainFrameController() {
    initComponents();
    initListeners();
  }

  public static MainFrameController getInstance(){
    if (instance == null){
      instance = new MainFrameController();
    }
    return instance;
  }

  public void showMainFrameWindow() {
//    mapBtn.setVisible(false);
    mainFrame.setVisible(true);
  }

  private void initComponents() {
    mainFrame = GameFrame.getInstance();

    gameTA = mainFrame.getGameTA();
    upBtn = mainFrame.getUpBtn();
    downBtn = mainFrame.getDownBtn();
    leftBtn = mainFrame.getLeftBtn();
    rightBtn = mainFrame.getRightBtn();
    mapBtn = mainFrame.getMapBtn();
    heroInfoLBL = mainFrame.getHeroInfoLBL();
    detailedFightChkBx = mainFrame.getDetailedFightCheckBox();
    mapEveryTurnChkBx = mainFrame.getPrintMapEveryTurnCheckBox();
  }

  private void initListeners() {
    upBtn.addActionListener(new UpBtnListener());
    downBtn.addActionListener(new DownBtnListener());
    leftBtn.addActionListener(new LeftBtnListener());
    rightBtn.addActionListener(new RightBtnListener());
    mapBtn.addActionListener(new MapBtnListener());
    detailedFightChkBx.addActionListener(new DetailedFightListener());
    mapEveryTurnChkBx.addActionListener(new MapEveryTurnListener());
  }

  public void printMsg(String msg){
    gameTA.append(msg);
  }

  public void printlnMsg(String msg){
    gameTA.append(msg + '\n');
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
      gameTA.append("Change detailedFight\n");
      Gameplay.getInstance().setDetailedFight(detailedFightChkBx.isSelected());
    }
  }

  private class MapEveryTurnListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      gameTA.append("Change mapEveryTurn\n");
      Gameplay.getInstance().setPrintMapEveryTurn(mapEveryTurnChkBx.isSelected());
    }
  }
//
//  private class TakeBtnListener implements ActionListener {
//    public void actionPerformed(ActionEvent e) {
//      gameTA.append("Take\n");
//    }
//  }
//
//  private class LeaveBtnListener implements ActionListener {
//    public void actionPerformed(ActionEvent e) {
//      gameTA.append("Leave\n");
//    }
//  }

  private class MapBtnListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      gameTA.append("Map\n");
      Gameplay.printMap();
    }
  }
}
