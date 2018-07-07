package tr.edu.itu.cavabunga.cavabungacaldav.caldav.build.collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
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

@Component
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
        return buildProperties(this.caldavCollectionFactory.createCollection(CaldavCollection.MAIN_COLLECTTION));
    }

    public AbstractCaldavCollection buildProperties(AbstractCaldavCollection collection){
        for(CaldavProperty p : this.mainCollectionConfiguration.getCollectionPropertyMap().keySet()){
            AbstractCaldavProperty property;
            property = this.caldavPropertyFactory.createProperty(p);
            property.setXmlTag(p.toString());
            property.setXmlValue(expressionReplacer(p));
            collection.addProperty(property);
        }

        return collection;
    }

    public void buildHeaders(String userName, CaldavRequestMethod method){

    }

    public void buildContent(String userName, CaldavRequestMethod method){

    }

    public String expressionReplacer(CaldavProperty p){
        String defaultExpression = this.mainCollectionConfiguration.getCollectionPropertyMap().get(p);
        String result;
        result = defaultExpression;
        for(Map.Entry<ExpressionEnum, String> e : this.argumentReplaceList.entrySet()){
                result = result.replace(e.getKey().toString(), e.getValue());
        }

        return result;
    }
}
