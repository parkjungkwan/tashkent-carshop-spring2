package texas.sbv.car.carbook;

import lombok.*;
import texas.sbv.car.user.User;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Component
@Lazy
@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name="mycar")
public class CarBook implements Serializable { 
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "mycar_id")  @NotNull  private Long mycarId;
    @Column(name = "brand")   private String brand;
    @Column(name = "model")  private String model;
    @Column(name = "year")   private String year;
    @Column(name = "month")   private String month;
    @Column(name = "distance")   private String distance;
    @Column(name = "fuel_type") private String fuelType;

    @OneToOne
    @JoinColumn(name = "user_seq")
    private User userSeq;

    @Builder
    public CarBook(String brand, String model, String year,
                   String month, String distance, String fuelType) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.month = month;
        this.distance = distance;
        this.fuelType = fuelType;
        

    }
    
}
