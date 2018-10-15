package ua.nekitoss.dbtest;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
//@Table(name = "tex_table")
@DiscriminatorValue("TexClass")
public class TexClass extends TableTestClass {

  private int extendInt;
  public int getExtendInt() {
    return extendInt;
  }
  public void setExtendInt(int extendInt) {
    this.extendInt = extendInt;
  }

  public TexClass() {
  }
}
