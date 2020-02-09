package texas.sbv.car.recommendedCar;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import texas.sbv.car.car.Car;

@Component
@Entity
@NoArgsConstructor
@Setter(AccessLevel.PUBLIC)
@Getter
@ToString
@Table(name = "recommended_car")
public class RecommendedCar implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "reco_car_seq") @NotNull private Long recoCarSeq;
    @Column(name = "userid", length = 25) @NotNull private String userid;
    @Column(name = "center_code", length = 3) @NotNull private String centerCode;

    @OneToOne
    @JoinColumn(name="cid")
    private Car cars;
}
