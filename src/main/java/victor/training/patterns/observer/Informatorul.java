package victor.training.patterns.observer;

import java.util.ArrayList;
import java.util.List;

public class Informatorul {
  public static void main(String[] args) {
    Baba baba = new Baba();
    baba.adauga(new Aglae());
    baba.adauga(new CostelDeLa3());

    baba.aflaCeva("breaking news");
  }
}




class CostelDeLa3 implements Barfitor {
  @Override
  public void aflaBarfa(String barfa) {
    System.out.println("Costel: "+ barfa);
  }
}
class Aglae implements Barfitor {
  @Override
  public void aflaBarfa(String barfa) {
    System.out.println("Aglae: "+ barfa);
  }
}
// ----------------

class Baba {
  List<Barfitor> barfitorList = new ArrayList<>();

  public void adauga(Barfitor barfitor) {
    barfitorList.add(barfitor);
  }

  public void aflaCeva(String barfa) {
    barfitorList.forEach(barfitor -> barfitor.aflaBarfa(barfa));
  }
}
interface Barfitor {
  void aflaBarfa(String barfa);
}