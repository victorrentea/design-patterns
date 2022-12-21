package victor.training.patterns.proxy.decorator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DecoratorPiBune {

  public static void main(String[] args) {
    List<Edge> edges = new ArrayList<>();
    edges.add(new Edge());
    edges.add(new Edge());
    edges.add(new Edge());
    List<Edge> listaNemodificabila = Collections.unmodifiableList(edges);

    new DecoratorPiBune().logicaHorror(listaNemodificabila);

    System.out.println("#sieu: " + edges);
  }

  public void logicaHorror(List<Edge> edges) {
   // la linia 870 :), ai un bug si ce te gandesti tu, sa 'hackuiesti' un pic lista
    edges.add(new Edge());

    // 100 lnii mai tarziu uiti de chestia asta.
  }
}

class Edge {

  @Override
  public String toString() {
    return "Edge";
  }
}