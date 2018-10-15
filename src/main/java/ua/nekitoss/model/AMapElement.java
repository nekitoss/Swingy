package ua.nekitoss.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;

@MappedSuperclass
public abstract class AMapElement {

  @NotEmpty
  @Column(name = "name")
  protected String name = "";

  @Transient
  protected int xPos = 0;
  @Transient
  protected int yPos = 0;
  @NotEmpty
  @Column(name = "mapSign")
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
