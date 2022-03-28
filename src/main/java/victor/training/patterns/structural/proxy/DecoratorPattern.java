package victor.training.patterns.structural.proxy;

public class DecoratorPattern {
   public static void main(String[] args) {
      Maths maths = new Maths();

//      ClientCode logic = new ClientCode(new MathsDecorator(maths));
      ClientCode logic = new ClientCode(maths);

      logic.method();
   }
}

// @Service
class ClientCode {
   private final IMaths maths;

   ClientCode(IMaths maths) {
      this.maths = maths;
   }

   public void method() {
      System.out.println("3 = " + maths.sum(1, 2));
      System.out.println("4 = " + maths.product(2, 2));
   }
}

// @Service
interface IMaths {
   int sum(int a, int b);

   int product(int a, int b);
}

class MathsDecorator implements IMaths {
   private final IMaths delegate;

   MathsDecorator(IMaths delegate) {
      this.delegate = delegate;
   }

   @Override
   public int sum(int a, int b) {
//      if (current.) throw
      System.out.println("Invisible extra stuff");
      return delegate.sum(a, b);
   }

   @Override
   public int product(int a, int b) {
      System.out.println("Invisible extra stuff");
      return delegate.product(a, b);
   }
}

//class already there that I want to change its methods' behavior
class Maths implements IMaths {
   public int sum(int a, int b) {
      return a + b;
   }

   public int product(int a, int b) {
      return a * b;
   }
}