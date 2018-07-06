package tr.edu.itu.cavabunga.cavabungacaldav.caldav.build.response;

import org.springframework.beans.factory.annotation.Autowired;
import tr.edu.itu.cavabunga.cavabungacaldav.caldav.AbstractCaldavCollection;
import tr.edu.itu.cavabunga.cavabungacaldav.caldav.enumerator.CaldavRequestMethod;
import tr.edu.itu.cavabunga.cavabungacaldav.configuration.caldav.MainCollectionConfiguration;

public class MainCollectionResponseBuilder implements CaldavResponseBuilder {
    private MainCollectionConfiguration mainCollectionConfiguration;


}
