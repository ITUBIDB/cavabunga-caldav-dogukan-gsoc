package tr.edu.itu.cavabunga.cavabungacaldav.caldav.enumerator;

public enum CaldavRequestMethod {
    GET("GET"),
    POST("POST"),
    PUT("PUT"),
    DELETE("DELETE"),
    PATCH("PATCH"),
    OPTIONS("OPTIONS"),
    HEAD("HEAD"),
    PROPFIND("PROPFIND"),
    PROPPATCH("PROPPATCH"),
    REPORT("REPORT");

    private String value;

    CaldavRequestMethod(String text){
        this.value = text;
    }

    @Override
    public String toString(){
        return value;
    }
}
