package ua.nekitoss.model;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

public class ASoul extends AMapElement {
  @PositiveOrZero
  protected int attack;
  @PositiveOrZero
  protected int defense;
  @Positive
  protected int hp;

  public int getAttack() {
    return attack;
  }

  public void setAttack(int attack) {
    this.attack = attack;
  }

  public int getDefense() {
    return defense;
  }

  public void setDefense(int defense) {
    this.defense = defense;
  }

  public int getHp() {
    return hp;
  }

  public void setHp(int hp) {
    this.hp = hp;
  }

  public boolean changeHp(int amount){
    this.hp += amount;
    if (this.hp <= 0){
      this.hp = 0;
      return false;
    }
    return true;
  }

  public boolean attackEnemy(ASoul opponent, boolean showDetailedFight){
    if (showDetailedFight) System.out.print(this.getName() + " attacks with " + (this.attack) + " power " + opponent.getName() + ", which has " + opponent.getHp() + " hp and " + opponent.getDefense() + " defence. As result " + opponent.getName() + " left with ");
    if (this.attack - opponent.getDefense() > 0){
      if (!opponent.changeHp(-(this.attack - opponent.getDefense()))){
        if (showDetailedFight)   System.out.println(opponent.getHp() + " hp - " + opponent.getName() + " is DEAD");
        return false;
      }
    }
    if (showDetailedFight) System.out.println(opponent.getHp() + " hp");
    return true;
  }
}
