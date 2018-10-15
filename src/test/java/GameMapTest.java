import org.junit.Assert;
import org.junit.Test;
import ua.nekitoss.model.AMapElement;
import ua.nekitoss.model.GameMap;
import ua.nekitoss.model.heroes.Hero;
import ua.nekitoss.model.heroes.HeroBuilder;

import java.util.ArrayList;

public class GameMapTest {
  @Test
  public void wrongMapSize(){
    GameMap tmp = GameMap.getInstance();
    AMapElement[][] tmp2 = null;
    Assert.assertNull("wrong size (-1) doesn't returned null", tmp.createMap(-1));
    Assert.assertNull("wrong size (0) doesn't returned null", tmp.createMap(0));
    Assert.assertNull("wrong size (1) doesn't returned null", tmp.createMap(1));
    Assert.assertNull("wrong size (20%2=0) doesn't returned null", tmp.createMap(20));
    tmp2 = tmp.map;
    Assert.assertNotNull("correct size (20) doesn't returned obj", tmp.createMap(21));
    Assert.assertNotEquals("arrays are equal", tmp.map, tmp2);
    Hero h = HeroBuilder.createAttacker("");
    Assert.assertTrue("wrong return of placeOnMap(0,0), must be empty cell", tmp.placeOnMap(h, 0, 0));
    Assert.assertFalse("Doesn't place soul on map", tmp.isFree(0, 0));
    Assert.assertTrue("wrong return of isFree, must be empty cell", tmp.isFree(1, 1));
    Assert.assertFalse("wrong return of placeOnMap(-1,0)", tmp.placeOnMap(h,-1,0));
    Assert.assertFalse("wrong return of placeOnMap(0,-1)", tmp.placeOnMap(h,0,-1));
    Assert.assertFalse("wrong return of placeOnMap(0,21)", tmp.placeOnMap(h,0,21));
    Assert.assertFalse("wrong return of placeOnMap(21,0)", tmp.placeOnMap(h,21,0));
    Assert.assertFalse("wrong return of deleteFromMap(-1,0)", tmp.deleteFromMap(-1,0));
    Assert.assertFalse("wrong return of deleteFromMap(0,-1)", tmp.deleteFromMap(0,-1));
    Assert.assertFalse("wrong return of deleteFromMap(0,21)", tmp.deleteFromMap(0,21));
    Assert.assertFalse("wrong return of deleteFromMap(21,0)", tmp.deleteFromMap(21,0));
    Assert.assertFalse("wrong return of delFromMap(1,1), must be empty cell", tmp.deleteFromMap(1, 1));
    Assert.assertTrue("wrong return of delFromMap(0,0), must be Not empty cell", tmp.deleteFromMap(0, 0));

    Assert.assertFalse("wrong return of coordinatesValid(-1,0)", GameMap.coordinatesValid(-1,0));
    Assert.assertFalse("wrong return of coordinatesValid(0,-1)", GameMap.coordinatesValid(0,-1));
    Assert.assertFalse("wrong return of coordinatesValid(0,21)", GameMap.coordinatesValid(0,21));
    Assert.assertFalse("wrong return of coordinatesValid(21,0)", GameMap.coordinatesValid(21,0));
    Assert.assertTrue("wrong return of coordinatesValid(0,0)", GameMap.coordinatesValid(0,0));

  }
}
