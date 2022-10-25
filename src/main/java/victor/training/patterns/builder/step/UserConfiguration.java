package victor.training.patterns.builder.step;

public class UserConfiguration {
   private final String name;
   private ServerDetails serverDetails;

   public UserConfiguration(String name) {
      this.name = name;
   }

   public void setServerDetails(ServerDetails serverDetails) {
      this.serverDetails = serverDetails;
   }

   public String getName() {
      return name;
   }

   public ServerDetails getServerDetails() {
      return serverDetails;
   }
}
