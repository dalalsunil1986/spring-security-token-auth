package pl.rmitula.springsecurityfirstapp.exception;

public class NotAuthorizedException extends RuntimeException {
    public NotAuthorizedException(String msg) {
        super(msg);
    }
}