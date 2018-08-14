package tr.edu.itu.cavabunga.cavabungacaldav.caldav.enumerator;

import tr.edu.itu.cavabunga.cavabungacaldav.exception.CaldavException;

public enum ExpressionEnum {
    USERNAME("{username}"),
    USER_MAIL("{user_mail}");

    private String value;

    ExpressionEnum(String text){
        this.value = text;
    }

    @Override
    public String toString(){
        return this.value;
    }

    public ExpressionEnum convertToEnum(String text){
        try {
            return ExpressionEnum.valueOf(text);
        } catch (IllegalArgumentException e){
            throw new CaldavException("Exression: " + text + " is not in ExpressionEnum");
        }
    }

}
