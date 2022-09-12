package victor.training.patterns.adapter;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import org.junit.Test;

public class AdapterArchUnitTest {
   @Test // WARNING: daca pica testu, suna-ma: 0720019564 sa-ti explic ce mama masii ise intampla aici.
   // 8pm burnout

   public void dependencyInversionTest() {
      JavaClasses classes = new ClassFileImporter().importPackages("victor.training.patterns.adapter");

      ArchRuleDefinition.noClasses()
          .that().resideInAPackage("..domain")
          .should()
          .dependOnClassesThat().resideInAPackage("..infra")
          .check(classes);
   }
}
