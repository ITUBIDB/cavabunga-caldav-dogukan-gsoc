package tr.edu.itu.cavabunga.cavabungacaldav.caldav.property;

import org.apache.commons.lang3.RandomStringUtils;
import tr.edu.itu.cavabunga.cavabungacaldav.caldav.AbstractCaldavProperty;

public class GetETag extends AbstractCaldavProperty {
    @Override
    public String getXmlValue(){
        return RandomStringUtils.randomAlphanumeric(12);
    }
}
