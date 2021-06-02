package victor.training.patterns.behavioral.observer.stock;

public class GenerateInvoiceCommand {
   private final long id;

   public GenerateInvoiceCommand(long id) {
      super();
      this.id = id;
   }

   public long getId() {
      return id;
   }
}

