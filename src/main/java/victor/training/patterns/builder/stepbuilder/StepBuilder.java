package victor.training.patterns.builder.stepbuilder;

/**
 * "Step Builder"
 */
public class StepBuilder {
   public static NameStep newBuilder() {
      return new Steps();
   }

   private StepBuilder() {
   }

   public interface NameStep {
      ServerStep name(String name);
   }

   public interface ServerStep {
      BuildStep onLocalhost();
      CredentialsStep onRemotehost(String host);
   }

   public interface CredentialsStep {
      BuildStep credentials(String user, String password);
   }

   public interface BuildStep {
      UserConfiguration build();
   }

   private static class Steps implements NameStep, ServerStep, CredentialsStep, BuildStep {
      private String name;
      private String host;
      private String user;
      private String password;

      public BuildStep onLocalhost() {
         this.host = "localhost";
         return this;
      }

      public ServerStep name(String name) {
         this.name = name;
         return null;
      }

      public CredentialsStep onRemotehost(String host) {
         this.host = host;
         return this;
      }

      public BuildStep credentials(String user, String password) {
         this.user = user;
         this.password = password;
         return this;
      }

      public UserConfiguration build() {
         UserConfiguration userConfiguration = new UserConfiguration(name);
         ServerDetails serverDetails = new ServerDetails(host);
         serverDetails.setUser(user);
         serverDetails.setPassword(password);
         userConfiguration.setServerDetails(serverDetails);
         return userConfiguration;
      }

   }
}