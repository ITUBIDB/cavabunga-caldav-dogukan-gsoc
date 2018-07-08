package tr.edu.itu.cavabunga.cavabungacaldav.configuration.caldav;

import lombok.Data;
import org.springframework.stereotype.Component;
import tr.edu.itu.cavabunga.cavabungacaldav.caldav.enumerator.CaldavCollection;
import tr.edu.itu.cavabunga.cavabungacaldav.caldav.enumerator.CaldavProperty;
import tr.edu.itu.cavabunga.cavabungacaldav.caldav.enumerator.ExpressionEnum;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Data
public class CaldavCollectionConfigurationImpl implements CaldavCollectionConfiguration {
    private Map<CaldavCollection , Map<CaldavProperty, String>> collectionPropertyMap;
    private Map<CaldavCollection, List<CaldavCollection>> collectionCollectionMap;

    public CaldavCollectionConfigurationImpl(){
        this.collectionCollectionMap = new ConcurrentHashMap<>();
        //collection conf
        collectionCollectionMap.put(CaldavCollection.MAIN_COLLECTTION, new ArrayList<CaldavCollection>(){{
            add(CaldavCollection.USER_COLLECTION);}}
            );
        collectionCollectionMap.put(CaldavCollection.USER_ADDRESS_COLLECTION,new ArrayList<CaldavCollection>(){{
            add(CaldavCollection.USER_CALENDAR_COLLECTION);
            add(CaldavCollection.USER_ADDRESS_COLLECTION);}}
        );
        collectionCollectionMap.put(CaldavCollection.USER_CALENDAR_COLLECTION, new ArrayList<CaldavCollection>(){{
            add(CaldavCollection.USER_CALENDAR_ICAL_COLLECTION);}}
        );
        collectionCollectionMap.put(CaldavCollection.USER_ADDRESS_COLLECTION, new ArrayList<CaldavCollection>(){{
            add(CaldavCollection.USER_ADDRESS_ICAL_COLLECTION);}}
        );
        collectionCollectionMap.put(CaldavCollection.USER_CALENDAR_ICAL_COLLECTION, new ArrayList<CaldavCollection>());
        collectionCollectionMap.put(CaldavCollection.USER_ADDRESS_ICAL_COLLECTION, new ArrayList<CaldavCollection>());

        this.collectionPropertyMap = new ConcurrentHashMap<>();
        // main collection configuration
        collectionPropertyMap.put(CaldavCollection.MAIN_COLLECTTION,new HashMap<CaldavProperty, String>(){{
            put(CaldavProperty.RESOURCE_TYPE, "</collection>");}}
            );
        collectionPropertyMap.put(CaldavCollection.MAIN_COLLECTTION, new HashMap<CaldavProperty, String>(){{
            put(CaldavProperty.CURRENT_USER_PRINCIPAL, "<href>/" + ExpressionEnum.USERNAME.toString() + "/</href>");}}
            );
        collectionPropertyMap.put(CaldavCollection.MAIN_COLLECTTION, new HashMap<CaldavProperty, String>(){{
            put(CaldavProperty.PRINCIPAL_URL, "<href>/" + ExpressionEnum.USERNAME.toString() + "/</href>");}}
            );
        collectionPropertyMap.put(CaldavCollection.MAIN_COLLECTTION, new HashMap<CaldavProperty, String>(){{
            put(CaldavProperty.CALENDAR_HOME_SET, "<href>/" + ExpressionEnum.USERNAME.toString() + "/</href>");}}
            );
        collectionPropertyMap.put(CaldavCollection.MAIN_COLLECTTION, new HashMap<CaldavProperty, String>(){{
            put(CaldavProperty.CALENDAR_USER_ADDRESS_SET, "<href>/" + ExpressionEnum.USERNAME.toString() + "/</href>" + "<href>mailto:" + ExpressionEnum.USER_MAIL.toString() + "</href>");}}
            );
        collectionPropertyMap.put(CaldavCollection.MAIN_COLLECTTION, new HashMap<CaldavProperty, String>(){{
            put(CaldavProperty.CALENDAR_HOME_SET, "<href>/" + ExpressionEnum.USERNAME.toString() + "/</href>");}}
            );
    }
}
