package ua.nekitoss.controller;

import ua.nekitoss.model.AMapElement;
import ua.nekitoss.model.ASoul;
import ua.nekitoss.model.GameMap;
import ua.nekitoss.model.enemy.AEnemy;
import ua.nekitoss.model.heroes.Hero;
import ua.nekitoss.model.obstacles.AObstacle;

public class Gameplay {
  public enum direction{
    NORTH,
    EAST,
    SOUTH,
    WEST,
    UP,
    RIGHT,
    LEFT,
    DOWN
  }

  GameMap map;
  Hero hero;

  public Gameplay(GameMap map, Hero hero) {
    this.map = map;
    this.hero = hero;
  }

  public boolean move(int oldX, int oldY, direction dir){
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
    if (GameMap.coordinatesValid(newX, newY))
    {
      if (map.isFree(newX, newY))
      {
        if (map.placeOnMap(map.getSoul(oldX, oldY), newX, newY))
          map.deleteFromMap(oldX, oldY);
        else
          System.err.println("cannot place on map, don't know why");
      }
      else
      {
        if (map.getSoul(newX, newY) instanceof AObstacle){
          System.out.println("blocked by " + map.getSoul(newX, newY).getClass().getSimpleName());
        }
        else if (map.getSoul(oldX, oldY) instanceof Hero && map.getSoul(newX, newY) instanceof AEnemy){
          System.out.println("enemy on your way: " + map.getSoul(newX, newY).getClass().getSimpleName());
          ///decide to run or fight
          this.fight(map.getSoul(oldX, oldY), map.getSoul(newX, newY));
          if (hero.getHp() > 0){
            map.deleteFromMap(newX, newY);
            map.placeOnMap(map.getSoul(oldX, oldY), newX, newY);
            map.deleteFromMap(oldX, oldY);
          }else{
            map.deleteFromMap(oldX, oldY);
            System.out.println("Rest In Peace, my Hero! Tales will remember you!");
//            this.hero = null;
          }
        }
        else{
//          System.err.println("unknown type on map!" + map.getSoul(newX, newY).getClass().getName());
          return false;
        }
      }
    }
    else
    {
      if (map.getSoul(oldX, oldY) instanceof Hero){
        System.out.println( "you won!");
      }
      else
        return false;
    }
    return true;
  }

  public void fight(AMapElement attacker, AMapElement defender) {
    this.fight(attacker, defender, false);
  }

  public void fight(AMapElement attacker, AMapElement defender, boolean showDetailedFight){
    if (attacker instanceof ASoul && defender instanceof ASoul) {
      ASoul a = (ASoul) attacker;
      ASoul d = (ASoul) defender;
      if (a.getAttack() - d.getDefense() <= 0 && d.getAttack() - a.getDefense() <= 0)
        System.err.println("infinity fight!");
      else {
        while (a.getHp() > 0 && d.getHp() > 0) {
          if (a.attackEnemy(d, showDetailedFight)) //round 1
            d.attackEnemy(a, showDetailedFight);//round 2 if alive
        }
        if (a.getHp() > 0)
          System.out.println(a.getName() + " killed " + d.getName());
        else
          System.out.println(d.getName() + " killed " + a.getName());
      }
    }
    else{
      System.err.println("non-souls cannot fight! a:" + attacker.toString() + " d:" + defender.toString());
    }

  }
}
