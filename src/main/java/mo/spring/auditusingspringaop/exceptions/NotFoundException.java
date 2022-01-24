package mo.spring.auditusingspringaop.exceptions;

public class NotFoundException extends RuntimeException {
    private static final long serialVersionUID = -318803497415355519L;

    public NotFoundException(String message) {
        super(message);
    }
}
