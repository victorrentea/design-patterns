package victor.training.patterns.solid;

import com.google.common.collect.ImmutableList;
import org.jooq.lambda.Seq;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class JDKBreaksLiskov {
  public static void main(String[] args) {
    List<String> listaMutabila95 = new ArrayList<>();

    listaMutabila95.add("a");
//    Reader reader2 = new FileReader();
//    Reader reader = new BufferedReader(new FileReader());
    evil(Collections.unmodifiableList(listaMutabila95));

    System.out.println(listaMutabila95);
  }

  private static void evil(List<String> list) {
    list.add("rele"); // PANICA LA DEVELOPER!! eu nu stiam ca add arunca!
    // incalca liskov
//    Seq // jool
  }

  private static void renuntaLaJavaCollectionFramework_chooseGoogle(
      ImmutableList<String> list) {
    list.add("rele"); // PANICA LA DEVELOPER!! eu nu stiam ca add arunca!
    // incalca liskov
  }

}
