package ua.nekitoss.model;


import java.util.Random;

public class GameMap {
  private static GameMap instance;
  public static int mapCenter;

  private static int size;
  public AMapElement[][] map;
//  char charMap[][];

  private GameMap() {
  }

  public static GameMap getInstance(){
    if (instance == null){
      instance = new GameMap();
    }
    return instance;
  }

  public GameMap createMap(int sizeToCreate){
    if (sizeToCreate < 5) {
      System.err.println("wrong map size to create:" + sizeToCreate);
      return null;
    }
    if (sizeToCreate % 2 != 1) {
      System.err.println("map is not odd (%2!=1)");
      return null;
    }
    size = sizeToCreate;
    mapCenter = sizeToCreate / 2;
    map = null;
    map = new AMapElement[sizeToCreate][sizeToCreate];
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
      for (int i=0; i < size; i++){
        for (int j=0; j < size; j++){
          if (map[i][j] == null)
            System.out.print('.');
          else
            System.out.print((map[i][j]).mapSign);
        }
        System.out.println();
      }
    }
  }

  public String mapAsString(){
    StringBuffer tmp = new StringBuffer();
    if (this.map != null){
      for (int i=0; i < size; i++){
        for (int j=0; j < size; j++){
          if (map[i][j] == null)
            tmp.append('X');
          else
            tmp.append((map[i][j]).mapSign);
        }
        tmp.append('\n');
      }
    }
    return tmp.toString();
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

  public static int getSize() {
    return size;
  }

  public int getCenter(){
    return mapCenter;
  }

  public AMapElement[][] getMap() {
    return map;
  }
}
