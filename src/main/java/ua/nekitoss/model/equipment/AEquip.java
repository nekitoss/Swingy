package ua.nekitoss.model.equipment;

import lombok.Data;

import javax.persistence.Entity;

@Entity
public class AEquip {
  public enum EquipType{
    WEAPON,
    ARMOR,
    HELM
  }

  String name;

  int statIncrease;

  EquipType type;

  protected AEquip() {
  }

  public AEquip(String name, int statIncrease, EquipType type) {
    this.name = name;
    this.statIncrease = statIncrease;
    this.type = type;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getStatIncrease() {
    return statIncrease;
  }

  public void setStatIncrease(int statIncrease) {
    this.statIncrease = statIncrease;
  }

  public EquipType getType() {
    return type;
  }

  public void setType(EquipType type) {
    this.type = type;
  }
}
