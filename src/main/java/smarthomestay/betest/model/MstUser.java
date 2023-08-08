package smarthomestay.betest.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "mst_user", schema = "public")
public class MstUser {
    @Id
    @SequenceGenerator(name = "mst_user_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mst_user_seq")
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "user_phone")
    private String userPhone;

    @Column(name = "user_email")
    private String userEmail;

    @Column(name = "password")
    private String password;

    @Column(name = "token")
    private String token;
}
