package victor.training.patterns.strategy;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import static java.lang.System.currentTimeMillis;

@Component
public class HttpFilter implements Filter {
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        System.out.println("Browser language: " + request.getHeader("Accept-Language"));
 // inainte
        long t0 = currentTimeMillis();
        chain.doFilter(req, res);
        long t1 = currentTimeMillis();
        System.out.printf(" A durat " + (t1-t0));
 // dupa

    }
}
