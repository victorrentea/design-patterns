package victor.training.patterns.template;

import lombok.RequiredArgsConstructor;
import victor.training.patterns.template.support.Order;
import victor.training.patterns.template.support.OrderRepo;

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


class FileExporter {
    private final OrderRepo orderRepo;
    private final File exportFolder;
    private final ContentWriter contentWriter;

    public FileExporter(OrderRepo orderRepo, File exportFolder, ContentWriter contentWriter) {
        this.orderRepo = orderRepo;
        this.exportFolder = exportFolder;
        this.contentWriter = contentWriter;
    }

    public File exportOrders() {
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
            System.out.println("Pretend: Metrics: Export finished in: " + (System.currentTimeMillis() - t0));
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
class Impl1 implements ContentWriter {
    // orders
    public void writeContent(Writer writer) throws IOException {
        writer.write("OrderID;Date\n");
        for (Order order : orderRepo.findByActiveTrue()) {
            String csv = order.getId() + ";" + order.getCustomerId() + ";" + order.getAmount() + "\n";
            writer.write(csv);
        }
    }

}class Impl2 implements ContentWriter {
    // products

    @Override
    public void writeContent(Writer writer) throws IOException {

    }
}
