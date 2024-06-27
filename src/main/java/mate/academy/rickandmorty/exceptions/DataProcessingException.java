package mate.academy.rickandmorty.exceptions;

public class DataProcessingException extends RuntimeException {
    public DataProcessingException(String message, Exception e) {
        super(message,e);
    }

    public DataProcessingException(String message) {
        super(message);
    }
}
