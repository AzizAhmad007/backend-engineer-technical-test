package smarthomestay.betest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MstUserDTO {
    private Long userId;
    private String firstName;
    private String lastName;
    private String userEmail;
    private String password;
    private String userPhone;
}
