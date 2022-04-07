package victor.training.patterns.creational.builder.stepbuilder;

public class Play {
   public static void main(String[] args) {

      UserConfiguration local = StepBuilder.newBuilder()
          .name("nume obligatoriu")
          .onLocalhost()
          .build();

      UserConfiguration remote = StepBuilder.newBuilder()
          .name("nume obligatoriu")
          .onRemotehost("remote")
          .credentials("u", "p")
          .build();
//

//      UserConfiguration config1 = StepBuilder.newBuilder()
//          .name("aaa")
//          .onRemotehost("http://localhost:9080")
//          .credentials("a", "p").build();
//
//      UserConfiguration config2 = StepBuilder.newBuilder()
//          .name("aaa")
//          .onLocalhost()
//          .build();
   }
}
