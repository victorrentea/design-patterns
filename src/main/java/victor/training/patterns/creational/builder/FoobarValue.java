package victor.training.patterns.creational.builder;

import com.google.common.base.Preconditions;
import org.apache.commons.lang.StringUtils;
import org.immutables.value.Value.Check;
import org.immutables.value.Value.Immutable;

import java.util.List;
import java.util.Set;
@Immutable
public abstract class FoobarValue {
   public abstract int foo();

   public abstract String bar();

   public abstract List<Integer> buz();


   public abstract Set<Long> crux();

   @Check
   public void validate() {
      Preconditions.checkState(StringUtils.isNotBlank(bar()),
          "bar should not be emptyssss");
   }
}
