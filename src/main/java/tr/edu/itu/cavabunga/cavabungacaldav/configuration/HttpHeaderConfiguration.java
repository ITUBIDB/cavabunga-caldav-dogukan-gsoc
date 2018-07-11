package tr.edu.itu.cavabunga.cavabungacaldav.configuration;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ControllerAdvice
public class HttpHeaderConfiguration extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response, FilterChain filterChain)  throws ServletException, IOException {
        response.addHeader("DAV", "1, 2, 3, access-control, calendar-access, calendar-schedule");
        response.addHeader("DAV", "extended-mkcol, bind, addressbook, calendar-auto-schedule");
        response.setHeader("Allow", "OPTIONS, PROPFIND, REPORT, DELETE, LOCK, UNLOCK, MOVE, GET, PUT, HEAD, MKTICKET, DELTICKET, ACL");
        response.addHeader("ETag", RandomStringUtils.randomAlphanumeric(12));
        filterChain.doFilter(request, response);
    }
}