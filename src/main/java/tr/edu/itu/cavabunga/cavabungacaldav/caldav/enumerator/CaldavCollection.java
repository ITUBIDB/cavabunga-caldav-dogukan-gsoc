package tr.edu.itu.cavabunga.cavabungacaldav.caldav.enumerator;

import tr.edu.itu.cavabunga.cavabungacaldav.caldav.AbstractCaldavCollection;
import tr.edu.itu.cavabunga.cavabungacaldav.caldav.collection.*;

public enum CaldavCollection {
    MAIN_COLLECTION("/"){
        public AbstractCaldavCollection create(){
            return new MainCollection();
        }
    },
    USER_ADDRESS_COLLECTION("/{username}/addresses/"){
        public AbstractCaldavCollection create(){
            return new UserAddressCollection();
        }
    },
    USER_ADDRESS_ICAL_COLLECTION("/{username}/addresses/{ical_file}"){
        public AbstractCaldavCollection create(){
            return new UserAddressIcalCollection();
        }
    },
    USER_CALENDAR_COLLECTION("/{username}/calendar/"){
        public AbstractCaldavCollection create(){
            return new UserCalendarCollection();
        }
    },
    USER_CALENDAR_ICAL_COLLECTION("/{username}/calendar/{ical_file}"){
        public AbstractCaldavCollection create(){
            return new UserCalendarIcalCollection();
        }
    },
    USER_COLLECTION("/{username}/"){
        public AbstractCaldavCollection create(){
            return new UserCollection();
        }
    };

    private String text;

    CaldavCollection(String text){
        this.text = text;
    }

    @Override
    public String toString(){
        return this.text;
    }

    public AbstractCaldavCollection create(){
        return null;
    }

}
