package victor.training.oo.creational.constructors;

import java.util.HashMap;
import java.util.Map;

public class ImmutableClass {
   private final int id;
   private final String name;

   private final Map<String, String> properties;
   private final String company;

   public ImmutableClass(int id, String name, Map<String, String> properties, String company) {
      this.id = id;
      this.name = name;
      this.properties = properties;
      this.company = company;
   }

   public ImmutableClass(int id, String name) {
      this(id, name, new HashMap<>(), null);
   }

   public int getId() {
      return id;
   }

   public String getName() {
      return name;
   }

   public Map<? extends String, String> getProperties() {
      return properties;
   }

   static {
      ImmutableClass i = new ImmutableClass(1, "");
//      i.getProperties().put("a", "xs");
   }

   public String getCompany() {
      return company;
   }


   public ImmutableClass setProperties(Map<String, String> newProperties) {
      return new ImmutableClass(id, name, newProperties, company);
   }

   public ImmutableClass setProperties(String newCompany) {
      return new ImmutableClass(id, name, properties, newCompany);
   }
}
