package texas.sbv.car.social;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import texas.sbv.car.user.User;

@Component @Lazy @Entity
@Getter @Setter
@ToString(exclude = {"userSeq","thumbs"})
@NoArgsConstructor
@Table(name="social_board")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "boardSeq")
public class Social implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "board_seq") @NotNull private Long boardSeq;
    @Column(name = "board_date", length=25) @NotNull private String boardDate;
    @Column(name = "car_code", length=50) @NotNull private String carCode;
    @Column(name = "car_name", length=100) @NotNull private String carName;
    @Column(name = "board_content", length = 20000) @NotNull private String boardContent;
    @Column(name = "board_img", length=200) @NotNull private String boardImg;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USERSEQ")
    @NotNull
    private User userSeq;

    @OneToMany(mappedBy = "boardSeq", cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Thumb> thumbs = new ArrayList<>();

    @Builder
    private Social(String boardDate, String carCode, String carName, String boardContent, String boardImg) {
        Assert.hasText(boardDate, "boardDate must not be empty");
        Assert.hasText(carCode, "carCode must not be empty");
        Assert.hasText(boardContent, "boardContent must not be empty");
        Assert.hasText(boardImg, "boardImg must not be empty");
        this.boardDate = boardDate;
        this.carCode = carCode;
        this.carName = carName;
        this.boardContent = boardContent;
        this.boardImg = boardImg;
    }
}
