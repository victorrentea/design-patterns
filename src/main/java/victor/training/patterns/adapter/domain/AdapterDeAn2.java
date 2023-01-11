package victor.training.patterns.adapter.domain;

public class AdapterDeAn2 {


  public static void main(String[] args) {

    printCuThread("Ceva4 ");
    printCuThread("Ceva1 ");
    printCuThread("Ceva5 ");
    printCuThread("Ceva7 ");
  }

  private static void printCuThread(String x) {
    System.out.println(Thread.currentThread().getName() + x);
  }
}
