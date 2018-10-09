package ua.nekitoss.model.heroes;

import lombok.Getter;
import lombok.Setter;
import ua.nekitoss.model.AMapElement;
import ua.nekitoss.model.ASoul;
import ua.nekitoss.model.equipment.AEquip;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

public  class Hero extends ASoul {
  private static Hero instance;
  public enum HeroClass {
    attacker,
    firstHeroClassType,
    secondHeroClassType,
    defaultHero
  }

  @NotNull
  @Getter
  @Setter
  protected HeroClass classOfHero;
  @PositiveOrZero
  protected int level;
  @PositiveOrZero
  protected int experience;
  @Getter
  @Setter
  protected AEquip weapon;
  @Getter
  @Setter
  protected AEquip helm;
  @Getter
  @Setter
  protected AEquip armor;

  private Hero() {
    this.name = "DefaultHero";
    this.mapSign = 'H';
    this.classOfHero = HeroClass.defaultHero;
    this.level = 0;
    this.experience = 0;
    this.attack = 0;
    this.defense = 0;
    this.hp = 1;
    instance = this;
  }

  public static Hero getInstance(){
    if (instance == null){
      instance = new Hero();
    }
    return instance;
  }

  public Hero(@NotEmpty String name, int xPos, int yPos, @NotEmpty char mapSign, @NotNull HeroClass classOfHero, @PositiveOrZero int level, @PositiveOrZero int experience, @PositiveOrZero int attack, @PositiveOrZero int defense, @Positive int hp) {
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
    instance = this;
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

  public boolean setExperience(int experience) {
    this.experience = experience;
    int oldLvl = this.level;
    this.setLevel(this.calculateLvlByExp(experience));
    if (oldLvl != this.level)
      return true;
    return false;
  }

  public boolean addExperience(int experience) {
    return (this.setExperience(getExperience() + experience));
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

  @Override
  public String toString() {
    return "Hero{" +
            "classOfHero=" + classOfHero +
            ", level=" + level +
            ", experience=" + experience +
            ", attack=" + attack +
            ", defense=" + defense +
            ", hp=" + hp +
            ", name='" + name + '\'' +
            ", xPos=" + xPos +
            ", yPos=" + yPos +
            ", mapSign=" + mapSign +
            '}';
  }

  //  Weapon[3] weapons;
//          • Weapon - increases the attack
//• Armor - increases defense
//• Helm - increases hit points
}
