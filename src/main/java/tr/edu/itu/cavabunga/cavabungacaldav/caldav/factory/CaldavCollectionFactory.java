package tr.edu.itu.cavabunga.cavabungacaldav.caldav.factory;

import org.springframework.stereotype.Component;
import tr.edu.itu.cavabunga.cavabungacaldav.caldav.AbstractCaldavCollection;
import tr.edu.itu.cavabunga.cavabungacaldav.caldav.enumerator.CaldavCollection;

@Component
public class CaldavCollectionFactory {
    public AbstractCaldavCollection createCollection(CaldavCollection caldavCollection){
        return caldavCollection.create();
    }
}
