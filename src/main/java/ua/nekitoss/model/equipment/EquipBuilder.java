package ua.nekitoss.model.equipment;

import java.util.Random;

public class EquipBuilder {

  private static final String[] weaponNames = {"kitty", "TheGreatSword", "banana"};
  private static final int[] weaponStats = {100, 1, 0};

  private static final String[] armorNames = {"T-shirt", "socks", "Titanium armor"};
  private static final int[] armorStats = {1, 20, 0};

  private static final String[] helmetNames = {"flight cap", "sombrero", "driver cap"};
  private static final int[] helmetStats = {10, 5, 0};


  public static AEquip generateRandomEquip(){
    Random r = new Random();

    int typeNum = r.nextInt(3);

    if (typeNum == 0) {//weapon
      int subTypeNum = r.nextInt(weaponNames.length);
      return (new AEquip(weaponNames[subTypeNum], weaponStats[subTypeNum], AEquip.EquipType.WEAPON));
    }
    else if (typeNum == 1) {
      int subTypeNum = r.nextInt(armorNames.length);
      return (new AEquip(armorNames[subTypeNum], armorStats[subTypeNum], AEquip.EquipType.ARMOR));
    }
    else {
      int subTypeNum = r.nextInt(helmetNames.length);
      return (new AEquip(helmetNames[subTypeNum], helmetStats[subTypeNum], AEquip.EquipType.HELM));
    }
  }

}
