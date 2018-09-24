package ua.nekitoss.model.enemy;

public class Plankton extends AEnemy {

  public Plankton(int x, int y){
    super("Plankton", 'P', 10, 0, 0, 1);
    this.xPos = x;
    this.yPos = y;
  }

  public Plankton() {
    this(0,0);
  }
}
