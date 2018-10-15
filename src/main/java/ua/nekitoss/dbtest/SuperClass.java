package ua.nekitoss.dbtest;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn
public abstract class SuperClass {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
  @GenericGenerator(name = "native", strategy = "native")
  @Type(type="int")
  @Column(name = "id")
  private int id;
  public int getId() {
    return id;
  }
  public void setId(int id) {
    this.id = id;
  }

}
