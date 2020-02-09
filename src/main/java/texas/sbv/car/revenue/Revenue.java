package texas.sbv.car.revenue;

import lombok.*;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Component
@Entity
@Lazy
@NoArgsConstructor
@Setter(AccessLevel.PUBLIC)
@Getter
@ToString
@Table(name = "revenue")
public class Revenue {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "revenue_seq") @NotNull private Long revenueSeq;
    @Column(name = "center_code", length = 3) @NotNull private String centerCode;
    @Column(name = "month") @NotNull private String month;
    @Column(name = "em_name", length = 20)@NotNull private String emName;
    @Column(name = "em_code",length = 6) @NotNull private String emCode;
    @Column(name = "em_revene") @NotNull private int emRevenue;
}
