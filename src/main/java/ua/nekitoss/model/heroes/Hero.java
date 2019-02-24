package ua.nekitoss.model.heroes;

import org.hibernate.annotations.GenericGenerator;
import ua.nekitoss.model.ASoul;
import ua.nekitoss.model.equipment.AEquip;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.Arrays;
import java.util.Date;

@Entity
@Table(name = "heroes")
public class Hero extends ASoul {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
  @GenericGenerator(name = "native", strategy = "native")
  @Column(name = "id")
  private int id;

  @Transient
  private static Hero instance;

  public static final int[][] PREDEFINED_HERO_STATS = {
//(attack, defense, hp);
    {20, 1, 10},  //attacker
    {1, 10, 10},  //defender
    {1, 1, 100},   //high_hp
    {10, 2, 30}   //default
  };
  public enum HeroClass {
    ATTACKER,
    DEFENDER,
    HIGH_HP,
    DEFAULT_HERO;

    public static String[] names() {
      return Arrays.toString(HeroClass.values()).replaceAll("^.|.$", "").split(", ");
    }
  }

  @NotNull
//  @Transient
  @Column(name = "classOfHero")
  protected HeroClass classOfHero;

  @PositiveOrZero
  @Column(name = "level")
  protected int level;

  @PositiveOrZero
  @Column(name = "experience")
  protected int experience;

  @Transient
  protected AEquip weapon;

  @Transient
  protected AEquip helm;

  @Transient
  protected AEquip armor;

  @Column(name = "updated")
  private Date saved;

  public Date getSaved() {
    return saved;
  }

  public void setSaved(Date saved) {
    this.saved = saved;
  }

  @PreUpdate
  protected void onUpdate() {
    saved = new Date();
  }

  protected Hero() {
    this.name = "DefaultHero";
    this.mapSign = 'H';
    this.classOfHero = HeroClass.DEFAULT_HERO;
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

  public Hero(Hero toClone){
    this.id = toClone.id;
    this.classOfHero = toClone.classOfHero;
    this.level = toClone.level;
    this.experience = toClone.experience;
    this.weapon = toClone.weapon;
    this.helm = toClone.helm;
    this.armor = toClone.armor;
    this.attack = toClone.attack;
    this.defense = toClone.defense;
    this.hp = toClone.hp;
    this.name = toClone.name;
    this.xPos = toClone.xPos;
    this.yPos = toClone.yPos;
    this.mapSign = toClone.mapSign;

    instance = this;
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

  public HeroClass getClassOfHero() {
    return classOfHero;
  }

  public void setClassOfHero(HeroClass classOfHero) {
    this.classOfHero = classOfHero;
  }

  public AEquip getWeapon() {
    return weapon;
  }

  public void setWeapon(AEquip weapon) {
    this.weapon = weapon;
  }

  public AEquip getHelm() {
    return helm;
  }

  protected void setHelm(AEquip helm) {
    this.helm = helm;
  }

  public boolean changeHelm(AEquip helm){
    if (this.helm == null) {
      this.helm = helm;
      return true;
    }
    else {
      if (this.helm.getStatIncrease() < this.getFullHp() || this.helm.getStatIncrease() < helm.getStatIncrease())
      {
        this.helm = helm;
        return true;
      }
      else //your hp holds on you helm
        return false;
    }
  }

  public AEquip getArmor() {
    return armor;
  }

  public void setArmor(AEquip armor) {
    this.armor = armor;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getFullHp(){
    return this.getHp() + (this.getHelm() == null ? 0 : this.getHelm().getStatIncrease());
  }

  public int getFullDefence(){
    return this.getDefense() + (this.getArmor() == null ? 0 : this.getArmor().getStatIncrease());
  }

  public int getFullAttack(){
    return this.getAttack() + (this.getWeapon() == null ? 0 : this.getWeapon().getStatIncrease());
  }

  //  Weapon[3] weapons;
//          • Weapon - increases the attack
//• Armor - increases defense
//• Helm - increases hit points
}
