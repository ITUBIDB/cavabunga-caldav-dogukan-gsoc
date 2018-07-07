package tr.edu.itu.cavabunga.cavabungacaldav.caldav.enumerator;

import tr.edu.itu.cavabunga.cavabungacaldav.caldav.AbstractCaldavProperty;
import tr.edu.itu.cavabunga.cavabungacaldav.caldav.property.*;
import tr.edu.itu.cavabunga.cavabungacaldav.exception.CaldavException;

public enum CaldavProperty {
    RESOURCE_TYPE("resourcetype"){
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
    FILTER("filter"){
        public AbstractCaldavProperty create() { return new Filter(); }
    },
    COMP_FILTER("comp-filter"){
        public AbstractCaldavProperty create() { return new CompFilter(); }
    },
    TIME_RANGE("time-range"){
        public AbstractCaldavProperty create() { return new TimeRange();}
    },
    CALENDAR_QUERY("calendar-query"){
        public AbstractCaldavProperty create() { return new CalendarQuery();}
    },
    CALENDAR_DATA("calendar-data"){
        public AbstractCaldavProperty create() { return new CalendarData(); }
    },
    CALENDAR_MULTIGET("calendar-multiget"){
        public AbstractCaldavProperty create() { return new CalendarMultiget();}
    },
    MULTISTATUS("multistatus"){
        public AbstractCaldavProperty create() { return new Multistatus(); }
    },
    RESPONSE("response"){
        public AbstractCaldavProperty create() { return new Response(); }
    },
    PROPSTAT("propstat"){
        public AbstractCaldavProperty create() { return new Propstat(); }
    },
    PROP("prop"){
        public AbstractCaldavProperty create() { return new Prop(); }
    },
    STATUS("status"){
        public AbstractCaldavProperty create() { return new Status();}
    },
    GET_CTAG("getctag"){
        public AbstractCaldavProperty create() { return new GetCTag();}
    };

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
        for(CaldavProperty c : CaldavProperty.values()){
            if(c.toString().equals(propertyXmlName)){
                return c;
            }
        }
        return null;
    }
}
