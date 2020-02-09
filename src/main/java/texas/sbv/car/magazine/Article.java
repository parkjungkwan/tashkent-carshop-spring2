package texas.sbv.car.magazine;

import lombok.*;
import org.springframework.context.annotation.Lazy;
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
@Lazy
@ToString
@Table(name = "magazine")
public class Article implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull @Column(name = "aid") private Long aid;
    @NotNull @Column(name = "article_id", length = 12) private String aricleId;
    @Column(name = "subject", length = 300) private String subject;
    @Column(name = "writer", length = 20) private String writer;
    @Column(name = "write_date", length = 6) private String writeDate;
    @Column(name = "article_href", length = 100) private String articleHref;
    @Column(name = "article_img", length = 100) private String articleImg;


    @Builder
    public Article (String aricleId,String subject,String writer,String writeDate,String articleHref,String articleImg) {
        Assert.hasText(aricleId, "aricleId must not be empty");
        this.aricleId = aricleId;
        this.subject = subject;
        this.writer = writer;
        this.writeDate = writeDate;
        this.articleHref = articleHref;
        this.articleImg = articleImg;
    }
}
