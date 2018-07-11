package tr.edu.itu.cavabunga.cavabungacaldav.caldav.property;

import org.apache.commons.lang3.RandomStringUtils;
import tr.edu.itu.cavabunga.cavabungacaldav.caldav.AbstractCaldavProperty;

import java.util.UUID;

public class GetCTag extends AbstractCaldavProperty {
    @Override
    public String getXmlValue(){
        return RandomStringUtils.randomAlphanumeric(12);
    }
}
