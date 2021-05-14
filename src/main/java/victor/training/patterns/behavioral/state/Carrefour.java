package victor.training.patterns.behavioral.state;

import lombok.Data;

public class Carrefour {
   public static void main(String[] args) {
      ShoppingCart cart = new ShoppingCart();

      cart.coin();
      System.out.println(cart);
      cart.coin();
      System.out.println(cart);
      cart.push();
      System.out.println(cart);
      cart.push();
      System.out.println(cart);
   }
}

@Data
class ShoppingCart {
   private ShoppingCartState state = new LockedState();

   public void coin() {
      state = state.coin();
   }

   public void push() {
      state = state.push();
   }
}

class LockedState implements ShoppingCartState {
   @Override
   public ShoppingCartState coin() {
      System.out.println("AUditez ca s-a folosit un carucior");
      return new UnlockedState();
   }

   @Override
   public ShoppingCartState push() {
      return this;
   }
}

class UnlockedState implements ShoppingCartState {
   @Override
   public ShoppingCartState coin() {
      return this;
   }

   @Override
   public ShoppingCartState push() {
      System.out.println("TODO: da moneda inapoi");
      return new LockedState();
   }
}

interface ShoppingCartState {
   ShoppingCartState coin();

   ShoppingCartState push();
}