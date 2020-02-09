package texas.sbv.car.record;

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

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import texas.sbv.car.carbook.CarBook;

@Component
@Entity
@Getter
@ToString
@Setter
@NoArgsConstructor
@Lazy
@Table(name="records")
public class Record  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull @Column(name = "record_id") private Long recordId;
    @Column(name = "record_date") private String recordDate;
    @Column(name = "service_code") private String serviceCode;
    @Column(name = "detail") private String detail;
    @Column(name = "price") private String price;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mycarId")
    private CarBook mycarId;



    @Builder
    public Record(String recordDate, String serviceCode, String detail,
                String price) {
        this.recordDate = recordDate;
        this.serviceCode = serviceCode;
        this.detail = detail;
        this.price = price;

    }
}
