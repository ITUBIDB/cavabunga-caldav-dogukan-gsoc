package tr.edu.itu.cavabunga.cavabungacaldav.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tr.edu.itu.cavabunga.cavabungacaldav.caldav.enumerator.CaldavCollection;
import tr.edu.itu.cavabunga.cavabungacaldav.service.MainCollectionService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class CaldavController {
    private MainCollectionService mainCollectionService;

    @Autowired
    public CaldavController(MainCollectionService mainCollectionService){
        this.mainCollectionService = mainCollectionService;
    }

    @RequestMapping("/")
    public String getMainCollection(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @RequestBody(required = false) String requestBody, @AuthenticationPrincipal UserDetails userDetails){
        httpServletResponse.addHeader("DAV", "1, 2, 3, access-control, calendar-access, calendar-schedule");
        httpServletResponse.addHeader("DAV", "extended-mkcol, bind, addressbook, calendar-auto-schedule");
        httpServletResponse.addHeader("Allow", "OPTIONS, PROPFIND, REPORT, DELETE, LOCK, UNLOCK, MOVE, GET, PUT, HEAD, MKTICKET, DELTICKET, ACL");
        return this.mainCollectionService.getCaldavResponse(httpServletRequest, httpServletResponse, requestBody, userDetails, CaldavCollection.MAIN_COLLECTION);
    }


    @RequestMapping("/{user_name}")
    public String getUserCollection(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @RequestBody(required = false) String requestBody, @AuthenticationPrincipal UserDetails userDetails) {
        return this.mainCollectionService.getCaldavResponse(httpServletRequest, httpServletResponse, requestBody, userDetails, CaldavCollection.USER_COLLECTION);
    }

    @RequestMapping("/{user_name}/calendar")
    public String getUserCalendarCollection(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse, @RequestBody(required = false) String requestBody, @AuthenticationPrincipal UserDetails userDetails){
        return this.mainCollectionService.getCaldavResponse(httpServletRequest, httpServletResponse, requestBody, userDetails, CaldavCollection.USER_CALENDAR_COLLECTION);
    }

    @RequestMapping("/{user_name}/calendar/{ical_file}")
    public String getUserCalendarIcalCollection(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @RequestBody(required = false) String requestBody, @AuthenticationPrincipal UserDetails userDetails){
        return "CALSCALE:GREGORIAN\n" +
                "PRODID:-//Ximian//NONSGML Evolution Calendar//EN\n" +
                "VERSION:2.0\n" +
                "BEGIN:VTIMEZONE\n" +
                "TZID:/freeassociation.sourceforge.net/Europe/Istanbul\n" +
                "X-LIC-LOCATION:Europe/Istanbul\n" +
                "BEGIN:STANDARD\n" +
                "TZNAME:+03\n" +
                "DTSTART:19700907T000000\n" +
                "TZOFFSETFROM:+0300\n" +
                "TZOFFSETTO:+0300\n" +
                "END:STANDARD\n" +
                "END:VTIMEZONE\n" +
                "\n" +
                "BEGIN:VEVENT\n" +
                "UID:20170505T114658Z-1764-0-1-5@localhost.localdomain\n" +
                "DTSTAMP:20170505T114631Z\n" +
                "DTSTART;TZID=/freeassociation.sourceforge.net/Europe/Istanbul:\n" +
                " 20170509T090000\n" +
                "DTEND;TZID=/freeassociation.sourceforge.net/Europe/Istanbul:\n" +
                " 20170509T093000\n" +
                "SEQUENCE:2\n" +
                "SUMMARY:Test-1-2-3\n" +
                "LOCATION:bidb\n" +
                "TRANSP:OPAQUE\n" +
                "CLASS:PUBLIC\n" +
                "CREATED:20170505T114800Z\n" +
                "LAST-MODIFIED:20170505T114800Z\n" +
                "END:VEVENT\n" +
                "\n" +
                "END:VCALENDAR\n";
        //return this.mainCollectionService.getCaldavResponse(httpServletRequest, httpServletResponse, requestBody, userDetails, CaldavCollection.USER_CALENDAR_ICAL_COLLECTION);
    }

    @RequestMapping("/{user_name}/addresses/{ical_file}")
    public String getUserAddressIcalCollection(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @RequestBody(required = false) String requestBody, @AuthenticationPrincipal UserDetails userDetails){
        return this.mainCollectionService.getCaldavResponse(httpServletRequest, httpServletResponse, requestBody, userDetails, CaldavCollection.USER_ADDRESS_ICAL_COLLECTION);
    }
}
