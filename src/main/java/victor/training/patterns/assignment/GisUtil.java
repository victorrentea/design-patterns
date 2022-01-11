package victor.training.patterns.assignment;

public class GisUtil {
   public static int getDistance(String gis1, String gis2) {
      return gis1.hashCode() - gis2.hashCode(); // silly implementation, sorry
   }
}
