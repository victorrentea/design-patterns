package victor.training.patterns.behavioral.observer;

import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.EvaluationResult;
import com.tngtech.archunit.library.dependencies.SliceRule;
import com.tngtech.archunit.library.dependencies.SlicesRuleDefinition;
import org.junit.Test;

import java.util.List;

import static com.tngtech.archunit.base.DescribedPredicate.alwaysTrue;
import static com.tngtech.archunit.core.domain.JavaClass.Predicates.resideInAPackage;
import static java.util.stream.Collectors.joining;
import static org.assertj.core.api.Assertions.assertThat;

public class ObserverArchUnitTest {

   @Test
   public void independentSubdomains() {
      JavaClasses classes = new ClassFileImporter()
          .importPackages("victor.training.patterns.behavioral.observer");

      String names = classes.stream().map(JavaClass::getSimpleName).collect(joining());
      System.out.println("Studying classes: " + names);

      SliceRule sliceRule = SlicesRuleDefinition.slices()
          .matching("..observer.(**)")
          .should().notDependOnEachOther()
          .ignoreDependency(alwaysTrue(), resideInAPackage("..events")); // allow dependencies to .events

      // Option 1 (existing code): progressively reducing the unwanted dependencies (eg strangling the monolith)
      EvaluationResult evaluationResult = sliceRule.evaluate(classes);
      List<String> violations = evaluationResult.getFailureReport().getDetails();
      assertThat(violations)
          .describedAs("subdomains should not depend on eachother")
          .hasSizeLessThan(10);

      // Option 2 (new project): fail at any deviation
      sliceRule.check(classes);

   }
}
