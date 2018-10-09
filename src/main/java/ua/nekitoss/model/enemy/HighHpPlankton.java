package ua.nekitoss.model.enemy;

public class HighHpPlankton extends AEnemy {

  public HighHpPlankton(int x, int y){
    super("HighHpPlankton", 'B', 1000, 0, 0, 123);
    this.xPos = x;
    this.yPos = y;
  }

  public HighHpPlankton() {
    this(0,0);
  }

  public HighHpPlankton(int addNumToName) {
    this(0,0);
    this.name += addNumToName;
  }
}
