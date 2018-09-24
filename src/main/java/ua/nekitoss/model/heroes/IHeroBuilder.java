package ua.nekitoss.model.heroes;

public interface IHeroBuilder {
  public IHeroBuilder setHeroName(String name);
  public IHeroBuilder setHeroXPos(int xPos);
  public IHeroBuilder setHeroYPos(int yPos);
  public IHeroBuilder setHeroClass(Hero.HeroClass class4Hero);
  public IHeroBuilder setHeroLvl(int level);
  public IHeroBuilder setHeroExp(int experience);
  public IHeroBuilder setHeroAttack(int attack);
  public IHeroBuilder setHeroDefence(int defense);
  public IHeroBuilder setHeroHp(int hp);
  public IHeroBuilder setHeroMapSign(char sign);
}
