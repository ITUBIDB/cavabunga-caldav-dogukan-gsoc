package tr.edu.itu.cavabunga.cavabungacaldav.caldav;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
abstract public class AbstractCaldavProperty {
    private String key;
    private String value;
    private String namespace;
    private List<String> attributes = new ArrayList<>();
    private List<AbstractCaldavProperty> properties = new ArrayList<>();
}
