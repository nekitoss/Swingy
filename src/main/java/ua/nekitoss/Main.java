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
    tmp.setName("2Greatest born");
//    tmp.setSlaveInfo(new SlaveClass());
    tmp.getSlaveInfo().setInfo("i'm slave of " + tmp.getName());
    tmp.getSlaveInfo().setSlave_id(tmp.getId());

//    TableTestClass tmp2 = new TableTestClass();
//    tmp2.setName("2Greatest born");
//    tmp2.setSlaveInfo(new SlaveClass());
//    tmp2.getSlaveInfo().setInfo("i'm slave of " + tmp2.getName());
//    tmp2.getSlaveInfo().setSlave_id(tmp2.getId());
//
//    TableTestClass tmp3 = new TableTestClass();
//    tmp3.setName("2Greatest born");
//    tmp3.setSlaveInfo(new SlaveClass());
//    tmp3.getSlaveInfo().setInfo("i'm slave of " + tmp3.getName());
//    tmp3.getSlaveInfo().setSlave_id(tmp3.getId());

/*
    TexClass extTmp = new TexClass();
    extTmp.setExtendInt(333);

    Tex2Class ext2Tmp = new Tex2Class();
    ext2Tmp.setExtend2Int(444);
*/

    TableTestDAOImpl s = new TableTestDAOImpl();
    try {
//        s.addTT(tmp);
        tmp.setName("2OOps, i wa already born");
        s.saveOrUpdate(tmp);
//      s.addTT(tmp);
//      s.addTT(tmp2);
//      s.addTT(tmp3);
//      s.addTT(extTmp);
//      s.addTT(ext2Tmp);
//      tmp.setId(num); //не надо - прописывает автоматом
//      s.getAllData();
    } catch (SQLException e) {
      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
