package ua.nekitoss.model.heroes;

public interface IHeroBuilder {
  public void setHeroName(String name);
  public void setHeroXPos(int xPos);
  public void setHeroYPos(int yPos);
  public void setHeroClass(AHero.HeroClass class4Hero);
  public void setHeroLvl(int level);
  public void setHeroExp(int experience);
  public void setHeroAttack(int attack);
  public void setHeroDefence(int defense);
  public void setHeroHp(int hp);
  public void setHeroMapSign(char sign);
}
