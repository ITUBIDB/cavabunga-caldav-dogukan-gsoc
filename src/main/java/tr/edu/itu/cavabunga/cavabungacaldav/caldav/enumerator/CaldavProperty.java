package tr.edu.itu.cavabunga.cavabungacaldav.caldav.enumerator;

import tr.edu.itu.cavabunga.cavabungacaldav.caldav.AbstractCaldavProperty;
import tr.edu.itu.cavabunga.cavabungacaldav.caldav.property.*;
import tr.edu.itu.cavabunga.cavabungacaldav.exception.CaldavException;

public enum CaldavProperty {
    RESOURCE_TYPE("resource-type"){
        public AbstractCaldavProperty create(){
            return new ResourceType();
        }
    },
    CURRENT_USER_PRINCIPAL("current-user-principal"){
        public AbstractCaldavProperty create(){
            return new CurrentUserPricipal();
        }
    },
    PRINCIPAL_URL("principal-URL"){
        public AbstractCaldavProperty create(){
            return new PrincipalUrl();
        }
    },
    CALENDAR_HOME_SET("calendar-home-set"){
        public AbstractCaldavProperty create(){
            return new CalendarHomeSet();
        }
    },
    CALENDAR_USER_ADDRESS_SET("calendar-user-address-set"){
        public AbstractCaldavProperty create(){
            return new CalendarUserAddressSet();
        }
    },
    ADDRESSBOOK_HOME_SET("addressbook-home-set"){
        public AbstractCaldavProperty create(){
            return new AddressbookHomeSet();
        }
    },
    PRINCIPAL_ADDRESS("principal-address"){
        public AbstractCaldavProperty create(){
            return new PrincipalAddress();
        }
    },
    HREF("href"){
        public AbstractCaldavProperty create() { return new Href();}
    },
    COLLECTION("collection"){
        public AbstractCaldavProperty create() { return new Collection();}
    },
    DISPLAYNAME("displayname"){
        public AbstractCaldavProperty create() { return new Displayname();}
    },
    CALENDAR_DESCRIPTION("calendar-description"){
        public AbstractCaldavProperty create() { return new CalendarDescription(); }
    },
    SUPPORTED_CALENDAR_COMPONENT_SET("calendar-user-address-set"){
        public AbstractCaldavProperty create() { return new SupportedCalendarComponentSet(); }
    },
    CALENDAR_COLOR("calendar-color"){
        public AbstractCaldavProperty create() { return new CalendarColor(); }
    },
    CALENDAR("calendar"){
        public AbstractCaldavProperty create() { return new Calendar(); }
    },
    ADDRESSBOOK("addressbook"){
        public AbstractCaldavProperty create() { return new Addressbook(); }
    },
    COMP("comp"){
        public AbstractCaldavProperty create() { return new Comp(); }
    },
    GET_ETAG("getetag"){
        public AbstractCaldavProperty create() { return new GetETag();}
    },

    ;

    private String text;

    CaldavProperty(String text){
        this.text = text;
    }

    @Override
    public String toString(){
        return this.text;
    }

    public AbstractCaldavProperty create(){
        return null;
    }

    public static CaldavProperty convertToEnum(String propertyXmlName){
        try {
            return CaldavProperty.valueOf(propertyXmlName);
        }catch (IllegalArgumentException e){
            throw new CaldavException("Property xml name is not defined in CaldavProperty enum propertyXmlName: " + e.getMessage());
        }
    }
}
