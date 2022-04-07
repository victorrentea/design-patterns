package victor.training.patterns.creational.singleton;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

public class CanFaculta {

   public static void main(String[] args) {
//      SingletonPeSpringByDefault i1 = SingletonPeSpringByDefault.getInstance();
   }

   @Autowired
   private SingletonPeSpringByDefault singletonPeSpringByDefault;

   public void logicaGrea() {
      // complexe
      // complexe
      singletonPeSpringByDefault.faCeva();
      // complexe
      // complexe

   }
}

@Service //@Component @RestController
class SingletonPeSpringByDefault {
   // - cod stupid idio(t)matic
   // - testare varza
   // - nu poate un Spring (DI container) sa-ti dea PROXY-uri la obiectele dorite

//   private static SingletonPeStiluVechi INSTANCE;
//   private SingletonPeStiluVechi() {}
//
//   public static SingletonPeStiluVechi getInstance() {
//      if (INSTANCE == null)  {
//         INSTANCE = new SingletonPeStiluVechi();
//      }
//      return INSTANCE;
//   }

   // WARNING: pe beanurile spring nu tii stare specifica req curent
//   private String currentUsername; // mutable state + multithreading (ca in context web) = 💀
   private final X x;

   public static boolean isPrimary;

   public SingletonPeSpringByDefault(X x) {
      this.x = x;
   }

   public void fatePrimary(String event) {
      isPrimary = true;

   }

   public void method() {
      if (isPrimary) {

      }

   }

   @Transactional
   public void faCeva() {
//      System.out.println(currentUsername);
   }

   @Transactional
   public void setCurrentUsername(String n) {
//      currentUsername=n;
   }
}

@Slf4j
@RequiredArgsConstructor
@Service
class X {
   private final SingletonPeSpringByDefault inapoi;

}