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
            /*add(CaldavCollection.USER_CALENDAR_ICAL_COLLECTION)*/;}}
        );
        collectionCollectionMap.put(CaldavCollection.USER_ADDRESS_COLLECTION,new ArrayList<CaldavCollection>(){{
            /*add(CaldavCollection.USER_ADDRESS_ICAL_COLLECTION)*/;}}
        );
        collectionCollectionMap.put(CaldavCollection.USER_CALENDAR_ICAL_COLLECTION, new ArrayList<CaldavCollection>());
        collectionCollectionMap.put(CaldavCollection.USER_ADDRESS_ICAL_COLLECTION, new ArrayList<CaldavCollection>());

        //collection's property configuration
        this.collectionPropertyMap = new HashMap<>();
        collectionPropertyMap.put(CaldavCollection.MAIN_COLLECTION,new HashMap<CaldavProperty, String>(){{
            put(CaldavProperty.RESOURCE_TYPE, "<collection/>");
            put(CaldavProperty.CURRENT_USER_PRINCIPAL, "<href>/" + ExpressionEnum.USERNAME.toString() + "/</href>");
            put(CaldavProperty.PRINCIPAL_URL, "<href>/" + ExpressionEnum.USERNAME.toString() + "/</href>");
            put(CaldavProperty.CALENDAR_HOME_SET, "<href>/" + ExpressionEnum.USERNAME.toString() + "/</href>");
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
        }});
        collectionPropertyMap.put(CaldavCollection.USER_CALENDAR_COLLECTION,new HashMap<CaldavProperty, String>(){{
            put(CaldavProperty.RESOURCE_TYPE, "<collection/><calendar/>");
            put(CaldavProperty.CALENDAR_USER_ADDRESS_SET, "<href>/" + ExpressionEnum.USERNAME.toString() + "/</href>" + "<href>mailto:" + ExpressionEnum.USER_MAIL.toString() + "</href>");
            put(CaldavProperty.DISPLAYNAME, ExpressionEnum.USERNAME.toString()+ " calendar");
            put(CaldavProperty.SUPPORTED_CALENDAR_COMPONENT_SET, "<comp name=\"VEVENT\"/><comp name=\"VTODO\"/><comp name=\"VJOURNAL\"/>");
            put(CaldavProperty.GET_CTAG, "-1");
            put(CaldavProperty.GET_ETAG, "-1");
            put(CaldavProperty.CALENDAR_DATA, "__CALENDAR_DATA__");
        }});
        collectionPropertyMap.put(CaldavCollection.USER_CALENDAR_ICAL_COLLECTION,new HashMap<CaldavProperty, String>(){{
            put(CaldavProperty.CALENDAR_DATA, "__CALENDAR_DATA__");
            put(CaldavProperty.GET_CTAG, "-1");
            put(CaldavProperty.GET_ETAG, "-1");
        }});
    }
}
