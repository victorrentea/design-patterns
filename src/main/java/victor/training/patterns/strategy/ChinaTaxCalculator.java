package victor.training.patterns.strategy;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

@Component
public class ChinaTaxCalculator implements TaxCalculator {
    public double calculate(double tobaccoValue, double regularValue) {
        // multa logica grea
        return tobaccoValue + regularValue;
    }

    @Override
    public boolean applicableFor(String originCountry) {
        return "CN".equals(originCountry);
    }
}

@Component
class MyFilter implements Filter {
    @Override
    public void doFilter(
            ServletRequest servletRequest,
            ServletResponse servletResponse,
            FilterChain filterChain) throws IOException, ServletException {

        String s = IOUtils.toString(servletRequest.getReader());
//        log
        // treaba lui
        filterChain.doFilter(servletRequest, servletResponse);

    }
}