package victor.training.patterns.state;

import lombok.Data;

public class ShoppingCartPlay {
   public static void main(String[] args) {
      ShoppingCart cart = new ShoppingCart();

      cart.coin(50);
      System.out.println(cart);
      cart.coin(50);
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

   public void coin(int coin) {
      state = state.coin(coin);
   }

   public void push() {
      state = state.push();
   }
}

class LockedState implements ShoppingCartState {
   @Override
   public ShoppingCartState coin(int coin) {
      System.out.println("Side effect: Capture Coin " + coin); // or send a kafka message...
      return new UnlockedState();
   }

   @Override
   public ShoppingCartState push() {
      return this;
   }
}

class UnlockedState implements ShoppingCartState {
   @Override
   public ShoppingCartState coin(int coin) {
      // TODO or throw?
      return this;
   }

   @Override
   public ShoppingCartState push() {
      System.out.println("Side effect: return the coin");
      return new LockedState();
   }
}

interface ShoppingCartState {
   ShoppingCartState coin(int coin);

   ShoppingCartState push();

   //   default ShoppingCartState coin(int coin) {
   //      throw new IllegalStateException("Illegal in state " + this);
   //   }
   //   default ShoppingCartState push() {
   //      throw new IllegalStateException("Illegal in state " + this);
   //   }
}
