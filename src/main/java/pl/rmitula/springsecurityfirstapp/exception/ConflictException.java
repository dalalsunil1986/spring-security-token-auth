package pl.rmitula.springsecurityfirstapp.exception;

public class ConflictException extends RuntimeException {
    public ConflictException(String msg) {
        super(msg);
    }
}