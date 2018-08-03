package pl.rmitula.springsecurityfirstapp.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class ErrorResponse {

    private Integer code;
    private String type;
    private String description;
}