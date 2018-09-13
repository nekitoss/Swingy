package ua.nekitoss.model.heroes;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

public class HeroBuilder implements IHeroBuilder{

  @NotEmpty
  private String name;
  private int xPos = 0;
  private int yPos = 0;
  @NotEmpty
  private char mapSign;
  @NotNull
  private AHero.HeroClass classOfHero;
  @PositiveOrZero
  private int level;
  @PositiveOrZero
  private int experience;
  @PositiveOrZero
  private int attack;
  @PositiveOrZero
  private int defense;
  @Positive
  private int hp;


  @Override
  public void setHeroName(String name) {
    this.name = name;
  }

  @Override
  public void setHeroXPos(int xPos) {
    this.xPos = xPos;
  }

  @Override
  public void setHeroYPos(int yPos) {
    this.yPos = yPos;
  }

  @Override
  public void setHeroClass(AHero.HeroClass class4Hero) {
    this.classOfHero = class4Hero;
  }

  @Override
  public void setHeroLvl(int level) {
    this.level = level;
  }

  @Override
  public void setHeroExp(int experience) {
    this.experience = experience;
  }

  @Override
  public void setHeroAttack(int attack) {
    this.attack = attack;
  }

  @Override
  public void setHeroDefence(int defense) {
    this.defense = defense;
  }

  @Override
  public void setHeroHp(int hp) {
    this.hp = hp;
  }

  @Override
  public void setHeroMapSign(char sign) {
    this.mapSign = sign;
  }

  public AHero getResult(){
    return new Attacker();//name, xPos, yPos, mapSign, classOfHero, level, experience, attack, defense, hp);
  }
}
