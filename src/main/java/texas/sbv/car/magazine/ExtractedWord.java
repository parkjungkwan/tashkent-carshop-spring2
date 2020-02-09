package texas.sbv.car.magazine;

import lombok.*;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Component
@Entity
@Lazy
@NoArgsConstructor
@Setter(AccessLevel.PUBLIC)
@Getter
@ToString
@Table(name = "EXTRACTEDWORD")
public class ExtractedWord implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull @Column(name = "WID") private Long wid;
    @Column(name = "WORD") private String word;
    @Column(name = "COUNT") private int count;

    @Builder
    public ExtractedWord (String word , int count ) {
        this.word = word;
        this.count = count;
    }

}
