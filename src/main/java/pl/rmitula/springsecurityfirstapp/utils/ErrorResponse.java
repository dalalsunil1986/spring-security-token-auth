package pl.rmitula.springsecurityfirstapp.utils;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class ErrorResponse {

    private Integer status;
    private String error;
    private String message;
}