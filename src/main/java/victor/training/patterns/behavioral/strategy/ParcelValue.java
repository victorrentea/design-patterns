package victor.training.patterns.behavioral.strategy;

import java.util.Objects;

// 100% Value Object : no PK, immutable fields, and equals/hash based on all fields
public final class ParcelValue {
   private final double tobaccoValue;
   private final double regularValue;

   public ParcelValue(double tobaccoValue, double regularValue) {
      this.tobaccoValue = tobaccoValue;
      this.regularValue = regularValue;
   }

   public double tobaccoValue() {
      return tobaccoValue;
   }

   public double regularValue() {
      return regularValue;
   }

   @Override
   public boolean equals(Object obj) {
      if (obj == this) return true;
      if (obj == null || obj.getClass() != this.getClass()) return false;
      var that = (ParcelValue) obj;
      return Double.doubleToLongBits(this.tobaccoValue) == Double.doubleToLongBits(that.tobaccoValue) &&
             Double.doubleToLongBits(this.regularValue) == Double.doubleToLongBits(that.regularValue);
   }

   @Override
   public int hashCode() {
      return Objects.hash(tobaccoValue, regularValue);
   }

   @Override
   public String toString() {
      return "ParcelValue[" +
             "tobaccoValue=" + tobaccoValue + ", " +
             "regularValue=" + regularValue + ']';
   }

}