package tr.edu.itu.cavabunga.cavabungacaldav.caldav.enumerator;

import tr.edu.itu.cavabunga.cavabungacaldav.caldav.AbstractCaldavCollection;
import tr.edu.itu.cavabunga.cavabungacaldav.caldav.collection.*;

public enum CaldavCollection {
    MAIN_COLLECTTION{
        public AbstractCaldavCollection create(){
            return new MainCollection();
        }
    },
    USER_ADDRESS_COLLECTION{
        public AbstractCaldavCollection create(){
            return new UserAddressCollection();
        }
    },
    USER_ADDRESS_ICAL_COLLECTION{
        public AbstractCaldavCollection create(){
            return new UserAddressIcalCollection();
        }
    },
    USER_CALENDAR_COLLECTION{
        public AbstractCaldavCollection create(){
            return new UserCalendarCollection();
        }
    },
    USER_CALENDAR_ICAL_COLLECTION{
        public AbstractCaldavCollection create(){
            return new UserCalendarIcalCollection();
        }
    },
    USER_COLLECTION{
        public AbstractCaldavCollection create(){
            return new UserCollection();
        }
    };

    public AbstractCaldavCollection create(){
        return null;
    }
}
