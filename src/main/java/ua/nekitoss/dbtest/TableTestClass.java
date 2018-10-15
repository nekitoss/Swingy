package ua.nekitoss.dbtest;

import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@DiscriminatorValue("TableTestClass")
@Table(name = "test_table", schema = "main")
public class TableTestClass extends SuperClass{


  @Basic
  @Type(type="string")
  @Column(name = "name")
  private String name;
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }


  @OneToOne(cascade = CascadeType.ALL)
//  @Transient
//  @OneToOne(cascade = CascadeType.ALL, mappedBy = "father")//fetch = FetchType.LAZY,
//  @PrimaryKeyJoinColumn
  @JoinColumn(name = "slave_id")

  private SlaveClass slaveInfo;
  public SlaveClass getSlaveInfo() {
    return slaveInfo;
  }
  public void setSlaveInfo(SlaveClass slaveInfo) {
    this.slaveInfo = slaveInfo;
  }

  public TableTestClass() {
    this.slaveInfo = new SlaveClass();
  }




  /*@Override
  public String toString() {
    return "TableTestClass{" +
            "id=" + id +
            ", name='" + name + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    TableTestClass that = (TableTestClass) o;

    if (id != that.id) return false;
    if (name != null ? !name.equals(that.name) : that.name != null) return false;

    return true;
  }

  @Override
  public int hashCode() {
    return super.hashCode();
  }*/
}
