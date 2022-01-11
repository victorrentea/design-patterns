package victor.training.patterns.assignment;

public class Lemon {
   private Long id;
   private String gisLocation;
   private Integer battery;
   private LemonType type;

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getGisLocation() {
      return gisLocation;
   }

   public void setGisLocation(String gisLocation) {
      this.gisLocation = gisLocation;
   }

   public Integer getBattery() {
      return battery;
   }

   public void setBattery(Integer battery) {
      this.battery = battery;
   }

   public LemonType getType() {
      return type;
   }

   public void setType(LemonType type) {
      this.type = type;
   }

   enum LemonType {
      SMALL(20), MEDIUM(25), LARGE(31);

      public final int maxSpeed;

      LemonType(int maxSpeed) {
         this.maxSpeed = maxSpeed;
      }
   }
}
