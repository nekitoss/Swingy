package ua.nekitoss.dbtest;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
//@Table(name = "tex_table")
@DiscriminatorValue("Tex2Class")
public class Tex2Class extends TableTestClass {

  private int extend2Int;
  public int getExtend2Int() {
    return extend2Int;
  }
  public void setExtend2Int(int extend2Int) {
    this.extend2Int = extend2Int;
  }

  public Tex2Class() {
  }
}
