package Exceptions;

public class ToDateLessThanFromDateException extends RuntimeException {
    public ToDateLessThanFromDateException(String message){
        super(message);
    }
}
