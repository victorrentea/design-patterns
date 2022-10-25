package victor.training.patterns.strategy;

import javax.servlet.*;
import java.io.IOException;

public class AnHttpFilter implements Filter {
    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain) throws IOException, ServletException {

        // done you stuff
        try {


            chain.doFilter(request, response); // allow the call to propagate to the NEXT filter (whatever that is)
        } catch (IOException | ServletException e) {
            throw new RuntimeException(e);
        }
    }
}
