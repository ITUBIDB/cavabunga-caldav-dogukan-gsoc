package tr.edu.itu.cavabunga.cavabungacaldav.caldav.build.response;

import lombok.Data;
import org.jdom2.Document;
import org.jdom2.Element;
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
    private Element multistatus = new Element("multistatus");

    public String getResponse(AbstractCaldavCollection collection, Element prop){
        Document result = new Document();
        multistatus.detach();
        multistatus.addContent(this.buildPropfindResponse(collection,prop));
        result.setRootElement(multistatus);
        return new XMLOutputter().outputString(result);
    }

    public Element buildPropfindResponse(AbstractCaldavCollection collection, Element prop) {
        Element response = new Element("response", prop.getNamespace());
        SAXBuilder xmlBuilder = new SAXBuilder();
        List<Element> foundProperties = new ArrayList<>();
        List<Element> notFoundProperties = new ArrayList<>();

        for (Element e : prop.getChildren()) {
            String t;
            try {
                t = CaldavProperty.convertToEnum(e.getName()).create().getClass().getName();
            } catch (Exception exp) {
                notFoundProperties.add(e);
                continue;
            }

            boolean found = false;
            for (AbstractCaldavProperty p : collection.getProperties()) {
                Element add = xmlBuilder.getJDOMFactory().element(p.getXmlTag());
                String test = p.getClass().getName();
                if (t.equals(test)) {
                    add.setText(p.getXmlValue());
                    add.setNamespace(e.getNamespace());
                    if (!foundProperties.contains(add)) {
                        foundProperties.add(add);
                        found = true;
                        break;
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
            System.out.println(k.getName());
            k.detach();
            propFound.addContent(k);
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
            System.out.println(q.getName());
            q.detach();
            propNotFound.addContent(q);
        }
        propNotFound.detach();
        propstatNotFound.addContent(propNotFound);
        statusNotFound.detach();
        propstatNotFound.addContent(statusNotFound);

        propstatFound.detach();
        propstatNotFound.detach();

        response.addContent(new Element(collection.getClass().getSimpleName()));
        response.addContent(propstatFound);
        response.addContent(propstatNotFound);

        this.multistatus.detach();
        this.multistatus.addContent(response);
        if(collection.getCollections() != null && !collection.getCollections().isEmpty()){
            for(AbstractCaldavCollection c : collection.getCollections()){
                this.multistatus.detach();
                this.multistatus.addContent(this.buildPropfindResponse(c, prop));
            }
        }

        response.detach();
        return response;
    }
}