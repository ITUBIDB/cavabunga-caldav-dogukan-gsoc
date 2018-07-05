package tr.edu.itu.cavabunga.cavabungacaldav.caldav.build.collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import tr.edu.itu.cavabunga.cavabungacaldav.caldav.AbstractCaldavCollection;
import tr.edu.itu.cavabunga.cavabungacaldav.caldav.AbstractCaldavProperty;
import tr.edu.itu.cavabunga.cavabungacaldav.caldav.enumerator.CaldavCollection;
import tr.edu.itu.cavabunga.cavabungacaldav.caldav.enumerator.CaldavProperty;
import tr.edu.itu.cavabunga.cavabungacaldav.caldav.factory.CaldavCollectionFactory;
import tr.edu.itu.cavabunga.cavabungacaldav.caldav.factory.CaldavPropertyFactory;

public class MainCollectionBuilder implements CaldavCollectionBuilder {
    private CaldavCollectionFactory caldavCollectionFactory;
    private CaldavPropertyFactory caldavPropertyFactory;

    @Autowired
    public MainCollectionBuilder(CaldavCollectionFactory caldavCollectionFactory,
                                 CaldavPropertyFactory caldavPropertyFactory){
        this.caldavCollectionFactory = caldavCollectionFactory;
        this.caldavPropertyFactory = caldavPropertyFactory;

    }

    public AbstractCaldavCollection build(UserDetails userDetails,){
        AbstractCaldavCollection collection = caldavCollectionFactory.createCollection(CaldavCollection.MAIN_COLLECTTION);
        AbstractCaldavProperty resourceType = caldavPropertyFactory.createProperty(CaldavProperty.RESOURCE_TYPE);
            resourceType.setValue("<collection/>");
        AbstractCaldavProperty currentUserPricipal = caldavPropertyFactory.createProperty(CaldavProperty.CURRENT_USER_PRINCIPAL);
            currentUserPricipal.setValue("<href>/" + userDetails.getUsername() + "/</href>");
        AbstractCaldavProperty principalUrl = caldavPropertyFactory.createProperty(CaldavProperty.PRINCIPAL_URL);
            principalUrl.setValue("<href>/" + userDetails.getUsername() + "/</href>");
        AbstractCaldavProperty calendarHomeSet = caldavPropertyFactory.createProperty(CaldavProperty.CALENDAR_HOME_SET);
            calendarHomeSet.setValue("<href>/" + userDetails.getUsername() + "/</href>");

    }
}
