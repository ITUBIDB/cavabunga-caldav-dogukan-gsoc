package tr.edu.itu.cavabunga.cavabungacaldav.caldav.build.response;

import lombok.Data;
import org.apache.commons.lang3.StringEscapeUtils;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.Namespace;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.XMLOutputter;
import org.springframework.stereotype.Component;
import tr.edu.itu.cavabunga.cavabungacaldav.caldav.AbstractCaldavCollection;
import tr.edu.itu.cavabunga.cavabungacaldav.caldav.AbstractCaldavProperty;
import tr.edu.itu.cavabunga.cavabungacaldav.caldav.enumerator.CaldavProperty;
import tr.edu.itu.cavabunga.cavabungacaldav.configuration.caldav.CaldavCollectionConfiguration;

import java.util.ArrayList;
import java.util.List;

@Data
@Component
public class CaldavResponseBuilderImpl implements CaldavResponseBuilder {
    private CaldavCollectionConfiguration caldavCollectionConfigurationImpl;
    private Element multistatus = new Element("multistatus", Namespace.getNamespace("D","DAV:"));

    public String getPropfindResponse(AbstractCaldavCollection collection, Element prop){
        Document result = new Document();
        multistatus.detach();
        multistatus.addContent(this.buildCollectionResponse(collection,prop));
        result.setRootElement(multistatus);

        this.multistatus = new Element("multistatus", Namespace.getNamespace("D","DAV:"));
        return StringEscapeUtils.unescapeXml(new XMLOutputter().outputString(result));
    }

    public String getReportResponse(AbstractCaldavCollection collection, Element prop){
        Document result = new Document();
        multistatus.detach();
        multistatus.addContent(this.buildCollectionResponse(collection,prop));
        result.setRootElement(multistatus);

        this.multistatus = new Element("multistatus", Namespace.getNamespace("D","DAV:"));
        return StringEscapeUtils.unescapeXml(new XMLOutputter().outputString(result));
    }

    public Element buildCollectionResponse(AbstractCaldavCollection collection, Element prop) {
        Element response = new Element("response", prop.getNamespace());
        SAXBuilder xmlBuilder = new SAXBuilder();
        List<Element> foundProperties = new ArrayList<>();
        List<Element> notFoundProperties = new ArrayList<>();

        for (Element e : prop.getChildren()) {
            String t = new String();
            try {
                t = CaldavProperty.convertToEnum(e.getName()).create().getClass().getName();
            } catch (Exception exp) {
                notFoundProperties.add(e);
                continue;
            }

            boolean found = false;
            for (AbstractCaldavProperty p : collection.getProperties()) {
                Element add = xmlBuilder.getJDOMFactory().element(p.getXmlTag());
                if (t.equals(p.getClass().getName())) {
                    add.setText(p.getXmlValue());
                    add.setNamespace(e.getNamespace());
                    found = true;
                    if (!foundProperties.contains(add)) {
                        foundProperties.add(add);
                    }
                }
            }

            if (!found) {
                notFoundProperties.add(e);
            }
        }

        Element propstatFound = new Element("propstat", prop.getNamespace());
        Element propFound = new Element("prop", prop.getNamespace());
        Element statusFound = new Element("status", prop.getNamespace());
        statusFound.setText("HTTP/1.1 200 OK");
        for (Element k : foundProperties) {
            Element z = k.clone();
            z.detach();
            propFound.addContent(z);
        }
        propFound.detach();
        propstatFound.addContent(propFound);
        statusFound.detach();
        propstatFound.addContent(statusFound);

        Element propstatNotFound = new Element("propstat", prop.getNamespace());
        Element propNotFound = new Element("prop", prop.getNamespace());
        Element statusNotFound = new Element("status", prop.getNamespace());
        statusNotFound.setText("HTTP/1.1 404 Not Found");
        for (Element q : notFoundProperties) {
            Element r = q.clone();
            r.detach();
            propNotFound.addContent(r);
        }
        propNotFound.detach();
        propstatNotFound.addContent(propNotFound);
        statusNotFound.detach();
        propstatNotFound.addContent(statusNotFound);
        propstatFound.detach();
        propstatNotFound.detach();

        //Element uri = new Element("href");
        //uri.setText(collection.getUri());
        //uri.detach();
        response.setText("<href>" + collection.getUri() + "</href>");
        //response.addContent(uri);
        response.addContent(propstatFound);
        response.addContent(propstatNotFound);

        this.multistatus.detach();
        this.multistatus.addContent(response);
        if(collection.getCollections() != null && !collection.getCollections().isEmpty()){
            for(AbstractCaldavCollection c : collection.getCollections()){
                this.multistatus.detach();
                this.multistatus.addContent(this.buildCollectionResponse(c, prop));
            }
        }

        response.detach();
        return response;
    }
}