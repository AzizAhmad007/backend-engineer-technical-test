package smarthomestay.betest.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;

@Getter
@Setter
@Entity
@Table(name = "mst_room", schema = "public")
public class MstRoom {
    @Id
    @SequenceGenerator(name = "mst_room_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mst_room_seq")
    @Column(name = "room_id")
    private Long roomId;

    @Column(name = "room_name")
    private String roomName;

    @Column(name = "price")
    private BigDecimal price;

}
