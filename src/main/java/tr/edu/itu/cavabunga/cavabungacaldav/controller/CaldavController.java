package tr.edu.itu.cavabunga.cavabungacaldav.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import tr.edu.itu.cavabunga.cavabungacaldav.caldav.enumerator.CaldavCollection;
import tr.edu.itu.cavabunga.cavabungacaldav.service.MainCollectionService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@RestController
public class CaldavController {
    //PROPFIND = REQUESTMETHOD.HEAD
    //REPORT = REQUESTMETHOD.TRACE

    private MainCollectionService mainCollectionService;
    @Autowired
    public CaldavController(MainCollectionService mainCollectionService){
        this.mainCollectionService = mainCollectionService;
    }

    @RequestMapping(value = "/", produces = "application/xml")
    @ResponseBody
    public ResponseEntity getMainCollection(HttpServletRequest request, @RequestBody String requestBody, Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        if(request.getMethod().equals("PROPFIND")){
            return new ResponseEntity<String>(mainCollectionService.getPropfindResponse(requestBody,userDetails,CaldavCollection.MAIN_COLLECTION, request.getHeader("Depth")), HttpStatus.MULTI_STATUS);
        }if(request.getMethod().equals("REPORT")){
           return new ResponseEntity<String>(mainCollectionService.getReportResponse(requestBody,userDetails,CaldavCollection.USER_CALENDAR_ICAL_COLLECTION, request.getHeader("Depth")), HttpStatus.MULTI_STATUS);
        }if(request.getMethod().equals("OPTIONS")){
            return new ResponseEntity<String>(mainCollectionService.getOptionsResponse(requestBody,CaldavCollection.MAIN_COLLECTION), HttpStatus.OK);
        }else{
            return new ResponseEntity(HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/{user_name}", produces = "application/xml")
    @ResponseBody
    public ResponseEntity getUserCollection(HttpServletRequest request, @RequestBody String requestBody, Authentication authentication) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            if(request.getMethod().equals("PROPFIND")){
                return new ResponseEntity<String>(mainCollectionService.getPropfindResponse(requestBody,userDetails,CaldavCollection.USER_COLLECTION, request.getHeader("Depth")), HttpStatus.MULTI_STATUS);
            }if(request.getMethod().equals("REPORT")){
            return new ResponseEntity<String>(mainCollectionService.getReportResponse(requestBody,userDetails,CaldavCollection.USER_CALENDAR_ICAL_COLLECTION, request.getHeader("Depth")), HttpStatus.MULTI_STATUS);
            }if(request.getMethod().equals("OPTIONS")){
                return new ResponseEntity<String>(mainCollectionService.getOptionsResponse(requestBody,CaldavCollection.USER_COLLECTION), HttpStatus.OK);
            }else{
                return new ResponseEntity(HttpStatus.OK);
            }
    }

    @RequestMapping(value = "/{user_name}/calendar", produces = "application/xml")
    @ResponseBody
    public ResponseEntity getUserCalendarCollection(HttpServletRequest request, @RequestBody String requestBody, Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        if(request.getMethod().equals("PROPFIND")){
            return new ResponseEntity<String>(mainCollectionService.getPropfindResponse(requestBody,userDetails,CaldavCollection.USER_CALENDAR_COLLECTION, request.getHeader("Depth")), HttpStatus.MULTI_STATUS);
        }if(request.getMethod().equals("REPORT")){
            return new ResponseEntity<String>(mainCollectionService.getReportResponse(requestBody,userDetails,CaldavCollection.USER_CALENDAR_ICAL_COLLECTION, request.getHeader("Depth")), HttpStatus.MULTI_STATUS);
        }if(request.getMethod().equals("OPTIONS")){
            return new ResponseEntity<String>(mainCollectionService.getOptionsResponse(requestBody,CaldavCollection.USER_CALENDAR_COLLECTION), HttpStatus.OK);
        }else{
            return new ResponseEntity(HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/{user_name}/calendar/{ical_file}", produces = "application/xml")
    @ResponseBody
    public ResponseEntity getUserCalendarIcalCollecion(HttpServletRequest request, @RequestBody String requestBody, Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        if(request.getMethod().equals("PROPFIND")){
            return new ResponseEntity<String>(mainCollectionService.getPropfindResponse(requestBody,userDetails,CaldavCollection.USER_CALENDAR_COLLECTION, request.getHeader("Depth")), HttpStatus.MULTI_STATUS);
        }if(request.getMethod().equals("REPORT")){
            return new ResponseEntity<String>(mainCollectionService.getReportResponse(requestBody,userDetails,CaldavCollection.USER_CALENDAR_ICAL_COLLECTION, request.getHeader("Depth")), HttpStatus.MULTI_STATUS);
        }if(request.getMethod().equals("OPTIONS")){
            return new ResponseEntity<String>(mainCollectionService.getOptionsResponse(requestBody,CaldavCollection.USER_CALENDAR_COLLECTION), HttpStatus.OK);
        }else{
            return new ResponseEntity(HttpStatus.OK);
        }
    }

}
