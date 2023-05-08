package victor.training.patterns.strategy;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class HttpFilter implements Filter {
    @Override
    public void doFilter(ServletRequest req,
                         ServletResponse res,
                         FilterChain chain
    ) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        System.out.println("Browser language: " + request.getHeader("Accept-Language"));
        // inainte
        chain.doFilter(req, res); // rulezi urm filtru, oricare ar fi el. nu depinzi de el.
        // dupa
    }
}
