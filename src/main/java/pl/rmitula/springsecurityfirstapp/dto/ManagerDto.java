package pl.rmitula.springsecurityfirstapp.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ManagerDto {
    private Long id;
    private Long user;
    private Long department;
    private String startDate;
}
