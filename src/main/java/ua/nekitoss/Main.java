package ua.nekitoss;

import ua.nekitoss.controller.DatabaseController;
import ua.nekitoss.controller.FrameController;
import ua.nekitoss.controller.Gameplay;
import ua.nekitoss.controller.LoadHeroController;
import ua.nekitoss.model.heroes.Hero;
import ua.nekitoss.model.heroes.HeroBuilder;
import ua.nekitoss.view.graphic.GameFrame;

import java.util.List;

public class Main {

  public static void main(String[] args) {
//    Hero testHero = new HeroBuilder().setHeroName("MyTestHero2")
//            .setHeroMapSign('H')
//            .setHeroLvl(0)
//            .setHeroHp(10)
//            .setHeroExp(0)
//            .setHeroDefence(2)
//            .setHeroClass(Hero.HeroClass.DEFAULT_HERO)
//            .setHeroAttack(20)
//            .getResult();
//    Hero testHero = HeroBuilder.createAttacker();


    DatabaseController s = new DatabaseController();
    try {
//      s.saveOrUpdate(testHero);
//      s.getAllData();
      List<Hero> heroList;
      heroList = s.listHero();
      System.out.println("end of try");
//    } catch (SQLException e) {
//      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }



//
    FrameController gameGui = FrameController.getInstance();
    Gameplay g = Gameplay.getInstance();
    g.setMfc(gameGui);
    g.setView(GameFrame.getInstance());
    LoadHeroController loadGui = LoadHeroController.getInstance();
    loadGui.showWindow();


    /*testHero.setExperience(1000);


    g.newLevel();
    gameGui.showMainFrameWindow();*/





//    System.out.println("end of while");









    /*
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


    TexClass extTmp = new TexClass();
    extTmp.setExtendInt(333);

    Tex2Class ext2Tmp = new Tex2Class();
    ext2Tmp.setExtend2Int(444);


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
    */
  }
}
