package smarthomestay.betest.common;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseDTO {
    private String code;
    private String message;
    private Object data;
}
