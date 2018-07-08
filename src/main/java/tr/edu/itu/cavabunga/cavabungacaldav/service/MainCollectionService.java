package tr.edu.itu.cavabunga.cavabungacaldav.service;

import org.jdom2.Element;
import org.jdom2.Namespace;
import org.jdom2.input.SAXBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import tr.edu.itu.cavabunga.cavabungacaldav.caldav.AbstractCaldavCollection;
import tr.edu.itu.cavabunga.cavabungacaldav.caldav.build.collection.CaldavCollectionBuilderImpl;
import tr.edu.itu.cavabunga.cavabungacaldav.caldav.build.response.CaldavResponseBuilder;
import tr.edu.itu.cavabunga.cavabungacaldav.caldav.build.response.CaldavResponseBuilderImpl;
import tr.edu.itu.cavabunga.cavabungacaldav.caldav.enumerator.CaldavCollection;
import tr.edu.itu.cavabunga.cavabungacaldav.caldav.enumerator.CaldavRequestMethod;
import tr.edu.itu.cavabunga.cavabungacaldav.configuration.caldav.CaldavCollectionConfiguration;
import tr.edu.itu.cavabunga.cavabungacaldav.exception.CaldavException;
import java.io.StringReader;

@Service
public class MainCollectionService {
    private CaldavCollectionConfiguration caldavCollectionConfigurationImpl;
    private CaldavCollectionBuilderImpl caldavCollectionBuilderImpl;
    private CaldavResponseBuilderImpl caldavResponseBuilderImpl;


    @Autowired
    public MainCollectionService(CaldavCollectionConfiguration caldavCollectionConfigurationImpl,
                                 CaldavCollectionBuilderImpl caldavCollectionBuilderImpl,
                                 CaldavResponseBuilderImpl caldavResponseBuilderImpl){
        this.caldavCollectionConfigurationImpl = caldavCollectionConfigurationImpl;
        this.caldavCollectionBuilderImpl = caldavCollectionBuilderImpl;
        this.caldavResponseBuilderImpl = caldavResponseBuilderImpl;
    }

    public String getCaldavResponse(String httpMethod,
                                    String requestBody,
                                    UserDetails userDetails){
        CaldavRequestMethod method = CaldavRequestMethod.convertToEnum(httpMethod);
        switch (method){
            case GET:
                return getGetResponse(requestBody, userDetails);
            case POST:
                return getPostResponse(requestBody, userDetails);
            case PUT:
                return getPutResponse(requestBody, userDetails);
            case DELETE:
                return getDeleteResponse(requestBody, userDetails);
            case PATCH:
                return getPatchResponse(requestBody, userDetails);
            case OPTIONS:
                return getOptionsResponse(requestBody, userDetails);
            case HEAD:
                return getHeadResponse(requestBody, userDetails);
            case PROPFIND:
                return getPorpfindResponse(requestBody, userDetails);
            case PROPPATCH:
                return getProppatchResponse(requestBody, userDetails);
            case REPORT:
                return getReportResponse(requestBody, userDetails);
            default:
                throw new CaldavException("Method enum didn't match any");
        }
    }


    public String getGetResponse(String requestBody, UserDetails userDetails){
        return "";
    }

    public String getPostResponse(String requestBody, UserDetails userDetails){
        return "";
    }

    public String getPutResponse(String requestBody, UserDetails userDetails){
        return "";
    }

    public String getDeleteResponse(String requestBody, UserDetails userDetails){
        return "";
    }

    public String getPatchResponse(String requestBody, UserDetails userDetails){
        return "";
    }

    public String getOptionsResponse(String requestBody, UserDetails userDetails){
        return "";
    }

    public String getHeadResponse(String requestBody, UserDetails userDetails){
        return "";
    }

    public String getPorpfindResponse(String requestBody, UserDetails userDetails){

        AbstractCaldavCollection collection = this.caldavCollectionBuilderImpl.build(CaldavCollection.MAIN_COLLECTTION, userDetails);
        SAXBuilder xmlBuilder = new SAXBuilder();
        org.jdom2.Document document;

        try{
            document = xmlBuilder.build(new StringReader(requestBody));
        }catch (Exception e){
            throw new CaldavException("XML Parsing error, " + e.getMessage());
        }

        if(!document.getRootElement().getName().equals("propfind")){
            throw new CaldavException("propfind root element couldn found");
        }

        Element propfind = document.getRootElement();

        if(!propfind.getChildren().get(0).getName().equals("prop")){
            throw new CaldavException("prop element cound not found");
        }
        Element prop = propfind.getChildren().get(0);

        return this.caldavResponseBuilderImpl.getResponse(collection, prop);
    }

    public String getProppatchResponse(String requestBody, UserDetails userDetails){
        return "";
    }

    public String getReportResponse(String requestBody, UserDetails userDetails){
        return "";
    }
}
