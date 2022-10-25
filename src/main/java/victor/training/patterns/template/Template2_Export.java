package victor.training.patterns.template;

import lombok.RequiredArgsConstructor;
import victor.training.patterns.template.support.Order;
import victor.training.patterns.template.support.OrderRepo;
import victor.training.patterns.template.support.ProductRepo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

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


abstract class FileExporter {
    private final File exportFolder;

    public FileExporter(File exportFolder) {
        this.exportFolder = exportFolder;
    }

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
            System.out.println("Pretend: Metrics: Export finished in: " + (System.currentTimeMillis() - t0));
        }
    }

    protected abstract void writeContent(Writer writer) throws IOException;

    public String escapeCell(String cellValue) {
        if (!cellValue.contains("\n")) return cellValue;
        return "\"" + cellValue.replace("\"", "\"\"") +  "\"";
    }
}

class OrderFileExporter extends FileExporter{
    private final OrderRepo orderRepo;

    OrderFileExporter(OrderRepo orderRepo, File exportFolder) {
        super(exportFolder);
        this.orderRepo = orderRepo;
    }

    @Override
    public void writeContent(Writer writer) throws IOException {
        writer.write("OrderID;Date\n");

        for (Order order : orderRepo.findByActiveTrue()) {
            String csv = order.getId() + ";" + order.getCustomerId() + ";" + order.getAmount() + "\n";
            writer.write(csv);
        }
    }
}

class ProductFileExporter extends FileExporter{
    private final ProductRepo productRepo;

    ProductFileExporter(ProductRepo productRepo, File exportFolder) {
        super(exportFolder);
        this.productRepo = productRepo;
    }

    @Override
    protected void writeContent(Writer writer) throws IOException {
        writer.write("Product .... ;Date\n");

        // TODO
//        for (Order order : productRepo.findByActiveTrue()) {
//            String csv = order.getId() + ";" + order.getCustomerId() + ";" + order.getAmount() + "\n";
//            writer.write(csv);
//        }
    }
}