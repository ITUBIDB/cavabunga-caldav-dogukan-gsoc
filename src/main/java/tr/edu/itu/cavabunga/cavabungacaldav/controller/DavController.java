package tr.edu.itu.cavabunga.cavabungacaldav.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tr.edu.itu.cavabunga.cavabungacaldav.service.CaldavService;

import javax.servlet.http.HttpServletRequest;

@RestController
public class DavController {
    private CaldavService caldavService;

    @Autowired
    public DavController(CaldavService caldavService){
        this.caldavService = caldavService;
    }

    @RequestMapping("/")
    public String test(HttpServletRequest httpServletRequest){
        return "test " + httpServletRequest.getMethod();
    }
}
