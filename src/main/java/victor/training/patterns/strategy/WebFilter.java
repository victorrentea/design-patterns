package victor.training.patterns.strategy;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.lang.System.currentTimeMillis;

public class WebFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {

        long t0 = currentTimeMillis();

        filterChain.doFilter(servletRequest,servletResponse);

        long t1 = currentTimeMillis();
        System.out.println("cat a durat" + (t1-t0));
    }
}
