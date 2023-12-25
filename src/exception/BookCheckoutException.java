package exception;

public class BookCheckoutException extends RuntimeException{
    public BookCheckoutException(String msg) {
        super(msg);
    }
}
