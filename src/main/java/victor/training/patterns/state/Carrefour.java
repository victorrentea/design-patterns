package victor.training.patterns.state;

import lombok.Data;

public class Carrefour {
   public static void main(String[] args) {
      ShoppingCart cart = new ShoppingCart();

      cart.insertCoin();
      System.out.println(cart);
      cart.insertCoin();
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

   public void insertCoin() {
      state = state.coin();
   }

   public void push() {
      state = state.push();
   }
}

class LockedState implements ShoppingCartState {
   @Override
   public ShoppingCartState coin() {
      System.out.println("Cart unlocked!");
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