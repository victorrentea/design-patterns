package victor.training.patterns.adapter;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import org.junit.Test;

public class AdapterArchUnitTest {
   @Test
   public void dependencyInversionTest() {
      JavaClasses classes = new ClassFileImporter().importPackages("victor.training.patterns.structural.adapter");

      ArchRuleDefinition.noClasses()
          .that().resideInAPackage("..domain")
          .should()
          .dependOnClassesThat().resideInAPackage("..infra")
          .check(classes);

   }
}
