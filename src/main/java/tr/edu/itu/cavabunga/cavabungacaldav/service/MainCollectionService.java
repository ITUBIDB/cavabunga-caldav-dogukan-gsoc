package tr.edu.itu.cavabunga.cavabungacaldav.service;

import org.jdom2.Element;
import org.jdom2.Namespace;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.XMLOutputter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import tr.edu.itu.cavabunga.cavabungacaldav.caldav.AbstractCaldavCollection;
import tr.edu.itu.cavabunga.cavabungacaldav.caldav.AbstractCaldavProperty;
import tr.edu.itu.cavabunga.cavabungacaldav.caldav.build.collection.MainCollectionBuilder;
import tr.edu.itu.cavabunga.cavabungacaldav.caldav.build.response.MainCollectionResponseBuilder;
import tr.edu.itu.cavabunga.cavabungacaldav.caldav.enumerator.CaldavProperty;
import tr.edu.itu.cavabunga.cavabungacaldav.caldav.enumerator.CaldavRequestMethod;
import tr.edu.itu.cavabunga.cavabungacaldav.configuration.caldav.MainCollectionConfiguration;
import tr.edu.itu.cavabunga.cavabungacaldav.exception.CaldavException;
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
        Namespace propfindNamespace;
        Namespace propNamespace;
        List<String> queriedProperties =  new ArrayList<>();
       // String result;

        try{
            document = xmlBuilder.build(new StringReader(requestBody));
        }catch (Exception e){
            throw new CaldavException("XML Parsing error, " + e.getMessage());
        }

        if(!document.getRootElement().getName().equals("propfind")){
            throw new CaldavException("propfind root element couldn found");
        }
        propfindNamespace = document.getRootElement().getNamespace();
        Element propfind = document.getRootElement();

        if(!propfind.getChildren().get(0).getName().equals("prop")){
            throw new CaldavException("prop element cound not found");
        }
        Element prop = propfind.getChildren().get(0);
        propNamespace = propfind.getChildren().get(0).getNamespace();

        List<Element> foundProperties = new ArrayList<>();
        List<Element> notFoundProperties = new ArrayList<>();
        for(Element e : prop.getChildren()){
            String t;
            try {
                t = CaldavProperty.convertToEnum(e.getName()).create().getClass().getName();
            }catch (Exception exp){
                notFoundProperties.add(e);
                continue;
            }

            boolean found = false;
            for(AbstractCaldavProperty p : collection.getProperties()){
                Element add = xmlBuilder.getJDOMFactory().element(p.getXmlTag());
                String test = p.getClass().getName();
                if(t.equals(test)){
                    add.setText(p.getXmlValue());
                    add.setNamespace(e.getNamespace());
                    if(!foundProperties.contains(add)){
                        foundProperties.add(add);
                        found = true;
                        break;
                    }
                }

            }

            if(!found){
                notFoundProperties.add(e);
            }
            //Element add = xmlBuilder.getJDOMFactory().element(p.getXmlTag());
        }

        org.jdom2.Document result = new org.jdom2.Document();
        Element multistatus = new Element("multistatus", propfindNamespace);
        Element response = new Element("response", propfindNamespace);

        Element propstatFound = new Element("propstat", propNamespace);
        Element propFound = new Element("prop", propNamespace);
        Element statusFound = new Element("status", propNamespace);
        statusFound.setText("HTTP/1.1 200 OK");
        for(Element k : foundProperties){
            System.out.println(k.getName());
            k.detach();
            propFound.addContent(k);
        }
        propFound.detach();
        propstatFound.addContent(propFound);
        statusFound.detach();
        propstatFound.addContent(statusFound);


        Element propstatNotFound = new Element("propstat", propNamespace);
        Element propNotFound = new Element("prop", propNamespace);
        Element statusNotFound = new Element("status", propNamespace);
        statusNotFound.setText("HTTP/1.1 404 Not Found");
        for(Element q : notFoundProperties){
            System.out.println(q.getName());
            q.detach();
            propNotFound.addContent(q);
        }
        propNotFound.detach();
        propstatNotFound.addContent(propNotFound);
        statusNotFound.detach();
        propstatNotFound.addContent(statusNotFound);

        propstatFound.detach();
        response.addContent(propstatFound);

        propstatNotFound.detach();
        response.addContent(propstatNotFound);

        response.detach();
        multistatus.addContent(response);

        multistatus.detach();
        result.setRootElement(multistatus);
        return new XMLOutputter().outputString(result);
    }

    public String getProppatchResponse(String requestBody, UserDetails userDetails){
        return "";
    }

    public String getReportResponse(String requestBody, UserDetails userDetails){
        return "";
    }
}
