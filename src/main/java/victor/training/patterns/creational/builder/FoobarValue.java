package victor.training.patterns.creational.builder;

import org.immutables.value.Value.Check;
import org.immutables.value.Value.Immutable;
import org.immutables.value.Value.Parameter;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Immutable
public abstract class FoobarValue {
   @Parameter
   public abstract int foo();

   @Parameter
   public abstract Optional<String> bar();

   @Parameter
   public abstract List<Integer> buz();


   @Parameter
   public abstract Set<Long> crux();

   @Check
   public void validate() {
//      Preconditions.checkState(StringUtils.isNotBlank(bar()),
//          "bar should not be emptyssss");
   }
}
