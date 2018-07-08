package tr.edu.itu.cavabunga.cavabungacaldav.caldav.build.response;

import lombok.Data;
import org.springframework.stereotype.Component;
import tr.edu.itu.cavabunga.cavabungacaldav.configuration.caldav.CaldavCollectionConfiguration;

@Data
@Component
public class MainCollectionResponseBuilder implements CaldavResponseBuilder {
    private CaldavCollectionConfiguration caldavCollectionConfigurationImpl;


}
