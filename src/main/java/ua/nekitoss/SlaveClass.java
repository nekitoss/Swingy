package ua.nekitoss;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Table(name = "slave_table")
public class SlaveClass {


  @Id
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
  @GenericGenerator(name = "native", strategy = "native")
//  @GeneratedValue(generator = "foreign")
//  @GenericGenerator(name = "foreign", strategy="foreign", parameters = {@org.hibernate.annotations.Parameter(name="property", value = "id")})
  @Type(type="int")
  @Column(name = "slave_id")
  private int slave_id;
  public int getSlave_id() {
    return slave_id;
  }
  public void setSlave_id(int id) {
    this.slave_id = id;
  }


  @Type(type="string")
  @Column(name = "info")

  private String info;
  public String getInfo() {
    return info;
  }
  public void setInfo(String info) {
    this.info = info;
  }


  @OneToOne(mappedBy = "slaveInfo")
  TableTestClass father;
  public TableTestClass getFather() {
    return father;
  }
  public void setFather(TableTestClass father) {
    this.father = father;
  }

  public SlaveClass() {
  }
}
