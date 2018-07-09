package tr.edu.itu.cavabunga.cavabungacaldav.service;

import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import tr.edu.itu.cavabunga.cavabungacaldav.caldav.AbstractCaldavCollection;
import tr.edu.itu.cavabunga.cavabungacaldav.caldav.build.collection.CaldavCollectionBuilderImpl;
import tr.edu.itu.cavabunga.cavabungacaldav.caldav.build.response.CaldavResponseBuilderImpl;
import tr.edu.itu.cavabunga.cavabungacaldav.caldav.enumerator.CaldavCollection;
import tr.edu.itu.cavabunga.cavabungacaldav.caldav.enumerator.CaldavRequestMethod;
import tr.edu.itu.cavabunga.cavabungacaldav.configuration.caldav.CaldavCollectionConfiguration;
import tr.edu.itu.cavabunga.cavabungacaldav.exception.CaldavException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.StringReader;

@Service
public class MainCollectionService {
    private CaldavCollectionConfiguration caldavCollectionConfigurationImpl;
    private CaldavCollectionBuilderImpl caldavCollectionBuilderImpl;
    private CaldavResponseBuilderImpl caldavResponseBuilderImpl;


    @Autowired
    public MainCollectionService(CaldavCollectionConfiguration caldavCollectionConfigurationImpl,
                                 CaldavCollectionBuilderImpl caldavCollectionBuilderImpl,
                                 CaldavResponseBuilderImpl caldavResponseBuilderImpl){
        this.caldavCollectionConfigurationImpl = caldavCollectionConfigurationImpl;
        this.caldavCollectionBuilderImpl = caldavCollectionBuilderImpl;
        this.caldavResponseBuilderImpl = caldavResponseBuilderImpl;
    }

    public String getCaldavResponse(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    String requestBody,
                                    UserDetails userDetails,
                                    CaldavCollection caldavCollection){
        CaldavRequestMethod method = CaldavRequestMethod.convertToEnum(httpServletRequest.getMethod());
        switch (method){
            case GET:
                return getGetResponse(httpServletRequest, requestBody, userDetails, caldavCollection);
            case POST:
                return getPostResponse(httpServletRequest, requestBody, userDetails, caldavCollection);
            case PUT:
                return getPutResponse(httpServletRequest, requestBody, userDetails, caldavCollection);
            case DELETE:
                return getDeleteResponse(httpServletRequest, requestBody, userDetails, caldavCollection);
            case PATCH:
                return getPatchResponse(httpServletRequest, requestBody, userDetails, caldavCollection);
            case OPTIONS:
                return getOptionsResponse(httpServletResponse,httpServletRequest, requestBody, userDetails, caldavCollection);
            case HEAD:
                return getHeadResponse(httpServletRequest, requestBody, userDetails, caldavCollection);
            case PROPFIND:
                return getPorpfindResponse(httpServletRequest, requestBody, userDetails, caldavCollection);
            case PROPPATCH:
                return getProppatchResponse(httpServletRequest, requestBody, userDetails, caldavCollection);
            case REPORT:
                return getReportResponse(httpServletRequest, requestBody, userDetails, caldavCollection);
            default:
                throw new CaldavException("Method enum didn't match any");
        }
    }


    public String getGetResponse(HttpServletRequest httpServletRequest, String requestBody, UserDetails userDetails, CaldavCollection caldavCollection){
        return "";
    }

    public String getPostResponse(HttpServletRequest httpServletRequest, String requestBody, UserDetails userDetails, CaldavCollection caldavCollection){
        return "";
    }

    public String getPutResponse(HttpServletRequest httpServletRequest, String requestBody, UserDetails userDetails, CaldavCollection caldavCollection){
        return "";
    }

    public String getDeleteResponse(HttpServletRequest httpServletRequest, String requestBody, UserDetails userDetails, CaldavCollection caldavCollection){
        return "";
    }

    public String getPatchResponse(HttpServletRequest httpServletRequest, String requestBody, UserDetails userDetails, CaldavCollection caldavCollection){
        return "";
    }

    public String getOptionsResponse(HttpServletResponse httpServletResponse, HttpServletRequest httpServletRequest, String requestBody, UserDetails userDetails, CaldavCollection caldavCollection){
        return "";
    }

    public String getHeadResponse(HttpServletRequest httpServletRequest, String requestBody, UserDetails userDetails, CaldavCollection caldavCollection){
        return   "";
    }

    public String getPorpfindResponse(HttpServletRequest httpServletRequest, String requestBody, UserDetails userDetails, CaldavCollection caldavCollection){

        AbstractCaldavCollection collection = this.caldavCollectionBuilderImpl.build(httpServletRequest, caldavCollection, userDetails);
        SAXBuilder xmlBuilder = new SAXBuilder();
        org.jdom2.Document document;

        try{
            document = xmlBuilder.build(new StringReader(requestBody));
        }catch (Exception e){
            throw new CaldavException("XML Parsing error, " + e.getMessage());
        }

        if(!document.getRootElement().getName().equals("propfind")){
            throw new CaldavException("propfind root element couldn found");
        }

        Element propfind = document.getRootElement();

        if(!propfind.getChildren().get(0).getName().equals("prop")){
            throw new CaldavException("prop element cound not found");
        }
        Element prop = propfind.getChildren().get(0);

        return this.caldavResponseBuilderImpl.getPropfindResponse(collection, prop);
    }

    public String getProppatchResponse(HttpServletRequest httpServletRequest, String requestBody, UserDetails userDetails, CaldavCollection caldavCollection){
        return "";
    }

    public String getReportResponse(HttpServletRequest httpServletRequest, String requestBody, UserDetails userDetails, CaldavCollection caldavCollection){
        return "<?xml version=\"1.0\" encoding=\"utf-8\" ?>\n" +
                "<multistatus xmlns=\"DAV:\" xmlns:C=\"urn:ietf:params:xml:ns:caldav\">\n" +
                " <response>\n" +
                "  <href>/testuser/calendar/calendar.ics</href>\n" +
                "  <propstat>\n" +
                "   <prop>\n" +
                "    <getetag>-1</getetag>\n" +
                "    <getetag>-1</getetag>\n" +
                "    <C:calendar-data>BEGIN:VCALENDAR\n" +
                "CALSCALE:GREGORIAN\n" +
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
                "END:VCALENDAR\n" +
                "</C:calendar-data>\n" +
                "   </prop>\n" +
                "   <status>HTTP/1.1 200 OK</status>\n" +
                "  </propstat>\n" +
                " </response>\n" +
                "</multistatus>";
    }
}
