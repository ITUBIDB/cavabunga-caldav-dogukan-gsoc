package tr.edu.itu.cavabunga.cavabungacaldav.service;

import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import tr.edu.itu.cavabunga.cavabungacaldav.caldav.AbstractCaldavCollection;
import tr.edu.itu.cavabunga.cavabungacaldav.caldav.build.collection.CaldavCollectionBuilderImpl;
import tr.edu.itu.cavabunga.cavabungacaldav.caldav.build.response.CaldavResponseBuilderImpl;
import tr.edu.itu.cavabunga.cavabungacaldav.caldav.enumerator.CaldavCollection;
import tr.edu.itu.cavabunga.cavabungacaldav.caldav.enumerator.CaldavRequestMethod;
import tr.edu.itu.cavabunga.cavabungacaldav.configuration.caldav.CaldavCollectionConfiguration;
import tr.edu.itu.cavabunga.cavabungacaldav.exception.CaldavException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

    /*public String getCaldavResponse(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    String requestBody,
                                    UserDetails userDetails,
                                    CaldavCollection caldavCollection){
        CaldavRequestMethod method = CaldavRequestMethod.convertToEnum(httpServletRequest.getMethod());
        switch (method){
            case GET:
                return getGetResponse(httpServletRequest, requestBody, userDetails, caldavCollection);
            case POST:
                return getPostResponse(httpServletRequest, requestBody, userDetails, caldavCollection);
            case PUT:
                return getPutResponse(httpServletRequest, requestBody, userDetails, caldavCollection);
            case DELETE:
                return getDeleteResponse(httpServletRequest, requestBody, userDetails, caldavCollection);
            case PATCH:
                return getPatchResponse(httpServletRequest, requestBody, userDetails, caldavCollection);
            case OPTIONS:
                return getOptionsResponse(httpServletResponse,httpServletRequest, requestBody, userDetails, caldavCollection);
            case HEAD:
                return getHeadResponse(httpServletRequest, requestBody, userDetails, caldavCollection);
            case PROPFIND:
                return getPropfindResponse(httpServletRequest, requestBody, userDetails, caldavCollection);
            case PROPPATCH:
                return getProppatchResponse(httpServletRequest, requestBody, userDetails, caldavCollection);
            case REPORT:
                return getReportResponse(httpServletRequest, requestBody, userDetails, caldavCollection);
            default:
                throw new CaldavException("Method enum didn't match any");
        }
    }*/


    public String getGetResponse(UserDetails userDetails, CaldavCollection caldavCollection){
        return "";
    }

    public String getPostResponse( UserDetails userDetails, CaldavCollection caldavCollection){
        return "";
    }

    public String getPutResponse(UserDetails userDetails, CaldavCollection caldavCollection){
        return "";
    }

    public String getDeleteResponse(UserDetails userDetails, CaldavCollection caldavCollection){
        return "";
    }

    public String getPatchResponse(UserDetails userDetails, CaldavCollection caldavCollection){
        return "";
    }

    public String getOptionsResponse( String requestBody, CaldavCollection caldavCollection){
        return "";
    }

    public String getHeadResponse(String requestBody, UserDetails userDetails, CaldavCollection caldavCollection){
        return   "";
    }

    public String getPropfindResponse(String requestBody, UserDetails userDetails, CaldavCollection caldavCollection, String depth){

        AbstractCaldavCollection collection = this.caldavCollectionBuilderImpl.build(caldavCollection, userDetails, depth);
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

        return this.caldavResponseBuilderImpl.getPropfindResponse(collection, prop);
    }

    public String getProppatchResponse(UserDetails userDetails, CaldavCollection caldavCollection){
        return "";
    }

    public String getReportResponse(String requestBody, UserDetails userDetails, CaldavCollection caldavCollection, String depth){
        AbstractCaldavCollection collection = this.caldavCollectionBuilderImpl.build(caldavCollection, userDetails, depth);
        SAXBuilder xmlBuilder = new SAXBuilder();
        org.jdom2.Document document;

        try{
            document = xmlBuilder.build(new StringReader(requestBody));
        }catch (Exception e){
            throw new CaldavException("XML Parsing error, " + e.getMessage());
        }

        Element prop;
        if(document.getRootElement().getName().equals("calendar-query")){
            Element propfind = document.getRootElement();
            if(!propfind.getChildren().get(0).getName().equals("prop")){
                throw new CaldavException("prop element couldn't not found in calendar-query");
            }
            prop = propfind.getChildren().get(0);

            return this.caldavResponseBuilderImpl.getReportResponse(collection, prop);
        }else if(document.getRootElement().getName().equals("calendar-multiget")){
            Element propfind = document.getRootElement();
            if(!propfind.getChildren().get(0).getName().equals("prop")){
                throw new CaldavException("prop element couldn't not found calendar-multiget");
            }
            prop = propfind.getChildren().get(0);
            return this.caldavResponseBuilderImpl.getReportResponse(collection, prop);
        }else{
            throw new CaldavException("cannot found calendar-query or calendar-multiget");
        }
    }
}
