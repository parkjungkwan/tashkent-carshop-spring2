package texas.sbv.car.car;

import lombok.*;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Component
@Entity
@NoArgsConstructor
@Setter(AccessLevel.PUBLIC)
@Getter
@ToString
@Table(name = "RECENTSEENCAR")
public class RecentSeenCar implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull @Column(name = "RSCID") private Long rscId;
    @NotNull @Column(name = "CARCD", length = 12) private String carcd;
    @Column(name = "USERID", length = 50) private String userId;
    @Column(name = "SEARCHTIME") private Long searchTime;


    @Builder
    public RecentSeenCar(
            String carcd, long searchTime, String userId
    ) {
        Assert.hasText(carcd, "carcd must not be empty");

        this.carcd = carcd;
        this.userId = userId;
        this.searchTime = searchTime;
    }



}
