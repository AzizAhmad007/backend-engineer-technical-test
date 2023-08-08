package smarthomestay.betest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MstEmployeeDTO {
    private Long employeeId;
    private String firstName;
    private String lastName;
    private String employeeEmail;
    private String password;
    private String employeePhone;
}
