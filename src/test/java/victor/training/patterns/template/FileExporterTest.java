//package victor.training.patterns.template;
//
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import victor.training.patterns.template.support.Order;
//import victor.training.patterns.template.support.OrderRepo;
//
//import java.io.File;
//import java.io.IOException;
//import java.nio.file.Files;
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.mockito.Mockito.when;
//
//class FileExporterTest {
//    OrderRepo orderRepo = Mockito.mock(OrderRepo.class);
//    FileExporter exporter;
//
//    File exportFolder = Files.createTempDirectory("test").toFile();
//
//    FileExporterTest() throws IOException {
//    }
//
//    @BeforeEach
//    final void before() {
//        exporter = new FileExporter(orderRepo, exportFolder);
//    }
//    @AfterEach
//    void after() {
//        // TODO cleanup delete temp folder.
//    }
//
//    @Test
//    void fragileTest() throws IOException {
//        Order order = new Order().setId(1L).setCustomerId(13L).setAmount(10D);
//        when(orderRepo.findByActiveTrue()).thenReturn(List.of(order));
//
//        File exportedFile = exporter.exportOrders(writer1 -> {
//            writer1.write("OrderID;Date\n");
//
//            for (Order order : orderRepo.findByActiveTrue()) {
//                String csv = order.getId() + ";" + order.getCustomerId() + ";" + order.getAmount() + "\n";
//                writer1.write(csv);
//            }
//        });
//
//        String contents = Files.readString(exportedFile.toPath());
//        assertThat(contents).isEqualTo("""
//                OrderID;Date
//                1;13;10.0
//                """);
//    }
//}