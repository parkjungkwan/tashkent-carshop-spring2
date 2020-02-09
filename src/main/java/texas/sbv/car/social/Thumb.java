package texas.sbv.car.social;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import texas.sbv.car.user.User;

@Component @Lazy @Entity
@ToString
@Getter @Setter(AccessLevel.PUBLIC)
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Table(name="THUMB")
public class Thumb implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "THUMBSEQ") @NotNull private Long thumbSeq;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BOARDSEQ")
    @NotNull
    private Social boardSeq;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USERSEQ")
    @NotNull
    private User userSeq;

}
