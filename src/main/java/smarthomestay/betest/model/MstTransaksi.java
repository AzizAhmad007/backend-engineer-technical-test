package smarthomestay.betest.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "mst_transaksi", schema = "public")
public class MstTransaksi {
    @Id
    @SequenceGenerator(name = "mst_transaksi_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mst_transaksi_seq")
    @Column(name = "transaksi_id")
    private Long transaksiId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "employee_id")
    private Long employeeId;

    @Column(name = "room_id")
    private Long roomId;

    @Column(name = "facility_id")
    private Long facilityId;

    @Column(name = "number_of_room")
    private BigDecimal numberOfRoom;

    @Column(name = "status")
    private String status;

    @Column(name = "date_checkin")
    private Date dateCheckin;

    @Column(name = "date_checkout")
    private Date dateCheckout;

    @Column(name = "length_of_stay")
    private BigDecimal lengthOfStay;

    @Column(name = "total")
    private BigDecimal total;
}
