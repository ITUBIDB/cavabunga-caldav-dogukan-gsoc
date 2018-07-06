package tr.edu.itu.cavabunga.cavabungacaldav.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tr.edu.itu.cavabunga.cavabungacaldav.service.MainCollectionService;

import javax.servlet.http.HttpServletRequest;

@RestController
public class CaldavController {
    private MainCollectionService mainCollectionService;

    @Autowired
    public CaldavController(MainCollectionService mainCollectionService){
        this.mainCollectionService = mainCollectionService;
    }

    @RequestMapping("/")
    public String getMainCollection(HttpServletRequest httpServletRequest, @RequestBody(required = false) String requestBody, @AuthenticationPrincipal UserDetails userDetails){
         return this.mainCollectionService.getCaldavResponse(httpServletRequest.getMethod(), httpServletRequest.getRequestURI(), requestBody, userDetails.getUsername());
    }

    @RequestMapping("/{user_name}")
    public String getUserCollection(){}

    @RequestMapping("/{user_name}/calendar")
    public String getUserCalendarCollection(){}

    @RequestMapping("/{user_name}/calendar/{ical_file}")
    public String getUserCalendarIcalCollection(){}

    @RequestMapping("/{user_name}/addresses/{ical_file}")
    public String getUserAddressIcalCollection(){}
}
