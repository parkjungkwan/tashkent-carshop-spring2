package texas.sbv.car.recommend;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Component
@Lazy
@Entity
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="recommend")
public class Recommend implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "reco_seq") @NotNull private Long recoSeq;
    @Column(name = "userid" ,length = 25) @NotNull private String userid;
    @Column(name = "name", length = 30) @NotNull private String name;
    @Column(name = "center_code" ,length = 3) @NotNull private String centerCode;
    @Column(name = "center_region", length = 10) private String centerRegion;
    @Column(name = "center_name", length = 20) private String centerName;
    @Column(name = "min_begin_year") private int minBeginYear;
    @Column(name = "max_begin_year") private int maxBeginYear;
    @Column(name = "min_price") private int minPrice;
    @Column(name = "max_price") private int maxPrice;
    @Column(name = "min_milage") private int minMilage;
    @Column(name = "max_milage") private int maxMilage;
    @Column(name = "transmissioncd_name", length = 10) private String transmissioncdName;
    @Column(name = "fule_typed_name", length = 9) private String fuleTypedName;
    @Column(name = "make_nm", length = 20) private String makeNm;
    @Column(name = "model_grp_nm", length = 100) private String modelGrpNm;
    @Column(name = "model_nm", length = 45) private String modelNm;
    @Column(name = "rec_comment_cd", length = 3) private String recCommentCd;


}
