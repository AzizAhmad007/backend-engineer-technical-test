package smarthomestay.betest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MstAddFacilityDTO {
    private Long facilityId;
    private String additionalFacilities;
    private BigDecimal price;
}
