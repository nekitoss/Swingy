package ua.nekitoss;

import java.sql.SQLException;

public class Main {

  public static void main(String[] args) {
/*    Hero testHero = new HeroBuilder().setHeroName("MyTestHero")
            .setHeroMapSign('H')
            .setHeroLvl(0)
            .setHeroHp(10)
            .setHeroExp(0)
            .setHeroDefence(2)
            .setHeroClass(Hero.HeroClass.attacker)
            .setHeroAttack(20)
            .getResult();

    testHero.setExperience(1000);
    MainFrameController gameGui = MainFrameController.getInstance();

    Gameplay g = Gameplay.getInstance();
    g.setMfc(gameGui);
    g.setView(GameFrame.getInstance());
    g.newLevel();
    gameGui.showMainFrameWindow();*/

//    while (Gameplay.getState() == Gameplay.gameState.CHOOSE_RUN_OR_FIGHT) {
//      try {
//        Thread.sleep(200);
//      } catch (InterruptedException e) {
//      }
//    }
//    System.out.println("end of while");

    TableTestClass tmp = new TableTestClass();
    tmp.setName("first born5");

    TableTestDAOImpl s = new TableTestDAOImpl();
    try {
      int num = s.addTT(tmp);
//      tmp.setId(num); //не надо - прописывает автоматом
//      s.getAllData();
    } catch (SQLException e) {
      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
