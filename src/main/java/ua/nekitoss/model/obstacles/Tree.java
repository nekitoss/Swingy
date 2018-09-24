package ua.nekitoss.model.obstacles;

import ua.nekitoss.model.AMapElement;

public class Tree extends AObstacle {
  public Tree(int x, int y){
    this();
    this.xPos = x;
    this.yPos = y;
  }

  public Tree() {
    this.mapSign = 'T';
    this.name = "Tree";
  }
}
