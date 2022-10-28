//package victor.training.patterns.template;
//
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import victor.training.patterns.template.support.Order;
//import victor.training.patterns.template.support.OrderRepo;
//
//import java.io.IOException;
//import java.io.StringWriter;
//import java.io.Writer;
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//class OrderContentWriterTest {
//
//    @Mock
//    OrderRepo orderRepo;
//    @InjectMocks
//    OrderContentWriter target;
//    @Test
//    void writeExport() throws IOException {
//        Order order = new Order().setId(1L).setCustomerId(2L).setAmount(1.0);
//        when(orderRepo.findByActiveTrue()).thenReturn(List.of(order));
//        StringWriter sw = new StringWriter();
//
//        target.writeExport(sw);
//
//        String content = sw.toString();
//        assertThat(content).isEqualTo("""
//                OrderID;Date
//                1;2;1.0
//                """);
//
//        // DO THIS please!
//    }
//}