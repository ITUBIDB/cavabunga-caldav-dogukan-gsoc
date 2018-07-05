package tr.edu.itu.cavabunga.cavabungacaldav.caldav;

import lombok.Data;

@Data
abstract public class AbstractCaldavProperty {
    private String key;
    private String value;
}
