package tr.edu.itu.cavabunga.cavabungacaldav.caldav.build.response;

import lombok.Data;
import org.springframework.stereotype.Component;
import tr.edu.itu.cavabunga.cavabungacaldav.configuration.caldav.MainCollectionConfiguration;

@Data
@Component
public class MainCollectionResponseBuilder implements CaldavResponseBuilder {
    private MainCollectionConfiguration mainCollectionConfiguration;


}
