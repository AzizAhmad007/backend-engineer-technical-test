package smarthomestay.betest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.BigInteger;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MstRoomDTO {
    private Long roomId;
    private String roomName;
    private BigDecimal price;
}
