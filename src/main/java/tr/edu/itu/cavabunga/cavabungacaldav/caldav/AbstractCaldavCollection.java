package tr.edu.itu.cavabunga.cavabungacaldav.caldav;


import lombok.Data;

import java.util.List;

@Data
abstract public class AbstractCaldavCollection {
    private List<AbstractCaldavProperty> properties;
    private List<String> header;
    private String content;
}
