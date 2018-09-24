package ua.nekitoss.model;


import lombok.Getter;
import java.util.Random;

public class GameMap {
  static int mapCenter;

  @Getter
  static int size;
  @Getter
  public AMapElement[][] map;
//  char charMap[][];

//  public GameMap() {
//  }
//
//  public GameMap(int size) {
//    this =
//  }

  public GameMap createMap(int size){
    if (size < 5) {
      System.err.println("wrong map size to create:" + size);
      return null;
    }
    if (size % 2 != 1) {
      System.err.println("map is not odd (%2!=1)");
      return null;
    }
    this.size = size;
    mapCenter = size / 2 + 1;
    map = null;
    map = new AMapElement[size][size];
    return this;
  }

  public boolean placeRandomly(AMapElement soul){
//    Random r = new Random(System.currentTimeMillis());
    Random r = new Random();
//    System.out.println(System.currentTimeMillis());
    int x = r.nextInt(size);
    int y = r.nextInt(size);
//    System.out.println("x="+x+"y="+y);
    if (map[y][x] != null)
      return false;
    map[y][x] = soul;
    soul.xPos = x;
    soul.yPos = y;
    return true;
  }

  public boolean placeOnMap(AMapElement braveSoul, int x, int y){
    if (x < 0 || y < 0 || x > size-1 || y > size-1 || map[y][x] != null)
      return false;
    map[y][x] = braveSoul;
    braveSoul.xPos = x;
    braveSoul.yPos = y;
    return true;
  }

  public boolean deleteFromMap(int x, int y){
    if (x < 0 || y < 0 || x > size-1 || y > size-1 || map[y][x] == null)
      return false;
    map[y][x] = null;
    return true;
  }

  public void printMap(){
    if (this.map != null){
      for (int i=0; i < this.size; i++){
        for (int j=0; j < this.size; j++){
          if (map[i][j] == null)
            System.out.print('.');
          else
            System.out.print((map[i][j]).mapSign);
        }
        System.out.println();
      }
    }
  }

  public boolean isFree(int x, int y){
    if (map[y][x] != null)
      return false;
    return true;
  }

  public AMapElement getSoul(int x, int y){
    return (map[y][x]);
  }

  public static boolean coordinatesValid(int x, int y){
    if (x < 0 || y < 0 || x > size-1 || y > size-1)
      return false;
    return true;
  }


}
