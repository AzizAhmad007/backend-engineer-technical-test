package smarthomestay.betest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MstTransaksiDTO {
    private Long transaksiId;
    private Long userId;
    private Long employeeId;
    private Long roomId;
    private Long facilityId;
    private BigDecimal numberOfRoom;
    private String status;
    private Date dateCheckin;
    private Date dateCheckout;
    private BigDecimal lengthOfStay;

}
