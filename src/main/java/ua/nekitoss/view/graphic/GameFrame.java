package ua.nekitoss.view.graphic;

import ua.nekitoss.view.AView;

import javax.swing.*;
import javax.swing.text.DefaultCaret;

public class GameFrame extends JFrame implements AView {
  private static GameFrame instance;

  private static final int WIDTH = 600;
  private static final int HEIGHT = 500;
  private JPanel mainPanel;
  private JScrollPane scrlPane;
  private JTextArea gameTA;
  private JButton upBtn;
  private JButton downBtn;
  private JButton rightBtn;
  private JButton leftBtn;
  private JButton mapBtn;
  private JLabel heroInfoLBL;
  private JCheckBox printMapEveryTurnCheckBox;
  private JCheckBox detailedFightCheckBox;
  private JLabel heroInfoLBL2;

  private GameFrame() {
    setSize(WIDTH, HEIGHT);
    setContentPane(mainPanel);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    scrlPane.setAutoscrolls(true);
    gameTA.setEditable(false);
    DefaultCaret caret = (DefaultCaret)gameTA.getCaret();
    caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
    setTitle("Swingy by nekitoss");
  }

  public static GameFrame getInstance(){
    if (instance == null){
      instance = new GameFrame();
    }
    return instance;
  }

  public static boolean askEndlessYesNo(String message, String title, Object buttonNames[]){
    int answer = -2;
    while (!(answer == 0 || answer == 1)) {
      answer = JOptionPane.showOptionDialog(null, message, title,
              JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
              null, buttonNames, buttonNames[0]);
//      System.out.println("answer =" + answer);
    }
    if (answer == JOptionPane.YES_OPTION) return true;
    return false;
  }


  @Override
  public void printMsg(String msg) {
    gameTA.append(msg);
  }

  @Override
  public void printlnMsg(String msg) {
    gameTA.append(msg + '\n');
  }

  @Override
  public void showBoldMsg(String msg) {
//    JOptionPane.showMessageDialog(this, msg);
    int exit = JOptionPane.showConfirmDialog(null, msg, null, JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
    if (exit == JOptionPane.OK_OPTION)
    {
      System.out.println("okbtn");
    }
//        JOptionPane.showMessageDialog(frame,
//                "Eggs are not supposed to be green.",
//                "Inane custom dialog",
//                JOptionPane.INFORMATION_MESSAGE,
//                icon);
  }

  @Override
  public void printHeroInfo(String msg) {
    heroInfoLBL.setText(msg);
  }

  @Override
  public void printHeroInfo2(String msg) {
    heroInfoLBL2.setText(msg);
  }

  public JTextArea getGameTA() {
    return gameTA;
  }

  public JButton getUpBtn() {
    return upBtn;
  }

  public JButton getDownBtn() {
    return downBtn;
  }

  public JButton getRightBtn() {
    return rightBtn;
  }

  public JButton getLeftBtn() {
    return leftBtn;
  }

  public JButton getMapBtn() {
    return mapBtn;
  }

  public JLabel getHeroInfoLBL() {
    return heroInfoLBL;
  }

  public JCheckBox getPrintMapEveryTurnCheckBox() {
    return printMapEveryTurnCheckBox;
  }

  public JCheckBox getDetailedFightCheckBox() {
    return detailedFightCheckBox;
  }

  public JLabel getHeroInfoLBL2() {
    return heroInfoLBL2;
  }
}
