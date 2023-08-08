package smarthomestay.betest.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "mst_add_facility", schema = "public")
public class MstAddFacility {
    @Id
    @SequenceGenerator(name = "mst_add_facilty_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mst_add_facilty_seq")
    @Column(name = "facility_id")
    private Long facilityId;

    @Column(name = "additional_facilities")
    private String additionalFacilities;

    @Column(name = "price")
    private BigDecimal price;
}
