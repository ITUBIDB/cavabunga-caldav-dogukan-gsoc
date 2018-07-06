package tr.edu.itu.cavabunga.cavabungacaldav.configuration.caldav;

import lombok.Data;
import org.springframework.stereotype.Component;
import tr.edu.itu.cavabunga.cavabungacaldav.caldav.enumerator.CaldavProperty;
import tr.edu.itu.cavabunga.cavabungacaldav.caldav.enumerator.ExpressionEnum;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Data
public class MainCollectionConfiguration extends CaldavCollectionConfiguration {
    private Map<CaldavProperty, List<String>> collectionPropertyMap;

    public MainCollectionConfiguration(){
        this.collectionPropertyMap = new ConcurrentHashMap<>();
        collectionPropertyMap.put(CaldavProperty.RESOURCE_TYPE,  Arrays.asList("</collection>"));
        collectionPropertyMap.put(CaldavProperty.CURRENT_USER_PRINCIPAL, Arrays.asList("<href>/" + ExpressionEnum.USERNAME.toString() + "/</href>"));
        collectionPropertyMap.put(CaldavProperty.PRINCIPAL_URL, Arrays.asList("<href>/" + ExpressionEnum.USERNAME.toString() + "/</href>"));
        collectionPropertyMap.put(CaldavProperty.CALENDAR_HOME_SET, Arrays.asList("<href>/" + ExpressionEnum.USERNAME.toString() + "/</href>"));
        collectionPropertyMap.put(CaldavProperty.CALENDAR_USER_ADDRESS_SET, Arrays.asList("<href>/" + ExpressionEnum.USERNAME.toString() + "/</href>","<href>mailto:" + ExpressionEnum.USER_MAIL.toString() + "</href>"));
        collectionPropertyMap.put(CaldavProperty.CALENDAR_HOME_SET, Arrays.asList("<href>/" + ExpressionEnum.USERNAME.toString() + "/</href>"));
    }
}
