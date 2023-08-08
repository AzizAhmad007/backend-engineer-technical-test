package smarthomestay.betest.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "mst_employee", schema = "public")
public class MstEmployee {
    @Id
    @SequenceGenerator(name = "mst_employee_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mst_employee_seq")
    @Column(name = "employee_id")
    private Long employeeId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "employee_phone")
    private String employeePhone;

    @Column(name = "employee_email")
    private String employeeEmail;

    @Column(name = "password")
    private String password;

    @Column(name = "token")
    private String token;
}
