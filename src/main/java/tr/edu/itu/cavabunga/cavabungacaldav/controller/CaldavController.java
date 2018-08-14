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

    @RequestMapping(value = "/{user_name}/calendar/{ical_file}", produces = "text/calendar")
    @ResponseBody
    public String getUserCalendarIcalCollecion() {

            return  "BEGIN:VCALENDAR\n" +
                    "VERSION:2.0\n" +
                    "PRODID:-//www.marudot.com//iCal Event Maker\n" +
                    "X-WR-CALNAME:PgCon\n" +
                    "CALSCALE:GREGORIAN\n" +
                    "BEGIN:VTIMEZONE\n" +
                    "TZID:Europe/Moscow\n" +
                    "TZURL:http://tzurl.org/zoneinfo-outlook/Europe/Moscow\n" +
                    "X-LIC-LOCATION:Europe/Moscow\n" +
                    "BEGIN:STANDARD\n" +
                    "TZOFFSETFROM:+0300\n" +
                    "TZOFFSETTO:+0300\n" +
                    "TZNAME:MSK\n" +
                    "DTSTART:19700101T000000\n" +
                    "END:STANDARD\n" +
                    "END:VTIMEZONE\n" +
                    "BEGIN:VEVENT\n" +
                    "DTSTAMP:20180711T065748Z\n" +
                    "UID:20180711T065748Z-1478802882@marudot.com\n" +
                    "DTSTART;TZID=\"Europe/Moscow\":20180712T120000\n" +
                    "DTEND;TZID=\"Europe/Moscow\":20180712T120000\n" +
                    "SUMMARY:TEST2\n" +
                    "DESCRIPTION:TEST\n" +
                    "END:VEVENT\n" +
                    "END:VCALENDAR";
    }

}


