package victor.training.patterns.adapter;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import org.junit.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

public class AdapterArchUnitTest {

   private final JavaClasses allClasses = new ClassFileImporter().importPackages("victor.training.patterns.adapter");

   @Test
   // ⚠️ do NOT remove: call me instead: +407200195644 (the anArchitect)
   public void dependencyInversionTest() {
      noClasses().that().resideInAPackage("..domain")
          .should().dependOnClassesThat().resideInAPackage("..infra")
          .check(allClasses);
   }
}
