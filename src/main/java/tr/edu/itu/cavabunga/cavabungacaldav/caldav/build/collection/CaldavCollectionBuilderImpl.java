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
import tr.edu.itu.cavabunga.cavabungacaldav.configuration.caldav.CaldavCollectionConfiguration;
import tr.edu.itu.cavabunga.cavabungacaldav.configuration.caldav.CaldavCollectionConfigurationImpl;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class CaldavCollectionBuilderImpl implements CaldavCollectionBuilder {
    private CaldavCollectionFactory caldavCollectionFactory;
    private CaldavPropertyFactory caldavPropertyFactory;
    private CaldavCollectionConfiguration caldavCollectionConfiguration;
    private Map<ExpressionEnum, String> argumentReplaceList;

    @Autowired
    public CaldavCollectionBuilderImpl(CaldavCollectionFactory caldavCollectionFactory,
                                       CaldavPropertyFactory caldavPropertyFactory,
                                       CaldavCollectionConfiguration caldavCollectionConfiguration){
        this.caldavCollectionFactory = caldavCollectionFactory;
        this.caldavPropertyFactory = caldavPropertyFactory;
        this.caldavCollectionConfiguration = caldavCollectionConfiguration;
        this.argumentReplaceList = new ConcurrentHashMap<>();

    }

    public AbstractCaldavCollection build(CaldavCollection caldavCollection,  UserDetails userDetails){
        this.argumentReplaceList.put(ExpressionEnum.USERNAME, userDetails.getUsername());
        this.argumentReplaceList.put(ExpressionEnum.USER_MAIL, userDetails.getUsername() + "@itu.edu.tr");
        return buildCollection(caldavCollection);
    }

    public AbstractCaldavCollection buildProperties(AbstractCaldavCollection collection, CaldavCollection caldavCollection){
           try {
               for (Map.Entry<CaldavProperty, String> p : this.caldavCollectionConfiguration.getCollectionPropertyMap().get(caldavCollection).entrySet()){
                   AbstractCaldavProperty property;
                   property = this.caldavPropertyFactory.createProperty(p.getKey());
                   property.setXmlTag(p.getKey().toString());
                   property.setXmlValue(expressionReplacer(caldavCollection, p.getKey()));
                   collection.addProperty(property);
               }
               return collection;
           }catch (Exception e){
                return collection;
           }
    }

    public AbstractCaldavCollection buildCollection(CaldavCollection caldavCollection){
        AbstractCaldavCollection collection = this.caldavCollectionFactory.createCollection(caldavCollection);
        if(this.caldavCollectionConfiguration.getCollectionCollectionMap().get(caldavCollection) != null ||
                !this.caldavCollectionConfiguration.getCollectionCollectionMap().get(caldavCollection).isEmpty()){
            for (CaldavCollection c : this.caldavCollectionConfiguration.getCollectionCollectionMap().get(caldavCollection)) {
                collection.setUri("");
                collection.addCollection(buildProperties(this.buildCollection(c), c));
            }
        }
        return buildProperties(collection, caldavCollection);
    }


    public void buildHeaders(String userName, CaldavRequestMethod method){

    }

    public void buildContent(String userName, CaldavRequestMethod method){

    }

    public String expressionReplacer(CaldavCollection caldavCollection, CaldavProperty p){
        String defaultExpression = this.caldavCollectionConfiguration.getCollectionPropertyMap().get(caldavCollection).get(p);
        String result;
        result = defaultExpression;
        for(Map.Entry<ExpressionEnum, String> e : this.argumentReplaceList.entrySet()){
                result = result.replace(e.getKey().toString(), e.getValue());
        }
        return result;
    }
}
