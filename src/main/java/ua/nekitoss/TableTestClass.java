package ua.nekitoss;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "test_table")
public class TableTestClass {
  @Id @GeneratedValue(strategy=GenerationType.AUTO)
//  @Column(name = "id")
//  @Type(type="int")
  private int id;
//  @Basic
//  @Type(type="string")
//  @Column(name = "name")
  private String name;

  public TableTestClass() {
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
