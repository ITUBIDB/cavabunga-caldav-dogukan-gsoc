package tr.edu.itu.cavabunga.cavabungacaldav.caldav.factory;

import org.springframework.stereotype.Component;
import tr.edu.itu.cavabunga.cavabungacaldav.caldav.AbstractCaldavProperty;
import tr.edu.itu.cavabunga.cavabungacaldav.caldav.enumerator.CaldavProperty;

@Component
public class CaldavPropertyFactory {
    public AbstractCaldavProperty createProperty(CaldavProperty caldavProperty){
        return caldavProperty.create();
    }
}
