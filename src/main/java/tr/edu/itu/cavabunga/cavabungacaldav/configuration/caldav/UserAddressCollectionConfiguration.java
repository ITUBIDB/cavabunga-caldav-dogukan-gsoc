package tr.edu.itu.cavabunga.cavabungacaldav.configuration.caldav;

import lombok.Data;
import org.springframework.stereotype.Component;
import tr.edu.itu.cavabunga.cavabungacaldav.caldav.enumerator.CaldavCollection;
import java.util.ArrayList;
import java.util.List;

@Component
@Data
public class UserAddressCollectionConfiguration extends CaldavCollectionConfiguration {
    private List<CaldavCollection> collectionCollectionList;

    public UserAddressCollectionConfiguration(){
        this.collectionCollectionList = new ArrayList<>();
        collectionCollectionList.add(CaldavCollection.USER_CALENDAR_ICAL_COLLECTION);
    }
}
