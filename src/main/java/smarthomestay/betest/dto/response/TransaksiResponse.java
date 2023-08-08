package smarthomestay.betest.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import smarthomestay.betest.dto.MstAddFacilityDTO;
import smarthomestay.betest.dto.MstEmployeeDTO;
import smarthomestay.betest.dto.MstRoomDTO;
import smarthomestay.betest.dto.MstUserDTO;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransaksiResponse {
    private MstUserDTO user;
    private MstEmployeeDTO employee;
    private MstRoomDTO room;
    private MstAddFacilityDTO addFacility;
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
    private BigDecimal total;
}
