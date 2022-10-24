package victor.training.patterns.adapter;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import org.junit.Test;
import org.junit.jupiter.api.Disabled;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

public class AdapterArchUnitTest {
   @Test // if you don't get what's up here,
   // WhatsApp me: +40720019564 (the architecT)
   public void dependencyInversionTest() {
      JavaClasses classes = new ClassFileImporter().importPackages("victor.training.patterns.adapter");

      noClasses().that().resideInAPackage("..domain")
          .should()
          .dependOnClassesThat().resideInAPackage("..infra")
          .check(classes);
   }
}
