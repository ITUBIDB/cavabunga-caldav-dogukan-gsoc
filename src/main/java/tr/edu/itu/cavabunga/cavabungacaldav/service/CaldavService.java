package tr.edu.itu.cavabunga.cavabungacaldav.service;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.jdom2.input.SAXBuilder;
import org.springframework.stereotype.Service;
import sun.misc.IOUtils;
import sun.nio.ch.IOUtil;
import tr.edu.itu.cavabunga.cavabungacaldav.dav.CaldavRequestMethod;
import tr.edu.itu.cavabunga.cavabungacaldav.exception.CaldavException;

import java.io.StringReader;

@Service
public class CaldavService {
    public String getCaldavResponse(String method, String uri, String body, String userName){
        CaldavRequestMethod caldavRequestMethod;
        try {
            caldavRequestMethod = CaldavRequestMethod.valueOf(method);
        }catch (IllegalArgumentException e){
            throw new CaldavException("Request method: " + method + " is not in supported enum, " + e.getMessage());
        }

        switch (caldavRequestMethod){
            case GET:
                return processGetRequest();
            case POST:
                return processPostRequest();
            case PUT:
                return processPutRequest();
            case DELETE:
                return processDeleteRequest();
            case PATCH:
                return processPatchRequest();
            case OPTIONS:
                return processOptionsRequest();
            case HEAD:
                return processHeadRequest();
            case PROPFIND:
                return processPropfindRequest(uri,body, userName);
            case PROPPATCH:
                return processProppatchRequest();
            case REPORT:
                return processReportRequest();
            default:
                throw new CaldavException("No http method match found in enumerator method: " + method);
        }
    }

    public String processGetRequest(){
        return "";
    }

    public String processPostRequest(){
        return "";
    }

    public String processPutRequest(){
        return "";
    }

    public String processDeleteRequest(){
        return "";
    }

    public String processPatchRequest(){
        return "";
    }

    public String processOptionsRequest(){
        return "";
    }

    public String processHeadRequest(){
        return "";
    }

    public String processPropfindRequest(String uri, String body, String userName){
        SAXReader saxReader = new SAXReader();
        Document document;
        try {
            document = saxReader.read(new StringReader(body));
        }catch (Exception e){
            throw new CaldavException(e.getMessage());
        }

        Element element =  document.getRootElement();

    }

    public String processProppatchRequest(){
        return "";
    }

    public String processReportRequest(){
        return "";
    }
}
