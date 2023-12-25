package exception;

public class ExceedLimitationException extends RuntimeException{
    public ExceedLimitationException(String msg) {
        super(msg);
    }
}
