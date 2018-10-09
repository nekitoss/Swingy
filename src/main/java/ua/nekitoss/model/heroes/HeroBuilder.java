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
  private Hero.HeroClass classOfHero;
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
  public HeroBuilder setHeroName(String name) {
    this.name = name;
    return this;
  }

  @Override
  public HeroBuilder setHeroXPos(int xPos) {
    this.xPos = xPos;
    return this;
  }

  @Override
  public HeroBuilder setHeroYPos(int yPos) {
    this.yPos = yPos;
    return this;
  }

  @Override
  public HeroBuilder setHeroClass(Hero.HeroClass class4Hero) {
    this.classOfHero = class4Hero;
    return this;
  }

  @Override
  public HeroBuilder setHeroLvl(int level) {
    this.level = level;
    return this;
  }

  @Override
  public HeroBuilder setHeroExp(int experience) {
    this.experience = experience;
    return this;
  }

  @Override
  public HeroBuilder setHeroAttack(int attack) {
    this.attack = attack;
    return this;
  }

  @Override
  public HeroBuilder setHeroDefence(int defense) {
    this.defense = defense;
    return this;
  }

  @Override
  public HeroBuilder setHeroHp(int hp) {
    this.hp = hp;
    return this;
  }

  @Override
  public HeroBuilder setHeroMapSign(char sign) {
    this.mapSign = sign;
    return this;
  }

  public Hero getResult(){
    return new Hero(name, xPos, yPos, mapSign, classOfHero, level, experience, attack, defense, hp);
  }

  public static Hero createAttacker(){
    return new Hero("DefaultAttacker", 0, 0, 'A', Hero.HeroClass.attacker, 0, 0, 20, 2, 10);
  }
}