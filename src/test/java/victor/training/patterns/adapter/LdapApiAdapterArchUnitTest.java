package victor.training.patterns.adapter;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import org.junit.Test;

public class LdapApiAdapterArchUnitTest {
   @Test
   public void dependencyInversionTest() {
      JavaClasses classes = new ClassFileImporter()
              .importPackages("victor.training.patterns.adapter");

      ArchRuleDefinition.noClasses()
          .that().resideInAPackage("..domain")
          .should()
          .dependOnClassesThat().resideInAPackage("..infra")
          .check(classes);
   }
}
