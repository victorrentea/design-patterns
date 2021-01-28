package victor.training.patterns.behavioral.observer;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.library.dependencies.SliceRule;
import com.tngtech.archunit.library.dependencies.SlicesRuleDefinition;
import org.junit.Test;

public class ObserverArchUnitTest {

   @Test
   public void independentSubdomains() {
      JavaClasses classes = new ClassFileImporter().importPackages("victor.training.patterns.behavioral.observer");

      SliceRule sliceRule = SlicesRuleDefinition.slices()
          .matching("..observer.(**)")
          .should().notDependOnEachOther();

      sliceRule.check(classes);


   }
}
