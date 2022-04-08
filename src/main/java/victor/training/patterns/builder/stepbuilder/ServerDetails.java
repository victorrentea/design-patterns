package victor.training.patterns.builder.stepbuilder;

public class ServerDetails {

   private final String host;
   private String user;
   private String password;

   public ServerDetails(String host) {
      this.host = host;
   }

   public void setUser(String user) {
      this.user = user;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   public String getHost() {
      return host;
   }

   public String getUser() {
      return user;
   }

   public String getPassword() {
      return password;
   }
}