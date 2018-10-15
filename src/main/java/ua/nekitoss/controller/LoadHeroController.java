package ua.nekitoss.controller;

import ua.nekitoss.model.heroes.Hero;
import ua.nekitoss.model.heroes.HeroBuilder;
import ua.nekitoss.view.graphic.HeroSelectFrame;

import java.sql.SQLException;
import java.util.List;

public class LoadHeroController {
  private static LoadHeroController instance;

  private FrameController fc;
  private List<Hero> heroList;

  private LoadHeroController() {
    fc = FrameController.getInstance();
    heroList = DatabaseController.getInstance().listHero();
  }

  public static LoadHeroController getInstance(){
    if (instance == null){
      instance = new LoadHeroController();
    }
    return instance;
  }

  public List<Hero> getHeroList() {
    return heroList;
  }

  public void showWindow() {


    fc.showSelectFrameWindow();
  }

  public void createNewHero(Hero.HeroClass heroClass, String name){
    System.out.println("Create HeroType =" + heroClass.name());
    Hero heroFreshlyCreated = HeroBuilder.createHeroByType(heroClass, name);

    try {
      DatabaseController.getInstance().add(heroFreshlyCreated);
    } catch (SQLException e) {
      System.err.println("Problems saving New hero to database, but you can still play!");
      e.printStackTrace();
    }
    Gameplay.getInstance().setHero(heroFreshlyCreated);
    Gameplay.getInstance().newLevel();
    fc.showMainFrameWindow();
  }

  public void loadHero(int idOfHeroInList){
    Hero heroToLoad = heroList.get(idOfHeroInList);
    System.out.println("Load HeroId =" + idOfHeroInList);
    Gameplay.getInstance().setHero(new Hero(heroToLoad));
    Gameplay.getInstance().newLevel();
    fc.showMainFrameWindow();
  }

}
