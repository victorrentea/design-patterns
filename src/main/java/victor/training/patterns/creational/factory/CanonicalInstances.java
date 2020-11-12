package victor.training.patterns.creational.factory;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.*;

public class CanonicalInstances {
   public static void main(String[] args) {
      Random r = new Random();
      List<Coord> coords = new ArrayList<>();
      for (int i = 0; i < 10_000_000; i++) {
         int x = r.nextInt(1000);
         int y = r.nextInt(1000);
//         coords.add(new Coord(x, y)); // 305 MB on my machine
         coords.add(Coord.forCoords(x, y)); // 146 MB on my machine
      }


      System.gc();

      System.out.println("Measure used memory now with jvisualvm after triggering a Full GC");
      System.out.println("Program paused. [ENTER] to continue");
      new Scanner(System.in).nextLine();
      System.out.println(coords.size());

      coords = null;

      System.gc();

      System.out.println("Measure used memory now again (no reference left) with jvisualvm after triggering a Full GC");
      System.out.println("Program paused. [ENTER] to continue");
      System.out.println("Entries: " + Coord.CURRENT_INSTANCES.size()); // Critical Line: size() will call java.util.WeakHashMap.expungeStaleEntries that clears entries with null keys
      new Scanner(System.in).nextLine();
      // 84 MB after
      System.out.println("Entries: " + Coord.CURRENT_INSTANCES.size());
      System.out.println(Coord.CURRENT_INSTANCES.entrySet().iterator().next().getKey());
   }
}

class Coord {
   private final int x;
   private final int y;

   Coord(int x, int y) {
      this.x = x;
      this.y = y;
   }

   public static Coord forCoords(int x, int y) {
      Coord newCoord = new Coord(x, y);
      if (!CURRENT_INSTANCES.containsKey(newCoord)) {
         CURRENT_INSTANCES.put(newCoord, new WeakReference<>(newCoord));
      }
      return CURRENT_INSTANCES.get(newCoord).get();
   }
   public static WeakHashMap<Coord, WeakReference<Coord>> CURRENT_INSTANCES = new WeakHashMap<>(); // this map is emptied automatically when its values are not referenced anywhere else

   public int getX() {
      return x;
   }

   public int getY() {
      return y;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Coord coord = (Coord) o;
      return x == coord.x &&
             y == coord.y;
   }

   @Override
   public int hashCode() {
      return Objects.hash(x, y);
   }
}