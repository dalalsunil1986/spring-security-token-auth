package pl.rmitula.springsecurityfirstapp.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pl.rmitula.springsecurityfirstapp.exception.BadRequestException;
import pl.rmitula.springsecurityfirstapp.exception.NotEmployeesFoundExcpetion;
import pl.rmitula.springsecurityfirstapp.exception.NotFoundException;
import pl.rmitula.springsecurityfirstapp.dto.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleNotFoundException(NotFoundException exception) {
        return new ErrorResponse(404, NotFoundException.class.getSimpleName(), exception.getMessage());
    }

    @ExceptionHandler(NotEmployeesFoundExcpetion.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleNotEmployeesFoundException(NotEmployeesFoundExcpetion exception) {
        return new ErrorResponse(404, NotEmployeesFoundExcpetion.class.getSimpleName(), exception.getMessage());
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleBadRequestException(BadRequestException exception) {
        return new ErrorResponse(400, BadRequestException.class.getSimpleName(), exception.getMessage());
    }
}
