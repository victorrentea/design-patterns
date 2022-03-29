package victor.training.patterns.structural.proxy;

import javax.xml.parsers.SAXParserFactory;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ImmutableObject {
   private final String name;
   private final List<String> phoneNumbers;

   public ImmutableObject(String name, List<String> phoneNumbers) {
      this.name = name;
      this.phoneNumbers = phoneNumbers;
   }

   public String getName() {
      return name;
   }

   public List<String> getPhoneNumbers() {
      SAXParserFactory saxParserFactory = SAXParserFactory.newInstance(); // the actual type returned to you depends on what LIBRARIES jar you have in classpath

//      return new ArrayList<>(phoneNumbers); // malloc allocates memory clones the list  = waste of memory.
//      return new UnmodifiableList(phoneNumbers); // they choose to hide the actual type returned. why ?
      // static factory method instead of a construtor call can hide the type returned
      // they allow freedom about what type they (the framework) retrn

//      asd
//      asdsada
//      a
      return Collections.unmodifiableList(phoneNumbers);
   }
}

class CanIChangeThatImmutableObjectAfterConstruction {
   public static void main(String[] args) {
      List<String> phones = new ArrayList<>();
      phones.add("a");
      ImmutableObject obj = new ImmutableObject("John", phones);
      // can I change it here ?
      System.out.println(obj.getPhoneNumbers());

      obj.getPhoneNumbers().clear();
//      String s = obj.getPhoneNumbers().get(0);

//      UnmodifiableList is a decorator
      // list = new UnmodifiableList(list)

      System.out.println(obj.getPhoneNumbers());
   }
}