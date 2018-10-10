package ua.nekitoss;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Table(name = "test_table", schema = "main")
public class TableTestClass implements java.io.Serializable{

  private int id;
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
  @GenericGenerator(name = "native", strategy = "native")
  @Type(type="int")
  @Column(name = "id")
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }







  private String name;

  @Basic
  @Type(type="string")
  @Column(name = "name")
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }


  public TableTestClass() {
  }




  @Override
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
  }
}
