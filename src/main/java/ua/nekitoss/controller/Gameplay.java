package ua.nekitoss.controller;

import ua.nekitoss.model.AMapElement;
import ua.nekitoss.model.ASoul;
import ua.nekitoss.model.GameMap;
import ua.nekitoss.model.enemy.AEnemy;
import ua.nekitoss.model.enemy.HighHpPlankton;
import ua.nekitoss.model.enemy.Plankton;
import ua.nekitoss.model.equipment.AEquip;
import ua.nekitoss.model.equipment.EquipBuilder;
import ua.nekitoss.model.heroes.Hero;
import ua.nekitoss.model.obstacles.AObstacle;
import ua.nekitoss.model.obstacles.Stone;
import ua.nekitoss.model.obstacles.Tree;
import ua.nekitoss.view.AView;
import ua.nekitoss.view.graphic.GameFrame;

import java.sql.SQLException;
import java.util.Random;

public class Gameplay {
  private final static int PERCENT_OF_ENEMIES = 30;
  private final static int PERCENT_OF_OBSTACLES = 10;

  private static Gameplay instance;

  public enum direction {
    NORTH,
    EAST,
    SOUTH,
    WEST,
    UP,
    RIGHT,
    LEFT,
    DOWN
  }

  public enum gameState {
    CHOOSE_RUN_OR_FIGHT,
    FIGHT,
    RUN,
    CHOOSE_LEAVE_OR_TAKE,
    OTHER
  }

  public static volatile gameState state;
  private GameMap map;
  private Hero hero;
  private FrameController mfc;
  private AView view;
  private boolean printMapEveryTurn;
  private boolean detailedFight;

  private Gameplay() {
  }

  public static Gameplay getInstance() {
    if (instance == null) {
      instance = new Gameplay();
      instance.map = GameMap.getInstance();
      instance.hero = Hero.getInstance();
//      instance.newLevel();
    }
    return instance;
  }

  public static gameState getState() {
    return state;
  }

  public void setView(AView view) {
    this.view = view;
  }

  public static void setState(gameState state) {
    Gameplay.state = state;
  }

  public void setMfc(FrameController mfc) {
    this.mfc = mfc;
  }

  public void newLevel(){

    try {
      DatabaseController.getInstance().saveOrUpdate(hero);
    } catch (SQLException e) {
      System.err.println("There is a problem saving hero to database, but you still can play!");
      e.printStackTrace();
    }

    int mapSize = (hero.getLevel() - 1) * 5 + 10 - (hero.getLevel() % 2);
    GameMap map = GameMap.getInstance().createMap(mapSize);

    if (PERCENT_OF_ENEMIES + PERCENT_OF_OBSTACLES > 90){
      System.err.println("map is overloaded with entities");
      System.exit(-1);
    }

    map.placeOnMap(hero, map.getCenter(), map.getCenter());
//    map.placeOnMap(new Stone(), 1, 0);
//    map.placeOnMap(new HighHpPlankton(999), 0, 1);

    Random r = new Random();

    int numOfObstacles = 0;
    while (numOfObstacles < mapSize * mapSize * PERCENT_OF_OBSTACLES / 100) {
      if (map.placeRandomly(r.nextBoolean() ? new Tree() : new Stone())) {
        numOfObstacles++;
      }
    }

    int numOfEnemies = 0;
    while (numOfEnemies < mapSize * mapSize * PERCENT_OF_ENEMIES / 100) {
      if (map.placeRandomly(r.nextBoolean() ? new HighHpPlankton(numOfEnemies) : new Plankton(numOfEnemies))) {
        numOfEnemies++;
      }
    }

    map.printMap();
    System.out.println();
    printMap();

    view.printlnMsg("You entered new location. Map size is " + GameMap.getSize() + 'x' + GameMap.getSize());
    this.showHeroInfo();
  }

  public boolean move(int oldX, int oldY, direction dir) {
    int newX = oldX;
    int newY = oldY;
    switch (dir) {
      case UP:
      case NORTH: //up
        newY--;
        break;
      case RIGHT:
      case EAST: //right
        newX++;
        break;
      case LEFT:
      case WEST: //left
        newX--;
        break;
      case DOWN:
      case SOUTH: //down
        newY++;
        break;
      default:
        System.err.println("Unknown direction");
        return false;
    }
    if (GameMap.coordinatesValid(newX, newY)) {
      if (map.isFree(newX, newY)) {
        if (map.placeOnMap(map.getSoul(oldX, oldY), newX, newY))
          map.deleteFromMap(oldX, oldY);
        else
          System.err.println("cannot place on map, don't know why");
      } else {
        if (map.getSoul(newX, newY) instanceof AObstacle) {
          view.printlnMsg("blocked by " + map.getSoul(newX, newY).getClass().getSimpleName());
        } else if (map.getSoul(oldX, oldY) instanceof Hero && map.getSoul(newX, newY) instanceof AEnemy) {
          view.printlnMsg("enemy on your way: " + map.getSoul(newX, newY).getClass().getSimpleName());
          ///decide to run or fight
//          state = gameState.CHOOSE_RUN_OR_FIGHT;
          Object[] options = {"Yes, kill him!", "No, ruuuun!"};
          if (GameFrame.askEndlessYesNo("Do you want to fight?", "Fight or run", options)) {
            //fight
            this.fight(map.getSoul(oldX, oldY), map.getSoul(newX, newY), detailedFight);
            this.afterBattle(oldX, oldY, newX, newY);
          } else {
            //run
            view.printMsg(hero.getName() + " is trying to run...");
            Random r = new Random();
            if (r.nextBoolean()) {
              view.printlnMsg("NO LUCK!");
              this.fight(map.getSoul(oldX, oldY), map.getSoul(newX, newY));
              this.afterBattle(oldX, oldY, newX, newY);
            } else {
              view.printlnMsg("LUCKY!");
              view.printlnMsg(hero.getName() + " successfully run to previous position");
            }
          }
          this.showHeroInfo();
        } else {
//          System.err.println("unknown type on map!" + map.getSoul(newX, newY).getClass().getName());
          return false;
        }
      }
    } else {
      if (map.getSoul(oldX, oldY) instanceof Hero) {
//        view.printlnMsg("you won!");
//        view.showBoldMsg("you won!");
        Object[] options = {"Next level", "Save and Exit"};
        if (GameFrame.askEndlessYesNo("Congratulations! You won!", "Congratulations!", options)){//next lvl
          this.newLevel();
        }
        else {//save&exit
          //save hero
          try {
            DatabaseController.getInstance().saveOrUpdate(hero);
          } catch (SQLException e) {
            System.err.println("There is a problem saving hero to database, but you still can play!");
            e.printStackTrace();
          }
          System.exit(0);
        }
      } else
        return false;
    }
    if (printMapEveryTurn){
      Gameplay.printMap();
    }
    return true;
  }

  public void fight(AMapElement attacker, AMapElement defender) {
    this.fight(attacker, defender, false);
  }

  public void fight(AMapElement attacker, AMapElement defender, boolean showDetailedFight) {
    if (attacker instanceof ASoul && defender instanceof ASoul) {
      ASoul a = (ASoul) attacker;
      ASoul d = (ASoul) defender;
      if (a.getAttack() - d.getDefense() <= 0 && d.getAttack() - a.getDefense() <= 0)
        System.err.println("infinity fight!");
      else {
        int i = 0;
//        view.printMsg("Turn " + (++i) + ": ");
        while (a.getHp() > 0 && d.getHp() > 0) {
          if (showDetailedFight) view.printMsg("Turn " + (++i) + ": ");
          if (a.attackEnemy(d, showDetailedFight)) { //round 1
            if (showDetailedFight) view.printMsg("Turn " + (++i) + ": ");
            d.attackEnemy(a, showDetailedFight);//round 2 if alive
          }
        }
        if (a.getHp() > 0)
          view.printlnMsg(a.getName() + " killed " + d.getName());
        else
          view.printlnMsg(d.getName() + " killed " + a.getName());
      }
    } else {
      System.err.println("non-souls cannot fight! a:" + attacker.toString() + " d:" + defender.toString());
    }

  }

  public Hero getHero() {
    return hero;
  }

  public static boolean moveHero(direction dir) {
    if (Gameplay.getInstance().getHero() != null) {
      return Gameplay.getInstance().move(Gameplay.getInstance().getHero().getxPos(), Gameplay.getInstance().getHero().getyPos(), dir);
    }
    return false;
  }

  public void afterBattle(int oldX, int oldY, int newX, int newY) {
    if (hero.getHp() > 0) {
      if (map.getSoul(newX, newY) instanceof AEnemy) {
        int rewardXp = ((AEnemy) map.getSoul(newX, newY)).getExperienceForKill();
        if (hero.addExperience(rewardXp))
          view.printlnMsg("You earned new level! You level now is " + hero.getLevel());
        Random r = new Random();
        if (r.nextInt(((AEnemy) map.getSoul(newX, newY)).getChanceToLoot()) == 0) {
          AEquip loot = EquipBuilder.generateRandomEquip();
          Object[] options = {"Yes, equip brand new item!", "No, throw it to Orodruin in Mordor!"};
          if (GameFrame.askEndlessYesNo("Do you want this Thing: " + loot.getType().name() + ", called " + loot.getName() +  ", that increase +" + loot.getStatIncrease() + " stat ?", "Collecting garbage", options)) {
            switch (loot.getType()){
              case WEAPON:
                hero.setWeapon(loot);
                break;
              case ARMOR:
                hero.setArmor(loot);
                break;
              case HELM:
                if (!hero.changeHelm(loot)) view.printMsg("You wasn't able to equip it. You are alive only because of existing helmet stats! New equipment was lost.");
                break;
            }
          }
          else {
            loot = null;
            view.printlnMsg("You destroyed loot from enemy");
          }
        }
        else
          System.out.println("no luck for loot");
      }
      else
        System.err.println("afterBattle musn't be used on non-Enemies!");
      map.deleteFromMap(newX, newY);
      map.placeOnMap(map.getSoul(oldX, oldY), newX, newY);
      map.deleteFromMap(oldX, oldY);
    } else {
      map.deleteFromMap(oldX, oldY);
      view.printlnMsg("Rest In Peace, my Hero! Tales will remember you!");
      //            this.hero = null;
    }
  }

  public AView getView() {
    return view;
  }

  public static void printMap(){
    Gameplay.getInstance().getView().printlnMsg(GameMap.getInstance().mapAsString());
  }

  public void showHeroInfo(){
    StringBuffer heroInfoMsg = new StringBuffer();
    heroInfoMsg.append(hero.getName());
    heroInfoMsg.append(": Lvl: ");
    heroInfoMsg.append(hero.getLevel());
    heroInfoMsg.append(";     Hp: ");
    heroInfoMsg.append(hero.getHp());
    view.printHeroInfo(heroInfoMsg.toString());

    StringBuffer heroInfoMsg2 = new StringBuffer();
    heroInfoMsg2.append("At: ");
    heroInfoMsg2.append(hero.getAttack());
    heroInfoMsg2.append("; De: ");
    heroInfoMsg2.append(hero.getDefense());
    heroInfoMsg2.append(";     Equip: He:");
    heroInfoMsg2.append(hero.getHelm() == null ? '-' : ("+" + hero.getHelm().getStatIncrease()));
    heroInfoMsg2.append(" ; Ar:");
    heroInfoMsg2.append(hero.getArmor() == null ? '-' : ("+" + hero.getArmor().getStatIncrease()));
    heroInfoMsg2.append(" ; We:");
    heroInfoMsg2.append(hero.getWeapon() == null ? '-' : ("+" + hero.getWeapon().getStatIncrease()));
    view.printHeroInfo2(heroInfoMsg2.toString());

  }

  public void setPrintMapEveryTurn(boolean printMapEveryTurn) {
    this.printMapEveryTurn = printMapEveryTurn;
  }

  public void setDetailedFight(boolean detailedFight) {
    this.detailedFight = detailedFight;
  }

  public void setHero(Hero hero) {
    this.hero = hero;
  }
}
