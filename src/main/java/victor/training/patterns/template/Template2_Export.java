package victor.training.patterns.template;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;
import victor.training.patterns.template.support.Order;
import victor.training.patterns.template.support.OrderRepo;
import victor.training.patterns.template.support.Product;
import victor.training.patterns.template.support.ProductRepo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Objects;


@RequiredArgsConstructor
public class Template2_Export {
  private final FileExporter exporter;

  public void exportOrders() throws Exception {
//    exporter.exportOrders();
  }

  public void exportProducts() throws Exception {
    // TODO 'the same way you did the export of orders'
    // RUN UNIT TESTS!
  }
}


// imi ranesc clientii. le bag pe gat [Product]ContentWriter
@Service
@RequiredArgsConstructor
class FileExporter {
  private final File exportFolder;
  private final ContentWriters contentWriters;

  public File exportOrders() {
    return export(writer -> contentWriters.writeOrders(writer));
  }
  public File exportProducts() {
    return export(writer -> contentWriters.writeProducts(writer));
  }
  private File export(ContentWriter contentWriter) {
    // INFRA CODE
    File file = new File(exportFolder, "orders.csv");
    long t0 = System.currentTimeMillis();
    try (Writer writer = new FileWriter(file)) {
      System.out.println("Starting export to: " + file.getAbsolutePath());

      contentWriter.writeContent(writer);

      System.out.println("File export completed: " + file.getAbsolutePath());
      return file;
    } catch (Exception e) {
      System.out.println("Pretend: Send Error Notification Email"); // TODO CR: only for export orders, not for products
      throw new RuntimeException("Error exporting data", e);
    } finally {
      long t1 = System.currentTimeMillis();
      System.out.println("Pretend: Metrics: Export finished in: " + (t1 - t0));
    }
  }

  public String escapeCell(Object cellValue) {
    if (cellValue instanceof String s) {
      if (!s.contains("\n")) return s;
      return "\"" + s.replace("\"", "\"\"") + "\"";
    } else {
      return Objects.toString(cellValue);
    }
  }

}
interface ContentWriter {
    void writeContent(Writer writer) throws IOException;
}
@Service
@RequiredArgsConstructor
class ContentWriters {
  private final ProductRepo productRepo;
  private final OrderRepo orderRepo;

  // presupun ca cele 2 fct sunt mici.
  // daca cresc mari -> spart clasa (SRP)
  public void writeProducts(Writer writer) throws IOException {
    // FILE FORMAT CODE
    writer.write("ProductID;Name\n");
    for (Product product : productRepo.findAll()) {
      String csv = product.getId() + ";" + product.getName() + "\n";
      writer.write(csv);
    }
  }
  public void writeOrders(Writer writer) throws IOException {
    writer.write("OrderID;Date\n");
    for (Order order : orderRepo.findByActiveTrue()) {
      String csv = order.getId() + ";" + order.getCustomerId() + ";" + order.getAmount() + "\n";
      writer.write(csv);
    }
  }
}
