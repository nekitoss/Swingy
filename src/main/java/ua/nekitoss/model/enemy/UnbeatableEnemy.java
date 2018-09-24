package ua.nekitoss.model.enemy;

public class UnbeatableEnemy extends AEnemy {

  public UnbeatableEnemy(int x, int y){
    super("UnbeatableEnemy", 'U', 0, 10000, 10000, 999999);
    this.xPos = x;
    this.yPos = y;
  }

  public UnbeatableEnemy() {
    this(0,0);
  }
}
