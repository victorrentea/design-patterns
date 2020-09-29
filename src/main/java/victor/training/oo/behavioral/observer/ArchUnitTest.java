package victor.training.oo.behavioral.observer;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import org.junit.Test;

public class ArchUnitTest {
   @Test
   public void check() {
      JavaClasses importedClasses = new ClassFileImporter().importPackages("victor");

      ArchRule rule = ArchRuleDefinition.classes().that().resideInAPackage("..repo..")
          .should().onlyBeAccessed().byAnyPackage("..facade..", "..service..");


      rule.evaluate(importedClasses).handleViolations((collection, s) -> System.out.printf(collection + ""));
//      rule.check(importedClasses);

      ArchRule rule2 = ArchRuleDefinition.classes().that().resideInAPackage("..service..")
          .should().onlyBeAccessed().byAnyPackage("..service..","..controller..");
   }
}
