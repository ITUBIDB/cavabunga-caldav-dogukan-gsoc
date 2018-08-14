package tr.edu.itu.cavabunga.cavabungacaldav.configuration.caldav;

import lombok.Data;
import org.springframework.stereotype.Component;
import tr.edu.itu.cavabunga.cavabungacaldav.caldav.enumerator.CaldavCollection;
import tr.edu.itu.cavabunga.cavabungacaldav.caldav.enumerator.CaldavProperty;
import tr.edu.itu.cavabunga.cavabungacaldav.caldav.enumerator.ExpressionEnum;

import java.util.*;


@Component
@Data
public class CaldavCollectionConfigurationImpl implements CaldavCollectionConfiguration {
    private Map<CaldavCollection, List<CaldavCollection>> collectionCollectionMap;
    private Map<CaldavCollection , Map<CaldavProperty, String>> collectionPropertyMap;

    public CaldavCollectionConfigurationImpl(){
        //collection's collection configuration
        this.collectionCollectionMap = new HashMap<>();
        collectionCollectionMap.put(CaldavCollection.MAIN_COLLECTION, new ArrayList<CaldavCollection>(){{
            add(CaldavCollection.USER_COLLECTION);}}
            );
        collectionCollectionMap.put(CaldavCollection.USER_COLLECTION,new ArrayList<CaldavCollection>(){{
            add(CaldavCollection.USER_CALENDAR_COLLECTION);
            add(CaldavCollection.USER_ADDRESS_COLLECTION);}}
        );
        collectionCollectionMap.put(CaldavCollection.USER_CALENDAR_COLLECTION, new ArrayList<CaldavCollection>(){{
            }}
        );
        collectionCollectionMap.put(CaldavCollection.USER_ADDRESS_COLLECTION,new ArrayList<CaldavCollection>(){{
            }}
        );
        collectionCollectionMap.put(CaldavCollection.USER_CALENDAR_ICAL_COLLECTION, new ArrayList<CaldavCollection>());
        collectionCollectionMap.put(CaldavCollection.USER_ADDRESS_ICAL_COLLECTION, new ArrayList<CaldavCollection>());

        //collection's property configuration
        this.collectionPropertyMap = new HashMap<>();
        collectionPropertyMap.put(CaldavCollection.MAIN_COLLECTION,new HashMap<CaldavProperty, String>(){{
            put(CaldavProperty.RESOURCE_TYPE, "<collection/>");
            put(CaldavProperty.CURRENT_USER_PRINCIPAL, "<href>/" + ExpressionEnum.USERNAME.toString() + "/</href>");
            put(CaldavProperty.PRINCIPAL_URL, "<href>/" + ExpressionEnum.USERNAME.toString() + "/</href>");
            put(CaldavProperty.CALENDAR_HOME_SET, "<href>/" + ExpressionEnum.USERNAME.toString() + "/calendar/</href>");
            put(CaldavProperty.CALENDAR_USER_ADDRESS_SET, "<href>/" + ExpressionEnum.USERNAME.toString() + "/</href>" + "<href>mailto:" + ExpressionEnum.USER_MAIL.toString() + "</href>");
            put(CaldavProperty.CALENDAR_HOME_SET, "<href>/" + ExpressionEnum.USERNAME.toString() + "/</href>");
            put(CaldavProperty.GET_CTAG, "-1");
            put(CaldavProperty.GET_ETAG, "-1");
        }});
        collectionPropertyMap.put(CaldavCollection.USER_COLLECTION,new HashMap<CaldavProperty, String>(){{
            put(CaldavProperty.RESOURCE_TYPE, "<collection/><principal/>");
            put(CaldavProperty.CALENDAR_USER_ADDRESS_SET, "<href>/" + ExpressionEnum.USERNAME.toString() + "/</href>" + "<href>mailto:" + ExpressionEnum.USER_MAIL.toString() + "</href>");
            put(CaldavProperty.DISPLAYNAME, ExpressionEnum.USERNAME.toString());
            put(CaldavProperty.GET_CTAG, "-1");
            put(CaldavProperty.GET_ETAG, "-1");
            put(CaldavProperty.CALENDAR_HOME_SET, "<href>/" + ExpressionEnum.USERNAME.toString() + "/calendar/</href>");
            put(CaldavProperty.SUPPORTED_CALENDAR_COMPONENT_SET, "<comp name=\"VEVENT\"/><comp name=\"VTODO\"/><comp name=\"VJOURNAL\"/>");
        }});
        collectionPropertyMap.put(CaldavCollection.USER_CALENDAR_COLLECTION,new HashMap<CaldavProperty, String>(){{
            put(CaldavProperty.RESOURCE_TYPE, "<collection/><CALDAV:calendar xmlns:CALDAV=\"urn:ietf:params:xml:ns:caldav\"/>");
            put(CaldavProperty.CALENDAR_USER_ADDRESS_SET, "<href>/" + ExpressionEnum.USERNAME.toString() + "/</href>" + "<href>mailto:" + ExpressionEnum.USER_MAIL.toString() + "</href>");
            put(CaldavProperty.DISPLAYNAME, ExpressionEnum.USERNAME.toString()+ " calendar");
            put(CaldavProperty.SUPPORTED_CALENDAR_COMPONENT_SET, "<CALDAV:comp name=\"VEVENT\" xmlns:CALDAV=\"urn:ietf:params:xml:ns:caldav\"/><CALDAV:comp name=\"VTODO\" xmlns:CALDAV=\"urn:ietf:params:xml:ns:caldav\"/><CALDAV:comp name=\"VJOURNAL\" xmlns:CALDAV=\"urn:ietf:params:xml:ns:caldav\"/>");
            put(CaldavProperty.GET_CTAG, "-1");
            put(CaldavProperty.GET_ETAG, "-1");
            put(CaldavProperty.CALENDAR_DATA, "__CALENDAR_DATA__");
        }});
        collectionPropertyMap.put(CaldavCollection.USER_CALENDAR_ICAL_COLLECTION,new HashMap<CaldavProperty, String>(){{
            put(CaldavProperty.CALENDAR_DATA,"BEGIN:VCALENDAR\n" +
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
                    "</C:calendar-data>\n");
            put(CaldavProperty.GETCONTENTTYPE, "text/calendar");
            put(CaldavProperty.GET_CTAG, "-1");
            put(CaldavProperty.GET_ETAG, "-1");
        }});
    }
}
