package tr.edu.itu.cavabunga.cavabungacaldav.configuration.caldav;

public interface CaldavCollectionConfiguration {
    java.util.Map<tr.edu.itu.cavabunga.cavabungacaldav.caldav.enumerator.CaldavCollection, java.util.Map<tr.edu.itu.cavabunga.cavabungacaldav.caldav.enumerator.CaldavProperty, String>> getCollectionPropertyMap();

    java.util.Map<tr.edu.itu.cavabunga.cavabungacaldav.caldav.enumerator.CaldavCollection, java.util.List<tr.edu.itu.cavabunga.cavabungacaldav.caldav.enumerator.CaldavCollection>> getCollectionCollectionMap();

    void setCollectionPropertyMap(java.util.Map<tr.edu.itu.cavabunga.cavabungacaldav.caldav.enumerator.CaldavCollection, java.util.Map<tr.edu.itu.cavabunga.cavabungacaldav.caldav.enumerator.CaldavProperty, String>> collectionPropertyMap);

    void setCollectionCollectionMap(java.util.Map<tr.edu.itu.cavabunga.cavabungacaldav.caldav.enumerator.CaldavCollection, java.util.List<tr.edu.itu.cavabunga.cavabungacaldav.caldav.enumerator.CaldavCollection>> collectionCollectionMap);

    boolean equals(Object o);

    int hashCode();

    String toString();
}
