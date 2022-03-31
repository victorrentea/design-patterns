package victor.training.patterns.structural.proxy;

import lombok.Value;

import java.util.Objects;

class Parinte {

}

public class ClasaCuDate extends Parinte {
   private String a,b,c;

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      ClasaCuDate that = (ClasaCuDate) o;
      return Objects.equals(a, that.a) && Objects.equals(b, that.b) && Objects.equals(c, that.c);
   }

   @Override
   public int hashCode() {
      return Objects.hash(a, b, c);
   }
}
