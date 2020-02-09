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
@Table(name = "RECENTSEARCHWORD")
public class RecentSearchWord implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull @Column(name = "RSWID") private Long rswId;
    @NotNull @Column(name = "SEARCHWORD", length = 100) private String searchWord;
    @Column(name = "USERID", length = 50) private String userId;
    @Column(name = "SEARCHTIME") private Long searchTime;

    @Builder
    public RecentSearchWord(
            String searchWord,long searchTime, String userId
    ) {
        Assert.hasText(searchWord, "searchWord must not be empty");

        this.searchWord = searchWord;
        this.userId = userId;
        this.searchTime = searchTime;
    }



}
