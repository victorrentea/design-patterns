package victor.training.patterns.template;

import lombok.RequiredArgsConstructor;
import org.apache.commons.io.function.IOConsumer;
import org.springframework.stereotype.Service;
import victor.training.patterns.template.support.Order;
import victor.training.patterns.template.support.OrderRepo;
import victor.training.patterns.template.support.Product;
import victor.training.patterns.template.support.ProductRepo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

@RequiredArgsConstructor
public class Template2_Export {
    private final FileExporter exporter;
    private final OrderContentWriter orderContentWriter;
    private final ProductsContentWriter productsContentWriter;
    public void exportOrders() throws Exception {
        exporter.exportOrders("products.csv", orderContentWriter::writeOrdersCSV);
    }

    public void exportProducts() throws Exception {
        // TODO 'the same way you did the export of orders'
        // RUN UNIT TESTS!
        exporter.exportOrders("products.csv",
                writer -> productsContentWriter.writeOrdersCSV(writer));
    }
}

@RequiredArgsConstructor
@Service
class OrderContentWriter {
    private final OrderRepo orderRepo;

    public void writeOrdersCSV(Writer writer) throws IOException {
        writer.write("OrderID;CustomerId;Date\n");

        for (Order order : orderRepo.findByActiveTrue()) {
            String csv = order.getId() + ";" + order.getCustomerId() + ";" + order.getAmount() + "\n";
            writer.write(csv);
        }
    }
}
@RequiredArgsConstructor
@Service
class ProductsContentWriter {
    private final ProductRepo productRepo;

    public void writeOrdersCSV(Writer writer) throws IOException {
        writer.write("ID;ProductName;Price\n");

        for (Product product : productRepo.findAll()) {
            writer.write(product.getId() + ";" + product.getName() + ";" + product.getPrice()+"\n");
        }
    }
}


class FileExporter {
    private final OrderRepo orderRepo;
    private final File exportFolder;

    public FileExporter(OrderRepo orderRepo, File exportFolder) {
        this.orderRepo = orderRepo;
        this.exportFolder = exportFolder;
    }

    public File exportOrders(String fileName, IOConsumer<Writer> ioConsumer) {
        File file = new File(exportFolder, fileName);
        long t0 = System.currentTimeMillis();
        try (Writer writer = new FileWriter(file)) {
            System.out.println("Starting export to: " + file.getAbsolutePath());

            ioConsumer.accept(writer);

            System.out.println("File export completed: " + file.getAbsolutePath());
            return file;
        } catch (Exception e) {
            System.out.println("Pretend: Send Error Notification Email"); // TODO CR: only for export orders, not for products
            throw new RuntimeException("Error exporting data", e);
        } finally {
            System.out.println("Pretend: Metrics: Export finished in: " +
                               (System.currentTimeMillis() - t0));
        }
    }

    public String escapeCell(String cellValue) {
        if (!cellValue.contains("\n")) return cellValue;
        return "\"" + cellValue.replace("\"", "\"\"") +  "\"";
    }
}
