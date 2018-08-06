package pl.rmitula.springsecurityfirstapp.exception.handler;

import com.fasterxml.jackson.core.JsonParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import pl.rmitula.springsecurityfirstapp.exception.*;
import pl.rmitula.springsecurityfirstapp.utils.ErrorResponse;

import javax.validation.ConstraintViolationException;

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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), exception.getClass().getSimpleName(), exception.getMessage());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleHttpMessageNotReadableException(HttpMessageNotReadableException exception) {
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), exception.getClass().getSimpleName(), exception.getMessage());
    }

    //NumberFormatException
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException exception) {
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), exception.getClass().getSimpleName(), exception.getMessage());
    }

    //Entity fields validation
    @ExceptionHandler(JsonParseException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleConstraintViolationException(ConstraintViolationException exception) {
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), exception.getClass().getSimpleName(), exception.getMessage());
    }


}
