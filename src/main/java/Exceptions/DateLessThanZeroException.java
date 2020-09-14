package Exceptions;

public class DateLessThanZeroException extends RuntimeException {
    public DateLessThanZeroException(String message){
        super(message);
    }
}
