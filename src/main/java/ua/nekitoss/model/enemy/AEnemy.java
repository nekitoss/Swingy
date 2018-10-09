package ua.nekitoss.model.enemy;

import ua.nekitoss.model.ASoul;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

public class AEnemy extends ASoul {
  @PositiveOrZero
  protected int experienceForKill;


  protected AEnemy(String name, char sign, @PositiveOrZero int experienceToKill, @PositiveOrZero int attack, @PositiveOrZero int defense, @Positive int hp) {
    this.name = name;
    this.experienceForKill = experienceToKill;
    this.attack = attack;
    this.defense = defense;
    this.hp = hp;
    this.mapSign = sign;
  }

  protected AEnemy() {}

  public int getExperienceForKill() {
    return experienceForKill;
  }
}
