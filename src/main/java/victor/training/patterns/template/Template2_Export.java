package victor.training.patterns.template;

import lombok.RequiredArgsConstructor;
import org.apache.commons.io.function.IOConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import victor.training.patterns.template.support.Order;
import victor.training.patterns.template.support.OrderRepo;
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
        exporter.exportOrders(w -> orderContentWriter.writeExport(w));
    }

    @Autowired
    private OrderContentWriter orderContentWriter;
    @Autowired
    private ProductContentWriter productContentWriter;

    public void exportProducts() throws Exception {
        exporter.exportOrders(w -> productContentWriter.writeExport(w));

        // TODO 'the same way you did the export of orders'
        // RUN UNIT TESTS!
    }
}
@RequiredArgsConstructor
@Service
class OrderContentWriter {
    private final OrderRepo orderRepo;

    public void writeExport(Writer writer) throws IOException {
        writer.write("OrderID;Date\n");

        for (Order order : orderRepo.findByActiveTrue()) {
            String csv = order.getId() + ";" + order.getCustomerId() + ";" + order.getAmount() + "\n";
            writer.write(csv);
        }
    }
}
@RequiredArgsConstructor
@Service
class ProductContentWriter {
    private final ProductRepo productRepo;

    public void writeExport(Writer writer) throws IOException {
        writer.write("Prodycts....\n");
        //TODO
    }
}


class FileExporter {
    private final OrderRepo orderRepo;
    private final File exportFolder;

    public FileExporter(OrderRepo orderRepo, File exportFolder) {
        this.orderRepo = orderRepo;
        this.exportFolder = exportFolder;
    }

    public File exportOrders(IOConsumer<Writer> ioConsumer) {
        File file = new File(exportFolder, "orders.csv");
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
