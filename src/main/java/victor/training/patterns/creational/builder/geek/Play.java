package victor.training.patterns.creational.builder.geek;

public class Play {
   public static void main(String[] args) {
      UserConfiguration config1 = StepBuilder.newBuilder()
          .name("a name")
          .onRemotehost("httpL///")
          .credentials("u","p")
          .build();


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
