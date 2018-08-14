package tr.edu.itu.cavabunga.cavabungacaldav.configuration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@EnableAutoConfiguration
@Configuration
public class HttpMethods extends WebMvcConfigurationSupport {

    public static class CopyMethodDispatcher extends DispatcherServlet {
        private static final long serialVersionUID = 1L;

        @Override
        protected void service(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
            if ("REPORT".equals(request.getMethod())) {
                super.doGet(request, response);
            } else if("PROPFIND".equals(request.getMethod())){
                super.doPost(request, response);
            }else {
                super.service(request, response);
            }
        }
    }

    public static void main(final String[] args) throws Exception {
        SpringApplication.run(HttpMethods.class, args);
    }



    @Override
    @Bean
    public RequestMappingHandlerAdapter requestMappingHandlerAdapter() {
        final RequestMappingHandlerAdapter requestMappingHandlerAdapter = super.requestMappingHandlerAdapter();
        requestMappingHandlerAdapter.setSupportedMethods("GET", "POST", "PUT", "DELETE", "PROPFIND", "REPORT", "OPTIONS"); // add all methods your controllers need to support

        return requestMappingHandlerAdapter;
    }

    @Bean
    DispatcherServlet dispatcherServlet() {
        return new CopyMethodDispatcher();
    }
}