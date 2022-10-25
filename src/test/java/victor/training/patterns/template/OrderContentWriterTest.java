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
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//class OrderContentWriterTest {
//    @Mock
//    OrderRepo orderRepo;
//    @InjectMocks
//    OrderContentWriter target;
//
//    @Test
//    void writeOrdersCSV() throws IOException {
//        Order order = new Order().setId(1L).setCustomerId(13L).setAmount(10D);
//        when(orderRepo.findByActiveTrue()).thenReturn(List.of(order));
//        StringWriter writer = new StringWriter();
//
//        target.writeOrdersCSV(writer);
//
//        String contents = writer.toString();
//        assertThat(contents).isEqualTo("""
//                OrderID;CustomerId;Date
//                1;13;10.0
//                """);
//    }
//}