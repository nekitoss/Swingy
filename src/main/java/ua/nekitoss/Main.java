package ua.nekitoss;

import javafx.scene.AmbientLight;
import ua.nekitoss.controller.Gameplay;
import ua.nekitoss.model.AMapElement;
import ua.nekitoss.model.GameMap;
import ua.nekitoss.model.enemy.HighHpPlankton;
import ua.nekitoss.model.enemy.Plankton;
import ua.nekitoss.model.heroes.Hero;
import ua.nekitoss.model.heroes.HeroBuilder;
import ua.nekitoss.model.obstacles.Stone;
import ua.nekitoss.model.obstacles.Tree;

public class Main {

    public static void main(String[] args) {
      HeroBuilder builder = new HeroBuilder();
      Hero testHero = builder.setHeroName("MyTestHero")
                .setHeroMapSign('H')
                .setHeroLvl(0)
                .setHeroHp(10)
                .setHeroExp(0)
                .setHeroDefence(2)
                .setHeroClass(Hero.HeroClass.attacker)
                .setHeroAttack(20)
                .getResult();
      GameMap map = new GameMap().createMap(5);
//      map.createMap(5);

      map.placeOnMap(testHero, 0, 0);
      map.placeOnMap(new Stone(), 1, 0);
      map.placeOnMap(new HighHpPlankton(), 0, 1);


      int numOfTrees = 0;
      while (numOfTrees < 5){
        if (map.placeRandomly(new Tree())) {
          numOfTrees++;
        }
      }
      map.printMap();
      System.out.println();

      Gameplay g = new Gameplay(map, testHero);
      g.move(0,0, Gameplay.direction.RIGHT);
      g.move(0,0, Gameplay.direction.DOWN);
      map.printMap();
    }
}
