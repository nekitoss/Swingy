package ua.nekitoss.dbtest;

import javax.persistence.*;

@Entity
@Table(name = "test_table", schema = "main", catalog = "")
public class TestTableEntity {
  private short id;
  private String name;

  @Id
  @Column(name = "id")
  public short getId() {
    return id;
  }

  public void setId(short id) {
    this.id = id;
  }

  @Basic
  @Column(name = "name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    TestTableEntity that = (TestTableEntity) o;

    if (id != that.id) return false;
    if (name != null ? !name.equals(that.name) : that.name != null) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = (int) id;
    result = 31 * result + (name != null ? name.hashCode() : 0);
    return result;
  }
}
