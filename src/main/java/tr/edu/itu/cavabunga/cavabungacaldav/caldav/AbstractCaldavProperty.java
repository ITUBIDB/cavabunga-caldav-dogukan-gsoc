package tr.edu.itu.cavabunga.cavabungacaldav.caldav;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
abstract public class AbstractCaldavProperty {
    private String xmlTag;
    private String xmlValue;
    private String xmlNamespace;
    private List<String> xmlAttributes = new ArrayList<>();
    private List<AbstractCaldavProperty> properties = new ArrayList<>();
 }
