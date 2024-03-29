package tr.edu.itu.cavabunga.cavabungacaldav.caldav.enumerator;

import tr.edu.itu.cavabunga.cavabungacaldav.exception.CaldavException;

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

    public static CaldavRequestMethod convertToEnum(String requestMethodName){
        try {
            return CaldavRequestMethod.valueOf(requestMethodName);
        }catch (IllegalArgumentException e){
            throw new CaldavException("Http method is not in CaldavRequestMethod enum: " + e.getMessage());
        }
    }
}
