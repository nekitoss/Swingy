package ua.nekitoss.model.heroes;

import lombok.Getter;
import lombok.Setter;
import ua.nekitoss.model.AMapElement;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

public abstract class AHero extends AMapElement {
  public enum HeroClass {
    attacker,
    firstHeroClassType,
    secondHeroClassType
  }

//  @NotEmpty
//  @Getter
//  @Setter
//  protected String  name;
  @NotNull
  @Getter
  @Setter
  protected HeroClass classOfHero;
  @PositiveOrZero
  protected int level;
  @PositiveOrZero
  protected int experience;
  @PositiveOrZero
  @Getter
  @Setter
  protected int attack;
  @PositiveOrZero
  @Getter
  @Setter
  protected int defense;
  @Positive
  @Getter
  @Setter
  protected int hp;

  public AHero(@NotEmpty String name, int xPos, int yPos,@NotEmpty char mapSign, @NotNull HeroClass classOfHero, @PositiveOrZero int level, @PositiveOrZero int experience, @PositiveOrZero int attack, @PositiveOrZero int defense, @Positive int hp) {
    this.name = name;
    this.xPos = xPos;
    this.yPos = yPos;
    this.mapSign = mapSign;
    this.classOfHero = classOfHero;
    this.level = level;
    this.experience = experience;
    this.attack = attack;
    this.defense = defense;
    this.hp = hp;
  }

  public int getLevel() {
    return level;
  }

  public void setLevel(int level) {
    this.level = level;
  }

  public int getExperience() {
    return experience;
  }

  public void setExperience(int experience) {
    this.experience = experience;
    this.setLevel(this.calculateLvlByExp(experience));
  }

  public int calculateLvlByExp(int experience)
  {
    if (experience < 1000)
      return 0;
    int level = 1;
    int etalon = 1000;
    while (etalon <= experience) {
      level++;
      etalon = level * 1000 + ((level - 1) * (level - 1)) * 450;
    }
    return level-1;
  }


  //  Weapon[3] weapons;
//          • Weapon - increases the attack
//• Armor - increases defense
//• Helm - increases hit points
}
