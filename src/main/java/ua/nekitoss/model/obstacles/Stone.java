package ua.nekitoss.model.obstacles;

import ua.nekitoss.model.AMapElement;

public class Stone extends AObstacle {
  public Stone(int x, int y){
    this();
    this.xPos = x;
    this.yPos = y;
  }

  public Stone() {
    this.mapSign = 'S';
    this.name = "Stone";
  }
}
