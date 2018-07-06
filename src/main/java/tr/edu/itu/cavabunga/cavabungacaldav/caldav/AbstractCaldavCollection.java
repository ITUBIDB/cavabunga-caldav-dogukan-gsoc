package tr.edu.itu.cavabunga.cavabungacaldav.caldav;


import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
abstract public class AbstractCaldavCollection {
    private List<AbstractCaldavProperty> properties = new ArrayList<>();
    private List<String> header;
    private String content;

    public void addProperty(AbstractCaldavProperty property){
        this.properties.add(property);
    }
}
