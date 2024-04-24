package victor.training.patterns.template;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
        exporter.exportOrders();
    }

    public void exportProducts() throws Exception {
        // TODO 'the same way you did the export of orders'
        // RUN UNIT TESTS!
    }
}


@RequiredArgsConstructor
abstract class FileExporter {
    private final File exportFolder;
    public File exportOrders() {
        File file = new File(exportFolder, "orders.csv");
        long t0 = System.currentTimeMillis();
        try (Writer writer = new FileWriter(file)) {
            System.out.println("Starting export to: " + file.getAbsolutePath());

            writeContent(writer);

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
    abstract protected void writeContent(Writer writer) throws IOException;

    // tools offered to subtypes
    public String escapeCell(Object cellValue) {
        if (cellValue instanceof String s) {
            if (!s.contains("\n")) return s;
            return "\"" + s.replace("\"", "\"\"") + "\"";
        } else {
            return Objects.toString(cellValue);
        }
    }

}
// folosim template method cand clasa de baza (cu metoda abstracta template)
// ofera subclaselor metode helper
// uses tools from superclass
class ProductFileExporter extends FileExporter {
    private final ProductRepo productRepo;
    public ProductFileExporter(File exportFolder, ProductRepo productRepo) {
        super(exportFolder);
        this.productRepo = productRepo;
    }
    @Override
    protected void writeContent(Writer writer) throws IOException {
        writer.write("ProductID;Name\n");
        for (Product product : productRepo.findAll()) {
            String csv = product.getId() + ";" + escapeCell(product.getName()) + "\n";
            writer.write(csv);
        }
    }
}

class OrderFileExporter extends FileExporter {
     private final OrderRepo orderRepo;
    public OrderFileExporter(File exportFolder, OrderRepo orderRepo) {
        super(exportFolder);
        this.orderRepo = orderRepo;
    }
    @Override
    protected void writeContent(Writer writer) throws IOException {
        writer.write("OrderID;Date\n");
        for (Order order : orderRepo.findByActiveTrue()) {
            String csv = order.getId() + ";" + order.getCustomerId() + ";" + escapeCell(order.getAmount()) + "\n";
            writer.write(csv);
        }
    }
}

