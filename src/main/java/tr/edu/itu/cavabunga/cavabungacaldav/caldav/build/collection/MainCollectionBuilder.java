package tr.edu.itu.cavabunga.cavabungacaldav.caldav.build.collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import tr.edu.itu.cavabunga.cavabungacaldav.caldav.AbstractCaldavCollection;
import tr.edu.itu.cavabunga.cavabungacaldav.caldav.AbstractCaldavProperty;
import tr.edu.itu.cavabunga.cavabungacaldav.caldav.enumerator.CaldavCollection;
import tr.edu.itu.cavabunga.cavabungacaldav.caldav.enumerator.CaldavProperty;
import tr.edu.itu.cavabunga.cavabungacaldav.caldav.enumerator.CaldavRequestMethod;
import tr.edu.itu.cavabunga.cavabungacaldav.caldav.enumerator.ExpressionEnum;
import tr.edu.itu.cavabunga.cavabungacaldav.caldav.factory.CaldavCollectionFactory;
import tr.edu.itu.cavabunga.cavabungacaldav.caldav.factory.CaldavPropertyFactory;
import tr.edu.itu.cavabunga.cavabungacaldav.configuration.caldav.MainCollectionConfiguration;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MainCollectionBuilder implements CaldavCollectionBuilder {
    private CaldavCollectionFactory caldavCollectionFactory;
    private CaldavPropertyFactory caldavPropertyFactory;
    private MainCollectionConfiguration mainCollectionConfiguration;
    private Map<ExpressionEnum, String> argumentReplaceList;

    @Autowired
    public MainCollectionBuilder(CaldavCollectionFactory caldavCollectionFactory,
                                 CaldavPropertyFactory caldavPropertyFactory,
                                 MainCollectionConfiguration mainCollectionConfiguration){
        this.caldavCollectionFactory = caldavCollectionFactory;
        this.caldavPropertyFactory = caldavPropertyFactory;
        this.mainCollectionConfiguration = mainCollectionConfiguration;
        this.argumentReplaceList = new ConcurrentHashMap<>();

    }

    public AbstractCaldavCollection build(UserDetails userDetails){
        this.argumentReplaceList.put(ExpressionEnum.USERNAME, userDetails.getUsername());
        this.argumentReplaceList.put(ExpressionEnum.USER_MAIL, userDetails.getUsername() + "@itu.edu.tr");
    }

    public AbstractCaldavCollection buildProperties(AbstractCaldavCollection collection, String userName){
        for(CaldavProperty p : this.mainCollectionConfiguration.getCollectionPropertyMap().keySet()){
            AbstractCaldavProperty property;
            property = this.caldavPropertyFactory.createProperty(p);
            property.setXmlTag(p.toString());
            property.setXmlValue(expressionReplacer(this.mainCollectionConfiguration.getCollectionPropertyMap().get(p), this.argumentReplaceList));
            collection.addProperty(property);
        }

        return collection;
    }

    public AbstractCaldavCollection buildHeaders(String userName, CaldavRequestMethod method){

    }

    public AbstractCaldavCollection buildContent(String userName, CaldavRequestMethod method){

    }

    public String expressionReplacer(List<String> expressionList, Map<ExpressionEnum, String> argumentReplaceList){
        return "";
    }
}
