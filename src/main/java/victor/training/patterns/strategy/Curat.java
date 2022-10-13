package victor.training.patterns.strategy;

import lombok.Data;
import lombok.Value;
import org.aspectj.weaver.ast.Or;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class Curat {
    public static void main(String[] args) {

    }
}

interface IOrderContext{
    String getCountryCode();
    LocalDate getEntryDate();
}

@Value
class Order implements IOrderContext {
    String countryCode;
    LocalDate entryDate;
   OrderPlugin head;

    public Double computeTaxes() {
//        OrderPlugin head = moduriPosibileDeCalcul.get(moduriPosibileDeCalcul.size() - 1);
//        for (int i = moduriPosibileDeCalcul.size()-1; i>=0; i--) {
//            OrderPlugin f = moduriPosibileDeCalcul.get(i);
//            f.setNext(head);
//            head = f;
//        }
        return head.calculate(this);
    }
}

interface  OrderPlugin {
    OrderPlugin getNext();
    void setNext(OrderPlugin next);
    double calculate(IOrderContext context) ;
}


class UKTax implements OrderPlugin {
    private OrderPlugin next;

    @Override
    public OrderPlugin getNext() {
        return next;
    }

    @Override
    public void setNext(OrderPlugin next) {
        this.next = next;
    }

    @Override
    public double calculate(IOrderContext context/*, OrderPlugin next*/) {
        if (context.getCountryCode().equals("UK"))
            return 1;
        else return next.calculate(context);

    }
}
class China2Tax implements OrderPlugin {
    private OrderPlugin next;

    @Override
    public OrderPlugin getNext() {
        return next;
    }

    @Override
    public void setNext(OrderPlugin next) {
        this.next = next;
    }

    @Override
    public double calculate(IOrderContext context) {
        if (context.getCountryCode().equals("CN"))
        return 1;
        else return next.calculate(context);
    }
}