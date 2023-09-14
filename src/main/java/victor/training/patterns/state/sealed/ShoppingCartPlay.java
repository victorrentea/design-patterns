//package victor.training.patterns.state.sealed;
//
//import lombok.Data;
//
//public class ShoppingCartPlay {
//   public static void main(String[] args) {
//      ShoppingCart cart = new ShoppingCart();
//
//      cart.coin(50);
//      System.out.println(cart);
//      cart.coin(50);
//      System.out.println(cart);
//      cart.push();
//      System.out.println(cart);
//      cart.push();
//      System.out.println(cart);
//   }
//}
//
//@Data
//class ShoppingCart {
//   private ShoppingCartState state = new LockedState();
//
//   public void coin(int coin) {
//      state = state.handle(new AddCoin(coin));
//   }
//
//   public void push() {
//      state = state.handle(new PushCart());
//   }
//}
//
//
//class LockedState implements ShoppingCartState {
//
//   @Override
//   public ShoppingCartState handle(Signal signal) {
//      return switch (signal) {
//         case AddCoin c -> {
//            System.out.println("Side effect: Capture Coin " + c.coin()); // or send a kafka message...
//            yield new LockedState();
//         }
//         default -> {throw new IllegalArgumentException();}
//         //         case AddCoin addCoin -> {throw new IllegalArgumentException();}
//      };
//   }
//}
//
//class UnlockedState implements ShoppingCartState {
//   @Override
//   public ShoppingCartState handle(Signal signal) {
//      return switch (signal) {
//         case PushCart c -> {
//            System.out.println("Side effect: return the coin");
//            yield new LockedState();
//         }
//         default -> {throw new IllegalArgumentException();}
////         case AddCoin addCoin -> {throw new IllegalArgumentException();}
//      };
//   }
//}
//
//sealed interface Signal permits AddCoin, PushCart{}
//record AddCoin(int coin) implements Signal{}
//record PushCart() implements Signal{}
//
//interface ShoppingCartState {
//   ShoppingCartState handle(Signal signal);
//}
