package pl.rmitula.springsecurityfirstapp.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pl.rmitula.springsecurityfirstapp.exception.*;
import pl.rmitula.springsecurityfirstapp.utils.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleNotFoundException(NotFoundException exception) {
        return new ErrorResponse(HttpStatus.NOT_FOUND.value(), exception.getClass().getSimpleName(), exception.getMessage());
    }

    @ExceptionHandler(NotEmployeesFoundExcpetion.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleNotEmployeesFoundException(NotEmployeesFoundExcpetion exception) {
        return new ErrorResponse(HttpStatus.NOT_FOUND.value(), exception.getClass().getSimpleName(), exception.getMessage());
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleBadRequestException(BadRequestException exception) {
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), exception.getClass().getSimpleName(), exception.getMessage());
    }

    // FIXME: fixme
    @ExceptionHandler(NotAuthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorResponse handleNotAuthorizedException(NotAuthorizedException exception) {
        return new ErrorResponse(HttpStatus.UNAUTHORIZED.value(), exception.getClass().getSimpleName(), exception.getMessage());
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorResponse handleAccessDeniedException(AccessDeniedException exception) {
        return new ErrorResponse(HttpStatus.FORBIDDEN.value(), exception.getClass().getSimpleName(), exception.getMessage());
    }

    @ExceptionHandler(ConflictException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleConflictException(ConflictException exception) {
        return new ErrorResponse(HttpStatus.CONFLICT.value(), exception.getClass().getSimpleName(), exception.getMessage());
    }
}
