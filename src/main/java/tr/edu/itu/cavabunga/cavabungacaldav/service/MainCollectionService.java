package tr.edu.itu.cavabunga.cavabungacaldav.service;

import org.dom4j.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import tr.edu.itu.cavabunga.cavabungacaldav.caldav.AbstractCaldavCollection;
import tr.edu.itu.cavabunga.cavabungacaldav.caldav.build.collection.MainCollectionBuilder;
import tr.edu.itu.cavabunga.cavabungacaldav.caldav.build.response.MainCollectionResponseBuilder;
import tr.edu.itu.cavabunga.cavabungacaldav.caldav.enumerator.CaldavProperty;
import tr.edu.itu.cavabunga.cavabungacaldav.caldav.enumerator.CaldavRequestMethod;
import tr.edu.itu.cavabunga.cavabungacaldav.configuration.caldav.MainCollectionConfiguration;
import tr.edu.itu.cavabunga.cavabungacaldav.exception.CaldavException;

import javax.xml.parsers.DocumentBuilder;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class MainCollectionService {
    private MainCollectionConfiguration mainCollectionConfiguration;
    private MainCollectionBuilder mainCollectionBuilder;
    private MainCollectionResponseBuilder mainCollectionResponseBuilder;

    @Autowired
    public MainCollectionService(MainCollectionConfiguration mainCollectionConfiguration,
                                 MainCollectionBuilder mainCollectionBuilder,
                                 MainCollectionResponseBuilder mainCollectionResponseBuilder){
        this.mainCollectionConfiguration = mainCollectionConfiguration;
        this.mainCollectionBuilder = mainCollectionBuilder;
        this.mainCollectionResponseBuilder = mainCollectionResponseBuilder;
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

        AbstractCaldavCollection collection = this.mainCollectionBuilder.build(userDetails);

        SAXBuilder xmlBuilder = new SAXBuilder();
        org.jdom2.Document document;
        String propfindNamespace;
        String propNamespace;

        try{
            document = xmlBuilder.build(new StringReader(requestBody));
        }catch (Exception e){
            throw new CaldavException("XML Parsing error");
        }

        if(!document.getRootElement().getName().equals("propfind")){
            throw new CaldavException("propfind root element couldn found");
        }
        Element propfind = document.getRootElement();

        if(!propfind.getChildren().get(0).getName().equals("prop")){
            throw new CaldavException("prop element cound not found");
        }
        Element prop = propfind.getChildren().get(0);

        for(Element e : prop.getChildren()){
            System.out.println(e.getName());
        }

        return "";
    }

    public String getProppatchResponse(String requestBody, UserDetails userDetails){
        return "";
    }

    public String getReportResponse(String requestBody, UserDetails userDetails){
        return "";
    }
}
