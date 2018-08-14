package tr.edu.itu.cavabunga.cavabungacaldav.exception;

public class CaldavException extends RuntimeException {
    public CaldavException(String message){super(message);}
    public CaldavException(Throwable cause){super(cause);}
    public CaldavException(){}
}
