package ua.nekitoss.model;

import javax.validation.constraints.NotEmpty;

public abstract class AMapElement {
  @NotEmpty
  protected String name = "";
  protected int xPos = 0;
  protected int yPos = 0;
  @NotEmpty
  protected char mapSign;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getxPos() {
    return xPos;
  }

  public void setxPos(int xPos) {
    this.xPos = xPos;
  }

  public int getyPos() {
    return yPos;
  }

  public void setyPos(int yPos) {
    this.yPos = yPos;
  }

  public char getMapSign() {
    return mapSign;
  }

  public void setMapSign(char mapSign) {
    this.mapSign = mapSign;
  }
}
