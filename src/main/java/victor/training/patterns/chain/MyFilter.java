package victor.training.patterns.chain;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Slf4j
public class MyFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        String requestBody = IOUtils.toString(servletRequest.getReader());

        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
//        if (list.contains(httpRequest.getRequestURI()))
        // risk ramai cu lista-n-pom
        log.debug(requestBody);

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
