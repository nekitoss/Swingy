package ua.nekitoss.model.equipment;

import lombok.Data;

@Data
public abstract class AEquip {
  public enum EquipType{
    WEAPON,
    ARMOR,
    HELM
  }
  String name;
  int statkIncrease;
}
