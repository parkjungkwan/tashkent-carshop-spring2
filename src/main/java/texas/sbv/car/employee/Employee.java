package texas.sbv.car.employee;

import lombok.*;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Component
@Entity
@NoArgsConstructor
@Setter
@Getter
@ToString
@Lazy
@Table(name = "employee")
public class Employee implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull @Column(name = "em_id") private Long emId;
    @NotNull @Column(name = "em_code",length = 6) private String emCode;
    @Column(name = "month_revenue") private int monthRevenue;
    @Column(name = "center_name", length = 20) private String centerName;
    @Column(name = "em_name", length = 20) private String emName;
    @Column(name = "center_code", length = 3) private String centerCode;
    @Column(name = "em_position", length = 3) private String emPosition;
    @Column(name = "cardcd", length = 12) private String carcd;
    @Column(name = "em_img", length = 100) private String emImg;

}
