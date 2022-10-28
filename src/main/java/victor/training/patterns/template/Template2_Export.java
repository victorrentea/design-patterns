package victor.training.patterns.template;

import lombok.RequiredArgsConstructor;
import org.apache.commons.io.function.IOConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import victor.training.patterns.facade.entity.Customer;
import victor.training.patterns.template.support.Order;
import victor.training.patterns.template.support.OrderRepo;
import victor.training.patterns.template.support.ProductRepo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.sql.ResultSet;
import java.sql.SQLException;
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

        //loan pattern:
        // some infrastructure code (Spring's JDBC template)
        // take a function from you that will work with a ResultSet that is managed from the infra
        // ie. JdbcTemplate will rs.close() and open the rs on the prepared statement.
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.queryForObject("SQL", (rowInTheResultSet, rowNum) -> {
            String name = rowInTheResultSet.getString(1);
            Customer customer = new Customer().setName(name);
            return customer;
        });
        //TODO
    }
}


// what matters /changes/ where most bugs will be
// =arch = the art of drawing lines =========================
// infra

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

            // LOAN: the function that I receive as an arg will loan from me a Writer that I created/closed/managed for it
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
