package ua.nekitoss.model;

import ua.nekitoss.controller.Gameplay;
import ua.nekitoss.model.enemy.AEnemy;
import ua.nekitoss.model.heroes.Hero;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

@MappedSuperclass
public class ASoul extends AMapElement {
  @PositiveOrZero
  @Column(name = "attack")
  protected int attack;

  @PositiveOrZero
  @Column(name = "defense")
  protected int defense;

  @Positive
  @Column(name = "hp")
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

    if (this instanceof Hero && opponent instanceof AEnemy){
      return heroAttacksEnemy((Hero) this, (AEnemy) opponent, showDetailedFight);
    }
    else if (opponent instanceof Hero && this instanceof AEnemy){
      return enemyAttacksHero((Hero) opponent, (AEnemy) this, showDetailedFight);
    }
    System.err.println("wrong souls to fight!");
    return true;
/*    if (showDetailedFight) Gameplay.getInstance().getView().printMsg(this.getName() + " attacks with " + (this.attack) + " power " + opponent.getName() + ", which has " + opponent.getHp() + " hp and " + opponent.getDefense() + " defence. As result " + opponent.getName() + " left with ");
    //System.out.print(this.getName() + " attacks with " + (this.attack) + " power " + opponent.getName() + ", which has " + opponent.getHp() + " hp and " + opponent.getDefense() + " defence. As result " + opponent.getName() + " left with ");
    if (this.attack - opponent.getDefense() > 0){
      if (!opponent.changeHp(-(this.attack - opponent.getDefense()))){
        if (showDetailedFight)   Gameplay.getInstance().getView().printlnMsg(opponent.getHp() + " hp - " + opponent.getName() + " is DEAD");
          //System.out.println(opponent.getHp() + " hp - " + opponent.getName() + " is DEAD");
        return false;
      }
    }
    if (showDetailedFight) Gameplay.getInstance().getView().printlnMsg(opponent.getHp() + " hp");
      //System.out.println(opponent.getHp() + " hp");
    return true;*/
  }

  private boolean heroAttacksEnemy(Hero hero, AEnemy enemy, boolean showDetailedFight){
    if (showDetailedFight) Gameplay.getInstance().getView().printMsg(hero.getName() + " attacks with " + (hero.getFullAttack()) + " power " + enemy.getName() + ", which has " + enemy.getHp() + " hp and " + enemy.getDefense() + " defence. As result " + enemy.getName() + " left with ");
    //System.out.print(this.getName() + " attacks with " + (this.attack) + " power " + opponent.getName() + ", which has " + opponent.getHp() + " hp and " + opponent.getDefense() + " defence. As result " + opponent.getName() + " left with ");
    if (hero.getFullAttack() - enemy.getDefense() > 0){
      if (!enemy.changeHp(-(hero.getFullAttack() - enemy.getDefense()))){
        if (showDetailedFight)   Gameplay.getInstance().getView().printlnMsg(enemy.getHp() + " hp - " + enemy.getName() + " is DEAD");
        //System.out.println(opponent.getHp() + " hp - " + opponent.getName() + " is DEAD");
        return false;
      }
    }
    if (showDetailedFight) Gameplay.getInstance().getView().printlnMsg(enemy.getHp() + " hp");
    //System.out.println(opponent.getHp() + " hp");
    return true;
  }

  private boolean enemyAttacksHero(Hero hero, AEnemy enemy, boolean showDetailedFight){
    if (showDetailedFight) Gameplay.getInstance().getView().printMsg(enemy.getName() + " attacks with " + (enemy.attack) + " power " + hero.getName() + ", which has " + hero.getFullHp() + " hp and " + hero.getFullDefence() + " defence. As result " + hero.getName() + " left with ");
    //System.out.print(this.getName() + " attacks with " + (this.attack) + " power " + opponent.getName() + ", which has " + opponent.getHp() + " hp and " + opponent.getDefense() + " defence. As result " + opponent.getName() + " left with ");
    if (enemy.attack - hero.getFullDefence() > 0){
      if (!hero.changeHp(-(enemy.attack - hero.getFullDefence()))){
        if (showDetailedFight)   Gameplay.getInstance().getView().printlnMsg(hero.getHp() + " hp - " + hero.getName() + " is DEAD");
        //System.out.println(opponent.getHp() + " hp - " + opponent.getName() + " is DEAD");
        return false;
      }
    }
    if (showDetailedFight) Gameplay.getInstance().getView().printlnMsg(hero.getHp() + " hp");
    //System.out.println(opponent.getHp() + " hp");
    return true;
  }
}
